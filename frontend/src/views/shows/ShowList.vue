<template>
  <div class="max-w-7xl mx-auto px-6 py-10">
    <!-- Titre -->
    <div class="mb-10">
      <h1 class="text-4xl font-extrabold text-gray-900">Liste des spectacles</h1>
    </div>

    <!-- Recherche par tag -->
    <form @submit.prevent="fetchShowsByTag" class="mb-6 flex gap-3 items-center">
      <label for="tag" class="text-gray-700">Rechercher par mot-clé :</label>
      <select v-model="selectedTag" id="tag" name="tag" class="border rounded px-3 py-1">
        <option value="">-- Tous les spectacles --</option>
        <option v-for="tag in availableTags" :key="tag" :value="tag">{{ tag }}</option>
      </select>
      <button type="submit" class="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700">
        Rechercher
      </button>
    </form>

    <!-- Liste des spectacles -->
    <div v-if="shows.length > 0" class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
      <div
          v-for="show in shows"
          :key="show.id"
          class="bg-white rounded-lg shadow-md hover:shadow-lg transition duration-300 overflow-hidden flex flex-col"
      >
        <!-- Affiche -->
        <div class="h-48 bg-gray-100 flex items-center justify-center">
          <img
              v-if="show.posterUrl"
              :src="`/images/${show.posterUrl}`"
              :alt="show.title"
              class="object-contain w-full h-full p-2"
          />
          <span v-else class="text-gray-400 italic text-sm">Pas d'image</span>
        </div>

        <!-- Infos -->
        <div class="p-4 flex-1 flex flex-col justify-between">
          <div>
            <h2 class="text-xl font-bold text-gray-800 mb-1">{{ show.title }}</h2>

            <div class="flex flex-wrap items-center gap-2 text-sm mb-3">
              <span
                  v-if="show.bookable"
                  class="inline-block px-2 py-1 bg-green-100 text-green-700 rounded-full text-xs font-medium"
              >
                Réservable
              </span>
              <span
                  v-else
                  class="inline-block px-2 py-1 bg-red-100 text-red-600 rounded-full text-xs font-medium"
              >
                Non réservable
              </span>

              <span
                  v-if="show.minPrice != null"
                  class="inline-block px-2 py-1 bg-blue-100 text-blue-700 rounded-full text-xs font-medium"
              >
                {{ show.minPrice }} €
              </span>
              <span v-else class="text-gray-400 italic text-xs">Prix non défini</span>

              <span
                  v-if="show.representationsCount > 0"
                  class="inline-block px-2 py-1 bg-indigo-100 text-indigo-700 rounded-full text-xs font-medium"
              >
                {{ show.representationsCount }} représentation(s)
              </span>
              <span
                  v-else
                  class="inline-block px-2 py-1 bg-gray-100 text-gray-500 rounded-full text-xs font-medium italic"
              >
                Aucune représentation
              </span>
            </div>
          </div>

          <!-- Voir -->
          <div class="mt-3">
            <router-link
                :to="`/shows/${show.id}`"
                class="inline-block text-sm text-blue-600 font-semibold hover:underline"
            >
              Voir les détails →
            </router-link>
          </div>
        </div>
      </div>
    </div>

    <!-- Aucun résultat -->
    <div
        v-else
        class="text-center text-gray-500 italic bg-white py-10 rounded shadow border mt-8 text-lg"
    >
      Aucun spectacle n’a encore été enregistré.
    </div>
  </div>
</template>

<script>
import api from '@/services/api'; // ✅ ton axios configuré

export default {
  name: 'ShowList',
  data() {
    return {
      shows: [],
      selectedTag: '',
      availableTags: []
    };
  },
  mounted() {
    this.fetchShows();
    this.fetchTags();
    const excludeTag = this.$route.query.excludeTag;
    if (excludeTag) {
      this.fetchShowsExcludingTag(excludeTag);
    } else {
      this.fetchShows();
    }
  },
  methods: {
    async fetchShows() {
      try {
        const response = await api.get('/shows');
        this.shows = response.data;
      } catch (error) {
        console.error('Erreur lors du chargement des spectacles :', error);
      }
    },
    async fetchTags() {
      try {
        const response = await api.get('/tags');
        this.availableTags = response.data.map(t => t.tag);
      } catch (error) {
        console.warn('Impossible de charger les tags disponibles.');
      }
    },
    async fetchShowsByTag() {
      if (!this.selectedTag) {
        return this.fetchShows();
      }
      try {
        const response = await api.get(`/shows?tag=${this.selectedTag}`);
        this.shows = response.data;
      } catch (error) {
        console.error('Erreur lors du filtrage :', error);
        this.shows = [];
      }
    },
    async fetchShowsExcludingTag(tag) {
      try {
        const response = await api.get(`/shows/exclude?tag=${tag}`);
        this.shows = response.data;
      } catch (error) {
        console.error('Erreur lors du filtrage exclu :', error);
        this.shows = [];
      }
    }
  }
};
</script>
