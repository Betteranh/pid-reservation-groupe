import { createRouter, createWebHistory } from 'vue-router'; // Importer vue-router
import ShowList from './components/ShowList.vue'; // Importer le composant pour la liste des spectacles

const routes = [
    {
        path: '/', // Route principale, affiche la liste des spectacles
        name: 'ShowList',
        component: ShowList,
    },

];

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL), // Créer l'historique du routeur
    routes, // Liste des routes
});

export default router; // Exporter le routeur configuré
