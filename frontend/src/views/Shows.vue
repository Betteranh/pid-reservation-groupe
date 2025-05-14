<template>
  <div class="max-w-7xl mx-auto px-4 py-8">
    <h1 class="text-3xl font-bold mb-6 text-center">Spectacles disponibles</h1>

    <div v-if="loading" class="text-center text-gray-500">Chargement en cours...</div>
    <div v-else-if="error" class="text-center text-red-500">Erreur lors du chargement des spectacles.</div>

    <div v-else class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
      <div
          v-for="show in shows"
          :key="show.id"
          class="bg-white shadow-md rounded-lg p-4 flex flex-col justify-between"
      >
        <div>
          <img :src="show.imageUrl || '/images/default-show.jpg'" alt="affiche" class="rounded mb-4 h-48 w-full object-cover" />
          <h2 class="text-xl font-semibold mb-2">{{ show.title }}</h2>
          <p class="text-gray-600 text-sm mb-2">{{ formatDate(show.date) }}</p>
          <p class="text-gray-700">{{ show.description }}</p>
        </div>
        <router-link
            :to="`/shows/${show.id}`"
            class="mt-4 inline-block bg-blue-500 text-white text-center py-2 px-4 rounded hover:bg-blue-600"
        >
          Réserver
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  name: 'ShowsPage',
  data() {
    return {
      shows: [],
      loading: true,
      error: false
    };
  },
  async mounted() {
    try {
      const response = await api.get('/shows'); // ou '/api/shows' selon ton backend
      this.shows = response.data;
    } catch (e) {
      console.error(e);
      this.error = true;
    } finally {
      this.loading = false;
    }
  },
  methods: {
    formatDate(dateStr) {
      const date = new Date(dateStr);
      return date.toLocaleDateString('fr-FR', {
        weekday: 'long',
        day: 'numeric',
        month: 'long',
        year: 'numeric'
      });
    }
  }
};
</script>

<style scoped>
/* Ajoute si nécessaire des styles personnalisés ici */
</style>
