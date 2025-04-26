<template>
  <div class="container mt-5">
    <h1 class="text-center mb-4">Liste des Spectacles</h1>
    <div class="row">
      <div class="col-12 col-md-6 col-lg-4" v-for="show in shows" :key="show.title">
        <div class="card mb-4">
          <!-- Affiche l'image du spectacle -->
          <img :src="getPosterUrl(show.posterUrl)" class="card-img-top" alt="Affiche de {{ show.title }}" />
          <div class="card-body">
            <!-- Titre du spectacle -->
            <h5 class="card-title">{{ show.title }}</h5>
            <!-- Date de création -->
            <p class="card-text">Créé le : {{ new Date(show.createdAt).toLocaleDateString() }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      shows: [] // Liste vide qui sera remplie par les données de l'API
    };
  },
  mounted() {
    this.fetchShows(); // Appel de la méthode pour récupérer les spectacles dès que le composant est monté
  },
  methods: {
    async fetchShows() {
      try {
        const response = await fetch('http://localhost:8080/api/spectacles'); // Appel à l'API
        if (!response.ok) {
          throw new Error('Erreur lors de la récupération des spectacles');
        }
        this.shows = await response.json(); // Stockage des spectacles récupérés
      } catch (error) {
        console.error(error); // Gestion des erreurs
      }
    },
    getPosterUrl(posterUrl) {
      // Si l'URL est un lien distant, la laisser telle quelle
      if (posterUrl.startsWith('http')) {
        return posterUrl;
      }
      // Sinon, chercher dans le dossier images local
      return `/images/${posterUrl}`;
    }
  }
};
</script>

<style scoped>
/* Tu peux ajouter tes propres styles ici si nécessaire */
</style>
