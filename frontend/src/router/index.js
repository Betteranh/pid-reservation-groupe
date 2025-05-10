import { createRouter, createWebHistory } from 'vue-router';

import ShowList from '@/views/ShowList.vue';
import ArtistList from "@/views/artist/ArtistList.vue";
import ArtistDetail from "@/views/artist/ArtistDetail.vue";
import ArtistForm from "@/views/artist/ArtistForm.vue";
import Home from "@/views/Home.vue";
import LoginPage from "@/views/LoginPage.vue";
import LocalityList from "@/views/locality/LocalityList.vue";
import LocalityShow from "@/views/locality/LocalityShow.vue";
import RepresentationList from "@/views/representation/RepresentationList.vue";
import RepresentationDetail from "@/views/representation/RepresentationDetail.vue";


const routes = [
  {
    path: '/',
    name:'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'LoginPage',
    component: LoginPage,
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
    meta: {requiresAuth: true},
  },
  {
    path: '/artists/:id',
    name: 'ArtistDetail',
    component : ArtistDetail,

  },
  {
    path: '/artists/:id/edit',
    name: 'ArtistEdit',
    component : ArtistForm,

  },
  {
    path: '/artists/create',
    name: 'ArtistCreate',
    component : ArtistForm,

  },
  {
    path: '/localities',
    name: 'LocalityList',
    component: LocalityList,
  },
  {
    path: '/localities/:id',
    name: 'LocalityShow',
    component: LocalityShow,
  },
  {
    path: '/representations',
    name: 'RepresentationList',
    component: RepresentationList,
  },
  {
    path: '/representations/:id',
    name: 'RepresentationDetail',
    component: RepresentationDetail,
  },


];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// equivalent du @authorized has role {admin ou autre } dans le controller mais pour le frontend
// rien n'interdit de le faire ici et dans le backend dans les apicontroller (controller restfull)

router.beforeEach((to, from, next) => {
  const requiresAuth = to.matched.some(record => record.meta && record.meta.requiresAuth);
  if (requiresAuth) {
    fetch('http://localhost:8080/api/auth/check', {
      credentials: 'include',
    })
        .then(response => {
          console.log('Auth check response:', response);
          if (response.ok) next();
          else next({ name: 'LoginPage' });
        })
        .catch(error => {
          console.error('Auth check error:', error);
          next({ name: 'LoginPage' });
        });
  } else {
    next();
  }
});

export default router;