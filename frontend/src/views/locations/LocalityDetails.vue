<template>
  <div class="max-w-3xl mx-auto px-6 py-8">
    <div v-if="locality">
      <h1 class="text-2xl font-semibold text-gray-800 mb-4">
        {{ locality.postalCode }} {{ locality.locality }}
      </h1>

      <div>
        <h2 class="text-lg font-medium text-gray-700 mb-2">Lieux de spectacle :</h2>

        <ul v-if="locality.places && locality.places.length > 0" class="list-disc list-inside text-gray-600 space-y-1">
          <li v-for="place in locality.places" :key="place">
            {{ place }}
          </li>
        </ul>

        <p v-else class="text-gray-500 italic">Aucun lieu de spectacle associé à cette localité.</p>
      </div>

      <div class="mt-6">
        <router-link to="/localities" class="text-sm text-blue-600 hover:underline">
          &larr; Retour à la liste des localités
        </router-link>
      </div>
    </div>
    <div v-else class="text-gray-500 italic text-center">
      Chargement en cours...
    </div>
  </div>
</template>

<script>
export default {
  name: 'LocalityDetails',
  data() {
    return {
      locality: null,
    };
  },
  async mounted() {
    try {
      const id = this.$route.params.id;
      const response = await this.$http.get(`/localities/${id}`);
      this.locality = response.data;
    } catch (error) {
      console.error("Erreur lors du chargement de la localité :", error);
    }
  }
}
</script>
