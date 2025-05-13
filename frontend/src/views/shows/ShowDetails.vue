<template>
  <div class="max-w-6xl mx-auto px-6 py-12">
    <div v-if="show">
      <!-- Titre -->
      <h1 class="text-4xl font-extrabold text-gray-900 mb-8">{{ show.title }}</h1>

      <!-- Affiche & Infos Principales -->
      <div class="grid grid-cols-1 md:grid-cols-3 gap-8 mb-12">
        <!-- Affiche -->
        <div>
          <div v-if="show.posterUrl" class="aspect-[2/3] bg-white rounded-lg shadow overflow-hidden">
            <img :src="`/images/${show.posterUrl}`" :alt="show.title" class="object-contain w-full h-full" />
          </div>
          <div v-else class="w-full aspect-[2/3] flex items-center justify-center bg-gray-100 text-gray-400 rounded-lg shadow">
            Pas d’image
          </div>
        </div>

        <!-- Détails -->
        <div class="md:col-span-2 space-y-4">
          <p v-if="show.location">
            <span class="font-semibold text-gray-800">Lieu de création :</span>
            <span class="text-gray-700">{{ show.location }}</span>
          </p>

          <div v-if="show.description" class="text-gray-700 leading-relaxed">
            <h2 class="font-semibold text-gray-800 mb-1">Description</h2>
            <p>{{ show.description }}</p>
          </div>

          <div class="flex flex-wrap items-center gap-3">
            <span v-if="show.bookable" class="inline-block px-3 py-1 bg-green-100 text-green-800 rounded-full text-sm font-medium">Réservable</span>
            <span v-else class="inline-block px-3 py-1 bg-red-100 text-red-700 rounded-full text-sm font-medium">Non réservable</span>

            <span v-if="show.minPrice != null" class="inline-block px-3 py-1 bg-blue-100 text-blue-800 rounded-full text-sm font-medium">{{ show.minPrice }} €</span>
            <span v-else class="italic text-gray-400 text-sm">Prix non défini</span>

            <div v-if="show.bookable" class="ml-auto">
              <button class="bg-blue-600 text-white px-5 py-2 rounded-lg shadow hover:bg-blue-700 transition">Réserver</button>
            </div>
          </div>
        </div>
      </div>

      <!-- Représentations -->
      <section class="mb-12">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">Représentations</h2>
        <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
          <div v-for="rep in show.representations" :key="rep.id" class="p-4 rounded-lg shadow flex flex-col" :class="rep.availableSeats > 0 ? 'bg-green-50' : 'bg-gray-200 text-gray-500'">
            <p class="font-medium mb-2">{{ formatDate(rep.when) }}</p>
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
          <span v-for="tag in show.tags" :key="tag" class="px-3 py-1 bg-yellow-100 text-yellow-800 rounded-full text-xs font-medium hover:bg-yellow-200">{{ tag }}</span>
        </div>
        <p v-if="show.tags.length === 0" class="italic text-gray-500 mb-4">Aucun mot-clé.</p>
      </section>
      <!-- Voir les spectacles sans ce mot-clé -->
      <div v-if="show.tags && show.tags.length > 0" class="mt-4">
        <p class="italic text-sm text-gray-600 mb-2">Voir les spectacles sans ce mot-clé :</p>
        <div class="flex flex-wrap gap-2">
          <router-link
              v-for="tag in show.tags"
              :key="'exclude-' + tag"
              :to="`/shows?excludeTag=${tag}`"
              class="px-3 py-1 bg-red-100 text-red-700 rounded-full text-xs font-medium hover:bg-red-200"
          >
            Sans {{ tag }}
          </router-link>
        </div>
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
      show: null
    };
  },
  mounted() {
    this.fetchShow();
  },
  methods: {
    async fetchShow() {
      try {
        const id = this.$route.params.id;
        const response = await api.get(`/shows/${id}`);
        this.show = response.data;
      } catch (error) {
        console.error('Erreur lors du chargement du spectacle :', error);
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
