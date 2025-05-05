import { createRouter, createWebHistory } from 'vue-router';
import ShowList from '@/components/ShowList.vue';
import ArtistList from "@/components/ArtistList.vue"; // Corrige le chemin si ShowList.vue est ailleurs

const routes = [
  {
    path: '/',
    redirect : '/spectacles'    //par defaut on aura la liste ( temporaire )
  },
  {
    path: '/spectacles',
    name: 'Showlist',
    component: ShowList,
  },
  {
    path: '/artists',
    name: 'Artistlist',
    component: ArtistList,
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;