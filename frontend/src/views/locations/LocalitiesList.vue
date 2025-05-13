<template>
  <div class="max-w-4xl mx-auto px-6 py-8">
    <h1 class="text-2xl font-semibold text-gray-800 mb-6">Liste des localités</h1>

    <div v-if="localities.length === 0" class="text-gray-500 italic text-center bg-white border rounded shadow py-6">
      Aucune localité enregistrée.
    </div>

    <ul v-else class="bg-white shadow border rounded divide-y divide-gray-200">
      <li v-for="locality in paginatedLocalities" :key="locality.id" class="px-4 py-3 flex justify-between items-center hover:bg-gray-50">
        <span class="text-gray-700 font-medium">
          {{ locality.postalCode }} {{ locality.locality }}
        </span>
        <router-link :to="`/localities/${locality.id}`" class="text-blue-600 text-sm hover:underline">Voir</router-link>
      </li>
    </ul>

    <!-- Pagination controls -->
    <div class="mt-6 flex justify-center">
      <button @click="changePage(currentPage - 1)" :disabled="currentPage === 0" class="px-4 py-2 bg-gray-300 text-gray-800 rounded-md hover:bg-gray-400">
        Précédent
      </button>
      <span class="mx-4 text-lg">{{ currentPage + 1 }} / {{ totalPages }}</span>
      <button @click="changePage(currentPage + 1)" :disabled="currentPage === totalPages - 1" class="px-4 py-2 bg-gray-300 text-gray-800 rounded-md hover:bg-gray-400">
        Suivant
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'LocalitiesList',
  data() {
    return {
      localities: [],  // Liste complète des localités
      currentPage: 0,  // Page actuelle
      itemsPerPage: 10,  // Nombre d'éléments par page
    };
  },
  computed: {
    // Calculer les localités pour la page actuelle
    paginatedLocalities() {
      const start = this.currentPage * this.itemsPerPage;
      const end = start + this.itemsPerPage;
      return this.localities.slice(start, end);
    },
    // Calculer le nombre total de pages
    totalPages() {
      return Math.ceil(this.localities.length / this.itemsPerPage);
    }
  },
  async mounted() {
    try {
      const response = await this.$http.get('/localities');
      console.log("✅ Données reçues :", response.data);
      this.localities = response.data;
    } catch (error) {
      console.error("Erreur lors du chargement des localités :", error);
    }
  },
  methods: {
    // Fonction pour changer de page
    changePage(page) {
      if (page >= 0 && page < this.totalPages) {
        this.currentPage = page;
      }
    }
  }
}
</script>

<style scoped>
/* Styles personnalisés pour la pagination */
button:disabled {
  background-color: #e0e0e0;
  cursor: not-allowed;
}
</style>
