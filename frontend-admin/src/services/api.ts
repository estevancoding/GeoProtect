import axios from 'axios';

const api = axios.create({
    baseURL: '/api',
});

export const login = async (email: string, password: string) => {
    const response = await api.post('/auth/login', { email, password });
    return response.data;
};

export const createAuthenticatedApi = (token: string) => {
    const authenticatedApi = axios.create({
        baseURL: '/api',
        headers: {
            Authorization: `Bearer ${token}`,
        },
    });

    // Interceptor para lidar com tokens expirados/inválidos
    authenticatedApi.interceptors.response.use(
        (response) => response,
        (error) => {
            if (error.response && [401, 403].includes(error.response.status)) {
                localStorage.removeItem('token');
                // Força o redirecionamento para a página de login
                window.location.href = '/login';
            }
            return Promise.reject(error);
        }
    );

    return authenticatedApi;
};

export const checkLocation = async (token: string, lat: number, lng: number) => {
    const authenticatedApi = createAuthenticatedApi(token);
    return await authenticatedApi.post('/v1/zonas-de-risco/verificar', { lat, lng });
};

export default api;
