import { createRouter, createWebHistory } from 'vue-router';


import AppHome from "@/views/AppHome.vue";
import LoginTest from "@/views/LoginTest.vue";

import LocalitiesList from "@/views/LocalitiesList.vue";
import LocalityDetails from "@/views/LocalityDetails.vue";
import Showlist from "@/views/shows/ShowList.vue";
import ShowDetails from "@/views/shows/ShowDetails.vue";


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


];

const router = createRouter({
  history: createWebHistory(),
  routes,
});



export default router;