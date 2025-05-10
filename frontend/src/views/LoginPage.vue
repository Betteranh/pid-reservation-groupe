<template>
  <div class="login-container">
    <h2>Connexion</h2>
    <form @submit.prevent="handleLogin">
      <div>
        <label for="login">Nom d'utilisateur</label>
        <input type="text" id="login" v-model="login" required />
      </div>
      <div>
        <label for="password">Mot de passe</label>
        <input type="password" id="password" v-model="password" required />
      </div>
      <button type="submit">Se connecter</button>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>
// Import de l'instance axios configurée
import axios from '@/services/axios';  // Adapte le chemin si nécessaire

export default {
  name: 'LoginPage',
  data() {
    return {
      login: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async handleLogin() {
      console.log('Tentative de connexion...');
      try {
        // Envoi de la requête POST pour tenter de se connecter
        const response = await axios.post('/login', {
          login: this.login,
          password: this.password
        });

        // Log pour vérifier la réponse de l'API
        console.log('Réponse de l\'API login:', response);

        // Si la connexion réussit, redirige l'utilisateur vers la page d'accueil
        this.$router.push('/');
      } catch (error) {
        // Log pour afficher l'erreur
        console.error('Erreur de connexion:', error);
        this.errorMessage = error.response?.data || 'Erreur de connexion.';
      }
    }
  }
};
</script>
