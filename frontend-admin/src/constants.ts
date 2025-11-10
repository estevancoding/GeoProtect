
// Arquivo para armazenar constantes usadas na aplicação frontend.
import type {LatLngExpression} from "leaflet";

// Configurações padrão para o mapa
export const MAP_CENTER: LatLngExpression = [-23.55052, -46.633308];
export const MAP_ZOOM: number = 13;

// Configurações do provedor de tiles do mapa (OpenStreetMap)
export const TILE_LAYER_URL: string = "https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png";
export const TILE_LAYER_ATTRIBUTION: string = '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors';
