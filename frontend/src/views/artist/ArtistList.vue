<template>
  <div class="container py-5">
    <h1 class="mb-4 text-center">Liste des Artistes</h1>

    <div v-if="loading" class="text-center">
      <div class="spinner-border" role="status">
        <span class="visually-hidden">Chargement…</span>
      </div>
    </div>

    <div v-else-if="artists.length === 0" class="alert alert-info text-center">
      Aucun artiste disponible.
    </div>

    <ul v-else class="list-group">
      <li
          v-for="artist in artists"
          :key="artist.id"
          class="list-group-item d-flex justify-content-between align-items-center"
      >
        <span>{{ artist.firstname }} {{ artist.lastname }}</span>
        <router-link
            :to="{ name: 'ArtistDetail', params: { id: artist.id } }"
            class="btn btn-sm btn-outline-primary"
        >
          Voir
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import artistService from "@/services/artistService";  // Importer le service

export default {
  name: 'ArtistList',
  data() {
    return {
      artists: [],
      loading: true,
      error: false
    };
  },
  async mounted() {
    try {
      const res = await artistService.getAll();  // Appeler le service
      this.artists = res.data;
    } catch (e) {
      this.error = true;
    } finally {
      this.loading = false;
    }
  }
};
</script>

<style scoped>
/* Ajoute ici des styles personnalisés si nécessaire */
</style>
