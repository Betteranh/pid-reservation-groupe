<template>
  <div class="max-w-6xl mx-auto px-6 py-12">
    <div v-if="show">
      <!-- Titre -->
      <h1 class="text-4xl font-extrabold text-gray-900 mb-8">{{ show.title }}</h1>

      <!-- Affiche & Infos Principales -->
      <div class="flex flex-col md:flex-row gap-10 mb-12 bg-white rounded-xl shadow p-6">
        <!-- Affiche à gauche -->
        <div class="w-full md:w-1/3 flex justify-center">
          <div v-if="show.posterUrl" class="aspect-[2/3] w-full bg-white rounded-lg shadow overflow-hidden">
            <img :src="`/images/${show.posterUrl}`" :alt="show.title" class="object-contain w-full h-full" />
          </div>
          <div v-else class="aspect-[2/3] w-full flex items-center justify-center bg-gray-100 text-gray-400 rounded-lg shadow">
            Pas d’image
          </div>
        </div>

        <!-- Détails à droite -->
        <div class="flex-1 space-y-5">
          <p v-if="show.location" class="text-gray-700 text-lg">
            <span class="font-semibold text-gray-900">📍 Lieu de création :</span>
            {{ show.location }}
          </p>

          <div v-if="show.description">
            <h2 class="text-xl font-bold text-gray-800 mb-1">📝 Description</h2>
            <p class="text-gray-700 leading-relaxed">{{ show.description }}</p>
          </div>

          <div class="flex flex-wrap items-center gap-3 mt-4">
            <span v-if="show.bookable" class="inline-block px-3 py-1 bg-green-100 text-green-800 rounded-full text-sm font-medium">Réservable</span>
            <span v-else class="inline-block px-3 py-1 bg-red-100 text-red-700 rounded-full text-sm font-medium">Non réservable</span>

            <span v-if="show.minPrice != null" class="inline-block px-3 py-1 bg-blue-100 text-blue-800 rounded-full text-sm font-medium">
        💶 {{ show.minPrice }} €
      </span>
            <span v-else class="italic text-gray-400 text-sm">Prix non défini</span>

            <div v-if="show.bookable" class="ml-auto">
              <a
                  :href="`http://localhost:8080/shows/${show.id}/reserve`"
                  class="bg-blue-600 text-white px-5 py-2 rounded-lg shadow hover:bg-blue-700 transition"
              >
                Réserver
              </a>
            </div>
          </div>
        </div>
      </div>


      <!-- Représentations -->
      <section class="mb-12">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">Représentations</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          <div v-for="rep in show.representations" :key="rep.id" class="p-4 rounded-lg shadow flex flex-col" :class="rep.availableSeats > 0 ? 'bg-green-50' : 'bg-gray-200 text-gray-500'">
            <p class="font-medium mb-2">{{ formatDate(rep.scheduled_at) }}</p>
            <p v-if="rep.availableSeats > 0" class="mt-auto font-semibold text-gray-800">{{ rep.availableSeats }} place(s) dispo</p>
            <p v-else-if="rep.availableSeats === 0" class="mt-auto italic">Complet</p>
            <p v-else class="mt-auto italic">Places inconnues</p>
          </div>
        </div>
      </section>

      <!-- Collaborateurs -->
      <section v-if="show.collaborateurs" class="mb-12">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">Collaborateurs</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6 text-gray-700 text-sm">
          <div v-if="show.collaborateurs.auteur">
            <h3 class="font-semibold mb-1">Auteur(s)</h3>
            <ul class="list-disc list-inside">
              <li v-for="a in show.collaborateurs.auteur" :key="a.id">{{ a.firstname }} {{ a.lastname }}</li>
            </ul>
          </div>
          <div v-if="show.collaborateurs['scénographe']">
            <h3 class="font-semibold mb-1">Metteur(s) en scène</h3>
            <ul class="list-disc list-inside">
              <li v-for="s in show.collaborateurs['scénographe']" :key="s.id">{{ s.firstname }} {{ s.lastname }}</li>
            </ul>
          </div>
          <div v-if="show.collaborateurs.comédien">
            <h3 class="font-semibold mb-1">Distribution</h3>
            <ul class="list-disc list-inside">
              <li v-for="c in show.collaborateurs.comédien" :key="c.id">{{ c.firstname }} {{ c.lastname }}</li>
            </ul>
          </div>
        </div>
      </section>

      <!-- Mots-clés -->
      <section class="mb-12">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">Mots-clés associés</h2>
        <div class="flex flex-wrap gap-2 mb-4">
          <span v-for="tag in show.tags" :key="tag" class="px-3 py-1 bg-yellow-100 text-yellow-800 rounded-full text-xs font-medium hover:bg-yellow-200">
            {{ tag }}
          </span>
        </div>
        <p v-if="show.tags.length === 0" class="italic text-gray-500 mb-4">Aucun mot-clé.</p>

        <!-- Formulaire d’ajout de mot-clé (ADMIN uniquement) -->
        <div v-if="roles.includes('ROLE_ADMIN')" class="mt-6">
          <form @submit.prevent="addTagToShow" class="flex items-center space-x-2">
            <select v-model="selectedTagId" required class="border rounded px-2 py-1 text-sm">
              <option disabled value="">Ajouter un mot-clé</option>
              <option v-for="tag in availableTags" :key="tag.id" :value="tag.id">
                {{ tag.tag }}
              </option>
            </select>
            <button type="submit" class="bg-green-600 text-white px-3 py-1 rounded-lg hover:bg-green-700 transition">
              Ajouter
            </button>
          </form>
        </div>
      </section>

      <!-- Vers le backend -->
      <div class="mb-4">
        <a
            :href="`/dev/shows/${show.id}`"
            target="_blank"
            class="inline-block bg-gray-800 text-white px-4 py-2 rounded hover:bg-gray-900 transition"
        >
           Commentaires
        </a>
      </div>

      <!-- Retour -->
      <div class="mt-8">
        <router-link to="/shows" class="text-blue-600 hover:underline">← Retour à la liste</router-link>
      </div>
    </div>
    <div v-else class="text-center text-gray-500 italic py-12">
      Chargement du spectacle...
    </div>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  name: 'ShowDetail',
  data() {
    return {
      show: null,
      availableTags: [],
      selectedTagId: '',
      isAuthenticated: false,
      roles: []
    };
  },
  async mounted() {
    const id = this.$route.params.id;
    await this.fetchShow(id);
    await this.fetchAvailableTags();
    await this.checkAuth();
  },
  methods: {
    async fetchShow(id) {
      try {
        const response = await api.get(`/shows/${id}`);
        this.show = response.data;
      } catch (error) {
        console.error('Erreur lors du chargement du spectacle :', error);
      }
    },
    async fetchAvailableTags() {
      try {
        const response = await api.get('/tags');
        this.availableTags = response.data;
      } catch (error) {
        console.error('Erreur lors du chargement des tags disponibles :', error);
      }
    },
    async checkAuth() {
      try {
        const response = await api.get('/auth/check');
        this.isAuthenticated = true;
        this.roles = response.data.roles || [];
      } catch {
        this.isAuthenticated = false;
        this.roles = [];
      }
    },
    async addTagToShow() {
      try {
        const formData = new FormData();
        formData.append('tagId', this.selectedTagId);

        await api.post(`/shows/${this.show.id}/tags`, formData);
        this.selectedTagId = '';
        await this.fetchShow(this.show.id); // Rafraîchir les tags
      } catch (error) {
        console.error("Erreur lors de l'ajout du tag :", error);
      }
    },
    formatDate(dateStr) {
      if (!dateStr) return 'Date inconnue';
      const d = new Date(dateStr);
      return isNaN(d)
          ? 'Date invalide'
          : d.toLocaleString('fr-BE', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
          });
    }
  }
};
</script>
