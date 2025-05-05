<template>
  <div class="container mx-auto px-6 py-12 bg-gray-50 min-h-screen">
    <h1 class="text-4xl font-extrabold text-gray-900 mb-8 text-center">
      Liste des Artistes
    </h1>

    <div v-if="loading" class="text-center">Chargement…</div>
    <div v-else-if="artists.length === 0" class="text-center text-gray-500">
      Aucun artiste disponible.
    </div>
    <ul v-else class="space-y-4">
      <li v-for="artist in artists" :key="artist.id"
          class="p-4 bg-white rounded-lg shadow flex justify-between items-center">
        <span>{{ artist.firstname }} {{ artist.lastname }}</span>
        <router-link
            :to="{ name: 'ArtistDetail', params: { id: artist.id } }"
            class="text-blue-600 hover:underline"
        >
          Voir
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'ArtistList',
  data() {
    return {
      artists: [],
      loading: true,
      error: false
    }
  },
  async mounted() {
    try {
      const res = await fetch('/api/artists')
      if (!res.ok) throw new Error('Erreur API')
      this.artists = await res.json()
    } catch {
      this.error = true
    } finally {
      this.loading = false
    }
  }
}
</script>

<style scoped>
/* styles éventuels */
</style>
