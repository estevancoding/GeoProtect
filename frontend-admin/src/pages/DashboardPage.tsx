import React, { useEffect, useState } from 'react';
import { useAuth } from '../contexts/AuthContext';
import { MapContainer, TileLayer, Polygon, Marker, Popup, useMapEvents } from 'react-leaflet';
import { type LatLngExpression, LatLng, Icon } from 'leaflet';
import { createAuthenticatedApi, checkLocation } from '../services/api';
import { MAP_CENTER, MAP_ZOOM, TILE_LAYER_ATTRIBUTION, TILE_LAYER_URL } from '../constants';
import Notification from '../components/Notification';
import './DashboardPage.css';
import 'leaflet/dist/leaflet.css';
import '../components/Notification.css';

// --- Interfaces ---
export interface GeoJsonPolygon {
    type: "Polygon";
    coordinates: number[][][];
}
export interface ZonaDeRisco {
    id: number;
    nome: string;
    area: GeoJsonPolygon;
}
export interface Status {
    inRiskArea: boolean;
    message: string;
}
// ------------------

// --- Icon Configuration ---
import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';

const redOptions = { color: 'red' };

const DashboardPage: React.FC = () => {
    const defaultIcon = new Icon({
        iconUrl: markerIcon,
        iconRetinaUrl: markerIcon2x,
        shadowUrl: markerShadow,
        iconSize: [25, 41],
        iconAnchor: [12, 41],
        popupAnchor: [1, -34],
        shadowSize: [41, 41]
    });

    const { token } = useAuth();
    const [zones, setZones] = useState<ZonaDeRisco[]>([]);
    const [marker, setMarker] = useState<LatLng | null>(null);
    const [locationStatus, setLocationStatus] = useState<Status | null>(null);
    const [notification, setNotification] = useState<string | null>(null);
    const [notificationKey, setNotificationKey] = useState<number>(Date.now());

    const MapClickHandler = () => {
        useMapEvents({
            click(e) {
                setMarker(e.latlng);
                if (token) {
                    checkLocation(token, e.latlng.lat, e.latlng.lng)
                        .then(response => {
                            const status = {
                                inRiskArea: response.data.emZonaDeRisco,
                                message: response.data.message
                            };
                            setLocationStatus(status);
                            setNotification(status.message);
                            setNotificationKey(Date.now()); // Update key to force re-render
                        })
                        .catch(() => {
                            setLocationStatus(null);
                            setNotification("Erro ao verificar a localização.");
                            setNotificationKey(Date.now()); // Update key even on error
                        });
                }
            },
        });
        return null;
    };

    const handleRemoveMarker = () => {
        setMarker(null);
        setLocationStatus(null);
        setNotification(null);
        setNotificationKey(Date.now()); // Reset key when marker is removed
    }

    useEffect(() => {
        if (token) {
            const api = createAuthenticatedApi(token);
            api.get('/v1/zonas-de-risco')
                .then(response => {
                    setZones(response.data);
                });
        }
    }, [token]);

    return (
        <div className="dashboard-container">
            {notification && <Notification key={notificationKey} message={notification} onClose={() => setNotification(null)} />}
            <header className="dashboard-header">
                <h1>Simular Localização</h1>
            </header>
            <main className="dashboard-main">
                <MapContainer center={MAP_CENTER} zoom={MAP_ZOOM} className="map-container">
                    <TileLayer
                        url={TILE_LAYER_URL}
                        attribution={TILE_LAYER_ATTRIBUTION}
                    />
                    <MapClickHandler />
                    {zones.map(zone => (
                        <Polygon key={zone.id} pathOptions={redOptions} positions={zone.area.coordinates[0].map((coord) => [coord[1], coord[0]]) as LatLngExpression[]} />
                    ))}
                    {marker && (
                        <Marker position={marker} icon={defaultIcon}>
                            <Popup>
                                {locationStatus ? locationStatus.message : "Verificando..."} <br />
                                <button onClick={handleRemoveMarker}>Remover</button>
                            </Popup>
                        </Marker>
                    )}
                </MapContainer>
            </main>
        </div>
    );
};

export default DashboardPage;
