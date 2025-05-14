import { createRouter, createWebHistory } from 'vue-router';


import AppHome from "@/views/AppHome.vue";
import LoginTest from "@/views/LoginTest.vue";

import LocalitiesList from "@/views/locations/LocalitiesList.vue";
import LocalityDetails from "@/views/locations/LocalityDetails.vue";
import Showlist from "@/views/shows/ShowList.vue";
import ShowDetails from "@/views/shows/ShowDetails.vue";
import Crud from "@/views/tags/Crud.vue";
import InfosPratiques from "@/views/info/InfosPratiques.vue";



const routes = [
  {
    path: '/',
    name:'Home',
    component: AppHome
  },
  {
    path: '/login',
    name: 'Login',
    component: LoginTest,
  },
  {
    path: '/shows',
    name: 'ShowList',
    component: Showlist
  },
  {
    path: '/shows/:id',
    name: 'ShowDetails',
    component: ShowDetails
  },
  {
    path: '/localities',
    name: 'LocalitiesList',
    component: LocalitiesList
  },
  {
    path: '/localities/:id',
    name: 'LocalityDetails',
    component: LocalityDetails,
    meta: {requiresAuth: true}
  },
  {
    path: '/tags',
    name: 'crud',
    component: Crud,
    meta: {requiresAuth: true}
  },
  {
    path: '/infos',
    name: 'InfosPratiques',
    component: InfosPratiques

  }



];

const router = createRouter({
  history: createWebHistory(),
  routes,
});



export default router;