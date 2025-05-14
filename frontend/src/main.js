import { createApp } from 'vue';
import App from './App.vue';
import router from './router';
import api from './services/api.js';     // ← assure-toi du “.js”


import './assets/styles/tailwind.css';

const app = createApp(App);

// on expose axios pré-configuré en this.$http
app.config.globalProperties.$http = api;
// 🛡️ Garde de navigation : vérifie l'authentification si route protégée
router.beforeEach(async (to, from, next) => {
    // Vérifie si la route nécessite l'authentification
    if (to.meta.requiresAuth) {
        try {
            const response = await api.get('/auth/check');
            if (response.status === 200) {
                next(); // autorisé
            } else {
                window.location.href = '/login'; // redirige vers Spring Security
            }
        } catch {
            window.location.href = '/login'; // pas connecté, redirige
        }
    } else {
        next(); // aucune restriction
    }
});

app.use(router).mount('#app');
