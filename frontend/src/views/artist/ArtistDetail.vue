<template>
  <div class="container py-5">
    <h1 class="mb-4 text-center">Fiche de l'Artiste</h1>

    <!-- Section de chargement -->
    <div v-if="loading" class="text-center">
      <div class="spinner-border" role="status">
        <span class="visually-hidden">Chargement…</span>
      </div>
    </div>

    <!-- Section d'erreur -->
    <div v-else-if="error" class="alert alert-danger text-center">
      Une erreur est survenue lors du chargement des détails.
    </div>

    <!-- Détails de l'artiste -->
    <div v-else-if="artist" class="bg-white border rounded shadow p-4">
      <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-2xl font-semibold text-gray-800">{{ artist.firstname }} {{ artist.lastname }}</h2>
        <div>
          <router-link :to="{ name: 'ArtistEdit', params: { id: artist.id } }" class="btn btn-warning btn-sm me-2">Modifier</router-link>
          <button class="btn btn-danger btn-sm" @click="deleteArtist">Supprimer</button>
        </div>
      </div>

      <div>
        <h3 class="text-lg font-medium text-gray-700 mb-2">Types associés :</h3>
        <ul class="list-group">
          <li v-for="(type, index) in artist.types" :key="index" class="list-group-item">
            {{ type }}
          </li>
        </ul>
        <p v-if="artist.types.length === 0" class="text-muted mt-2">Aucun type associé à cet artiste.</p>
      </div>

      <div class="mt-4">
        <router-link to="/artists" class="btn btn-link">&larr; Retour à la liste des artistes</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import artistService from "@/services/artistService";  // Importer le service

export default {
  name: 'ArtistDetail',
  data() {
    return {
      artist: null,
      loading: true,
      error: false
    };
  },
  async mounted() {
    const artistId = this.$route.params.id;
    try {
      const res = await artistService.getById(artistId);  // Appeler le service
      this.artist = res.data;
    } catch (e) {
      this.error = true;
    } finally {
      this.loading = false;
    }
  },
  methods: {
    async deleteArtist() {
      if (confirm(`Voulez-vous vraiment supprimer l'artiste ${this.artist.firstname} ${this.artist.lastname}?`)) {
        try {
          await artistService.delete(this.artist.id);  // Appeler le service pour la suppression
          this.$router.push({name: 'ArtistList'});
        } catch (e) {
          alert("Une erreur est survenue lors de la suppression.");
        }
      }
    }
  }
};
</script>

<style scoped>
/* Ajoute ici des styles personnalisés si nécessaire */
</style>
