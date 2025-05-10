<template>
  <div class="container mx-auto py-8">
    <h1 class="text-2xl font-bold mb-6">Liste des représentations de spectacle</h1>
    <div v-if="representations.length === 0">
      <p class="text-gray-600">Aucune représentation disponible.</p>
    </div>
    <div v-else class="overflow-x-auto">
      <table class="min-w-full bg-white shadow-md rounded-lg overflow-hidden">
        <thead class="bg-gray-200">
        <tr>
          <th class="py-3 px-4 text-left">Spectacle</th>
          <th class="py-3 px-4 text-left">Lieu</th>
          <th class="py-3 px-4 text-left">Date</th>
          <th class="py-3 px-4 text-left">Heure</th>
          <th class="py-3 px-4"></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="representation in representations" :key="representation.id" class="border-b hover:bg-gray-50">
          <td class="py-3 px-4">{{ representation.showTitle }}</td>
          <td class="py-3 px-4">{{ representation.locationDesignation }}</td>
          <td class="py-3 px-4">{{ formatDate(representation.when, 'dd-MM-yyyy') }}</td>
          <td class="py-3 px-4">{{ formatDate(representation.when, 'HH:mm') }}</td>
          <td class="py-3 px-4">
            <router-link :to="'/representations/' + representation.id" class="text-blue-500 hover:text-blue-700">Voir</router-link>
          </td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      representations: [],
    };
  },
  methods: {
    async fetchRepresentations() {
      try {
        const response = await axios.get("/api/representations");
        this.representations = response.data;
      } catch (error) {
        console.error("Erreur lors de la récupération des représentations:", error);
      }
    },
    formatDate(date) {
      const options = {
        year: "numeric",
        month: "2-digit",
        day: "2-digit",
        hour: "2-digit",
        minute: "2-digit",
        hour12: false,
      };
      return new Date(date).toLocaleString("fr-FR", options).replace(",", "");
    },
  },
  mounted() {
    this.fetchRepresentations();
  },
};
</script>

<style scoped>
/* Styles spécifiques à cette vue */
</style>
