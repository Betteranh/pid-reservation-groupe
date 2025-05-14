import axios from 'axios';

const api = axios.create({
    baseURL: '/api',
    withCredentials: true // nécessaire pour envoyer les cookies (JSESSIONID, XSRF-TOKEN)
});

// Intercepteur : ajoute automatiquement le header CSRF à chaque requête (si le cookie est présent)
api.interceptors.request.use(config => {
    const token = getCookie('XSRF-TOKEN');
    if (token) {
        config.headers['X-XSRF-TOKEN'] = token;
    }

    return config;
});

// Fonction utilitaire pour lire un cookie
function getCookie(name) {
    const value = `; ${document.cookie}`;
    const parts = value.split(`; ${name}=`);
    if (parts.length === 2) return parts.pop().split(';').shift();
}

export default api;
