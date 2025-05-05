<!-- frontend/src/components/ShowList.vue -->
<template>
  <!-- Conteneur avec fond gris clair -->
  <div class="container mx-auto px-6 py-12 bg-gray-50 min-h-screen">
    <h1 class="text-4xl font-extrabold text-gray-900 mb-8 text-center">
      Liste des Spectacles
    </h1>
    <div class="container mt-5">
      <h1 class="text-primary">Hello Bootstrap 👋</h1>
      <button class="btn btn-success">Bouton test</button>
    </div>
    <!-- Grid des spectacles -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-8">
      <div
          v-for="show in shows"
          :key="show.id"
          class="card bg-white rounded-lg shadow-lg overflow-hidden"
      >
        <!-- Affiche du spectacle -->
        <img
            :src="getPosterUrl(show.posterUrl)"
            class="w-full h-64 object-cover"
            :alt="`Affiche de ${show.title}`"
        />

        <!-- Détails du spectacle -->
        <div class="p-6">
          <h5 class="text-xl font-bold text-gray-800 mb-2">{{ show.title }}</h5>
          <p class="text-gray-600 mb-4">
            Créé le : {{ new Date(show.createdAt).toLocaleDateString('fr-FR') }}
          </p>

          <!-- Badge de disponibilité -->
          <div class="flex items-center mt-4 mb-4">
            <span
                v-if="show.availableSeats > 0"
                class="px-3 py-1 bg-green-100 text-green-800 rounded-full text-sm font-medium"
            >
              {{ show.availableSeats }} place(s) disponible(s)
            </span>
            <span
                v-else
                class="px-3 py-1 bg-gray-200 text-gray-500 rounded-full text-sm font-medium"
            >
              Complet
            </span>
          </div>

          <!-- Bouton de réservation si possible -->
          <div v-if="show.bookable">
            <a
                :href="`/shows/${show.id}/reserve`"
                class="bg-blue-600 text-white px-5 py-2 rounded-lg shadow hover:bg-blue-700 transition"
            >
              Réserver
            </a>
          </div>
        </div>
      </div>
    </div>

    <!-- Message si aucun spectacle -->
    <div v-if="shows.length === 0" class="text-center text-gray-500 mt-12">
      Aucun spectacle disponible pour le moment.
    </div>
  </div>
</template>

<script>
export default {
  name: 'ShowList',
  data() {
    return {
      shows: []  // Liste des spectacles récupérée depuis l'API
    };
  },
  mounted() {
    this.fetchShows();  // Appel à l'API lors du montage du composant
  },
  methods: {
    // Fonction pour récupérer les spectacles via l'API
    async fetchShows() {
      try {
        const response = await fetch('/api/spectacles');
        if (!response.ok) {
          throw new Error('Erreur lors de la récupération des spectacles');
        }
        const data = await response.json();
        console.log('Données reçues depuis /api/spectacles :', data); // Debug
        this.shows = data;  // On enregistre les données dans `shows`
      } catch (error) {
        console.error(error);
      }
    },
    // Fonction pour obtenir l'URL de l'affiche (si elle est relative, elle est corrigée)
    getPosterUrl(posterUrl) {
      return posterUrl.startsWith('http') ? posterUrl : `/images/${posterUrl}`;
    }
  }
};
</script>

<style scoped>
/* Styles personnalisés pour le composant */
.card {
  transition: transform 0.3s ease-in-out;
}

.card:hover {
  transform: translateY(-5px);
}
</style>
