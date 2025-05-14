<template>
  <div>
    <h1>Bienvenue sur l'application !</h1>

    <!-- Formulaire de connexion -->
    <form @submit.prevent="login">
      <div>
        <label for="login">Login :</label>
        <input v-model="loginInput" id="login" type="text" required />
      </div>
      <div>
        <label for="password">Mot de passe :</label>
        <input v-model="password" id="password" type="password" required />
      </div>
      <button type="submit">Se connecter</button>
      <p v-if="errorMessage" class="text-red-500">{{ errorMessage }}</p>
    </form>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  data() {
    return {
      loginInput: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    async login() {
      try {
        // Envoi du POST /login intercepté par Spring Security
        await api.post('/login', {
          login: this.loginInput,
          password: this.password
        })
            .then(() => {
              return api.get('/api/auth/check');
            })
            .then((me) => {
              this.username = me.data.username;
              this.isAuthenticated = true;
              this.showLoginForm = false;
            })
            .catch((error) => {
              if (error.response?.status === 403) {
                this.errorMessage = 'Accès refusé (403) — CSRF ou mauvais identifiants.';
              } else {
                this.errorMessage = 'Échec de la connexion.';
              }
            });


        // Session créée : aller sur /shows (redirection manuelle côté client)
        this.$router.push('/shows');
      } catch (err) {
        this.errorMessage = 'Échec de la connexion !';
      }
    }
  }
};
</script>

<style scoped>
form { max-width: 400px; margin: 2rem auto; }
button {
  margin-top: 1rem;
  padding: 0.5rem 1rem;
  background-color: #42b983;
  color: white;
  border: none;
  cursor: pointer;
}
button:hover { background-color: #365e02; }
</style>
