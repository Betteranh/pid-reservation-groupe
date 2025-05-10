<template>
  <Header class="bg-dark text-white py-3 fixed-top shadow">
    <div class="container d-flex justify-content-between align-items-center">
      <div class="d-flex align-items-center gap-4">
        <router-link to="/" class="text-white fs-4 fw-bold text-decoration-none">Réservation</router-link>

        <nav class="d-none d-md-flex gap-3">
          <router-link to="/" class="text-white nav-link">Accueil</router-link>
          <router-link to="/spectacles" class="text-white nav-link">Spectacles</router-link>
          <router-link to="/artists" class="text-white nav-link">Artistes</router-link>
        </nav>
      </div>

      <div>
        <div v-if="!isAuthenticated" class="text-white">
          <router-link to="/login" class="text-white">Se connecter</router-link>
          <span class="mx-1">|</span>
          <router-link to="/registration" class="text-white">S'inscrire</router-link>
        </div>
        <div v-else class="text-white d-flex align-items-center gap-2">
          <span>Bonjour, {{ username }}</span>
          <button @click="logout" class="text-white hover:text-red-400">Se déconnecter</button>
        </div>
      </div>
    </div>
  </Header>
</template>

<script>
import axios from 'axios'; // Import axios pour gérer les appels API

export default {
  name: "AppHeader",
  data() {
    return {
      isAuthenticated: false,
      username: ''
    };
  },
  created() {
    this.checkAuthentication(); // Vérifie si l'utilisateur est authentifié dès le chargement du composant
  },
  methods: {
    // Vérification de l'authentification de l'utilisateur
    checkAuthentication() {
      axios.get('http://localhost:8080/api/auth/check', { withCredentials: true })
          .then(response => {
            this.isAuthenticated = true;
            this.username = response.data.username; // Récupère le nom de l'utilisateur depuis l'API
          })
          .catch(() => {
            this.isAuthenticated = false;
            this.username = '';
          });
    },
    async logout() {
      try {
        // Déconnexion de l'utilisateur via l'API
        await axios.post('http://localhost:8080/api/logout', null, { withCredentials: true });
        this.isAuthenticated = false; // Réinitialiser l'état d'authentification
        this.username = '';
        this.$router.push('/login'); // Redirige l'utilisateur vers la page de login
      } catch (e) {
        console.error(e);
      }
    }
  }
}
</script>
