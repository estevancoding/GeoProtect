import React, { useEffect, useState } from 'react';
import { useAuth } from '../contexts/AuthContext';
import { MapContainer, TileLayer, Polygon, Marker, Popup, useMapEvents } from 'react-leaflet';
import { type LatLngExpression, LatLng, Icon } from 'leaflet';
import { createAuthenticatedApi, checkLocation } from '../services/api';
import { MAP_CENTER, MAP_ZOOM, TILE_LAYER_ATTRIBUTION, TILE_LAYER_URL } from '../constants';
import './DashboardPage.css'; // Importa os estilos CSS
import 'leaflet/dist/leaflet.css'; // Importa o CSS do Leaflet

// --- Interfaces movidas para cá para evitar erro de módulo ---
export interface GeoJsonPolygon {
    type: "Polygon";
    coordinates: number[][][];
}
export interface ZonaDeRisco {
    id: number;
    nome: string;
    area: GeoJsonPolygon;
}
// ---------------------------------------------------------

// Configuração do ícone padrão para os marcadores
import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';

const redOptions = { color: 'red' };

const DashboardPage: React.FC = () => {
    // Move a criação do ícone para dentro do componente
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

    // Componente interno para lidar com eventos do mapa
    const MapClickHandler = () => {
        useMapEvents({
            click(e) {
                setMarker(e.latlng); // Define a posição do novo marcador
                if (token) {
                    // Envia a localização para o backend, sem logar erros no console
                    checkLocation(token, e.latlng.lat, e.latlng.lng).catch(() => {});
                }
            },
        });
        return null;
    };

    const handleRemoveMarker = () => {
        setMarker(null);
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
            <header className="dashboard-header">
                <h1>Simular Localização</h1>
            </header>
            <main className="dashboard-main">
                <MapContainer center={MAP_CENTER} zoom={MAP_ZOOM} className="map-container">
                    <TileLayer
                        url={TILE_LAYER_URL}
                        attribution={TILE_LAYER_ATTRIBUTION}
                    />

                    {/* Componente para escutar cliques no mapa */}
                    <MapClickHandler />

                    {/* Renderiza as zonas de risco */}
                    {zones.map(zone => (
                        <Polygon key={zone.id} pathOptions={redOptions} positions={zone.area.coordinates[0].map((coord) => [coord[1], coord[0]]) as LatLngExpression[]} />
                    ))}

                    {/* Renderiza o marcador se ele existir */}
                    {marker && (
                        <Marker position={marker} icon={defaultIcon}>
                            <Popup>
                                Localização selecionada. <br />
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
