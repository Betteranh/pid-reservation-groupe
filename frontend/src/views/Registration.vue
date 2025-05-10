<template>
  <div class="registration-container">
    <h2>Inscription</h2>
    <form @submit.prevent="handleRegistration">
      <div>
        <label for="login">Login :</label>
        <input v-model="login" id="login" type="text" required />
      </div>
      <div>
        <label for="password">Mot de passe :</label>
        <input v-model="password" id="password" type="password" required />
      </div>
      <div>
        <label for="email">Email :</label>
        <input v-model="email" id="email" type="email" required />
      </div>
      <div>
        <label for="firstname">Prénom :</label>
        <input v-model="firstname" id="firstname" type="text" required />
      </div>
      <div>
        <label for="lastname">Nom :</label>
        <input v-model="lastname" id="lastname" type="text" required />
      </div>
      <button type="submit">S'inscrire</button>
    </form>
    <div v-if="errorMessage">{{ errorMessage }}</div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      login: '',
      password: '',
      email: '',
      firstname: '',
      lastname: '',
      errorMessage: '',
    };
  },
  methods: {
    async handleRegistration() {
      try {
        const response = await axios.post('http://localhost:8080/registration', {
          login: this.login,
          password: this.password,
          email: this.email,
          firstname: this.firstname,
          lastname: this.lastname,
        });
        // Rediriger vers la page de connexion ou la page d'accueil
        this.$router.push({ name: 'Login' });
      } catch (error) {
        this.errorMessage = 'Erreur lors de l\'inscription !';
      }
    },
  },
};
</script>

<style scoped>
/* Styles de ta page d'inscription */
</style>
