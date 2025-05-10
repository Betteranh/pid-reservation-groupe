<template>
  <div class="container mx-auto py-8" v-if="representation">
    <h1 class="text-2xl font-bold">{{ representation.showTitle }}</h1>
    <p class="text-gray-600">{{ representation.locationDesignation }}</p>
    <p class="text-gray-600">{{ formatDate(representation.when, 'dd-MM-yyyy HH:mm') }}</p>
    <p>{{ representation.description }}</p>
    <router-link to="/representations" class="text-blue-500 hover:text-blue-700">Retour à la liste</router-link>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      representation: null,
    };
  },
  methods: {
    async fetchRepresentation() {
      try {
        const response = await axios.get(`/api/representations/${this.$route.params.id}`);
        this.representation = response.data;
      } catch (error) {
        console.error("Erreur lors de la récupération des détails de la représentation:", error);
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
    this.fetchRepresentation();
  },
};
</script>

<style scoped>
/* Styles spécifiques à cette vue */
</style>
