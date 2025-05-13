<template>
  <header class="bg-gray-800 fixed top-0 w-full z-50 shadow">
    <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <div class="flex items-center justify-between h-16">
        <div class="flex items-center">
          <router-link to="/" class="text-white font-bold text-xl">Réservation</router-link>
          <nav class="ml-8 space-x-4">
            <a href="/localities" class="text-gray-300 hover:text-white">Locations</a>
            <router-link to="/localities" class="text-gray-300 hover:text-white">Localités</router-link>
            <router-link to="/shows" class="text-gray-300 hover:text-white">Spectacles</router-link>
            <router-link to="/cart/view" class="text-gray-300 hover:text-white">🛒 Mon panier</router-link>

            <!-- ✅ Bouton Tags visible seulement pour les admins -->
            <router-link
                v-if="roles && roles.includes('ROLE_ADMIN')"
                to="/tags"
                class="text-yellow-300 hover:text-white"
            >
              Tags
            </router-link>
          </nav>
        </div>

        <!-- Authentification -->
        <div class="flex items-center space-x-4">
          <div v-if="!isAuthenticated">
            <a href="/login" class="text-gray-300 hover:text-white">Se connecter</a>
            <a href="/registration" class="text-gray-300 hover:text-white ml-2">S'inscrire</a>
          </div>
          <div v-else class="flex items-center space-x-3">
            <span class="text-gray-300">Bonjour, {{ username }}</span>
            <form method="POST" action="/logout">
              <button type="submit" class="text-blue-400 hover:text-white">Se déconnecter</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  </header>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      isAuthenticated: false,
      username: '',
      roles: []
    };
  },
  async mounted() {
    try {
      const response = await api.get('/auth/check');
      this.username = response.data.username;
      this.roles = Array.isArray(response.data.roles) ? response.data.roles : [];
      this.isAuthenticated = true;
      console.log('✅ Rôles récupérés :', this.roles);
    } catch {
      this.isAuthenticated = false;
      this.roles = [];
    }
  }
};
</script>

<style scoped>
/* Tu peux ajouter un fond ou une bordure pour tester visuellement */
</style>
