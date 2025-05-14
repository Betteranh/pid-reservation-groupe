<template>
  <div class="space-y-16">

    <!-- Section Hero -->
    <section class="relative h-96 bg-cover bg-center rounded-lg" style="background-image: url('/images/spectacle-hero.jpg');">
      <div class="absolute inset-0 bg-gradient-to-b from-black/60 to-black/30 rounded-lg"></div>
      <div class="relative z-10 flex flex-col items-center justify-center h-full text-center px-4">
        <h1 class="text-5xl font-extrabold text-white mb-4">Vivez la magie du spectacle</h1>
        <p class="text-lg text-gray-200 mb-6">Réservez en ligne vos places pour les événements incontournables</p>
        <router-link
            to="/shows"
            class="inline-block bg-blue-500 hover:bg-blue-600 text-white font-medium px-8 py-3 rounded-full transition"
        >
          Découvrir les spectacles
        </router-link>
      </div>
    </section>

    <!-- Pourquoi nous choisir -->
    <section class="bg-gray-50 rounded-lg py-12">
      <h2 class="text-3xl font-bold text-gray-800 mb-8 text-center">Pourquoi réserver chez nous ?</h2>
      <div class="max-w-4xl mx-auto grid grid-cols-1 md:grid-cols-3 gap-8 px-4">
        <div class="text-center px-4">
          <div class="text-blue-500 text-5xl mb-4">🔒</div>
          <h3 class="text-xl font-semibold mb-2">Paiement sécurisé</h3>
          <p class="text-gray-600">Vos transactions sont protégées par les meilleurs standards bancaires.</p>
        </div>
        <div class="text-center px-4">
          <div class="text-blue-500 text-5xl mb-4">⚡</div>
          <h3 class="text-xl font-semibold mb-2">Réservation express</h3>
          <p class="text-gray-600">Trois clics suffisent pour confirmer vos places, où que vous soyez.</p>
        </div>
        <div class="text-center px-4">
          <div class="text-blue-500 text-5xl mb-4">✉️</div>
          <h3 class="text-xl font-semibold mb-2">Support 7j/7</h3>
          <p class="text-gray-600">Une question ? Notre équipe vous répond par chat et email.</p>
        </div>
      </div>
    </section>

    <!-- Newsletter -->
    <section class="bg-blue-500 rounded-lg py-12 text-white text-center px-4">
      <h2 class="text-3xl font-bold mb-4">Restez informé·e</h2>
      <p class="mb-6">Inscrivez-vous à notre newsletter pour recevoir les avant-premières et offres exclusives.</p>
      <form @submit.prevent="subscribeToNewsletter" class="max-w-md mx-auto flex flex-col sm:flex-row gap-4">
        <input
            type="email"
            v-model="email"
            required
            placeholder="Votre adresse email"
            class="flex-1 px-4 py-2 rounded-md text-gray-900 focus:outline-none"
        />
        <button type="submit" class="bg-white text-blue-600 font-semibold px-6 py-2 rounded-md hover:bg-gray-100 transition">
          S’abonner
        </button>
      </form>
    </section>

  </div>
</template>

<script>
export default {
  name: "AppHome",
  data() {
    return {
      email: ""
    };
  },
  methods: {
    async subscribeToNewsletter() {
      try {
        // À adapter selon ton backend
        await fetch('/newsletter/subscribe', {
          method: 'POST',
          headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
          body: new URLSearchParams({ email: this.email })
        });
        alert("Inscription réussie !");
        this.email = "";
      } catch (error) {
        console.error(error);
        alert("Erreur lors de l'inscription.");
      }
    }
  }
};
</script>

<style scoped>
/* Ajoutez ici vos styles personnalisés si besoin */
</style>
