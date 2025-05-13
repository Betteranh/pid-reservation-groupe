<template>
  <div>
    <!-- Section Hero -->
    <section class="bg-cover bg-center h-96 rounded-lg" :style="{ backgroundImage: 'url(\'/images/spectacle-hero.jpg\')' }">
      <div class="bg-gray-900 bg-opacity-50 h-full flex flex-col justify-center items-center">
        <h1 class="text-white text-4xl font-bold mb-4">Frontend</h1>
        <p class="text-gray-300 text-lg mb-6">Réservez vos places pour les spectacles les plus en vogue</p>
        <router-link
            to="/shows"
            class="bg-blue-500 hover:bg-blue-600 text-white px-6 py-3 rounded-full text-lg font-medium"
        >
          Découvrir les spectacles
        </router-link>
      </div>
    </section>

    <!-- Section Fonctionnalités -->
    <section class="mt-12">
      <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
        <!-- Réservation en ligne -->
        <div class="bg-white rounded-lg shadow-lg p-6 text-center">
          <div class="mb-4">
            <i class="fas fa-ticket-alt text-blue-500 text-4xl"></i>
          </div>
          <h3 class="text-xl font-semibold mb-2">Réservation en ligne</h3>
          <p class="text-gray-600">
            Accédez rapidement à vos réservations et achetez vos billets en quelques clics.
          </p>
        </div>
        <!-- Évènements à venir -->
        <div class="bg-white rounded-lg shadow-lg p-6 text-center">
          <div class="mb-4">
            <i class="fas fa-calendar-alt text-blue-500 text-4xl"></i>
          </div>
          <h3 class="text-xl font-semibold mb-2">Évènements à venir</h3>
          <p class="text-gray-600">
            Consultez notre calendrier pour ne rien manquer des prochains spectacles.
          </p>
        </div>
        <!-- Communauté -->
        <div class="bg-white rounded-lg shadow-lg p-6 text-center">
          <div class="mb-4">
            <i class="fas fa-users text-blue-500 text-4xl"></i>
          </div>
          <h3 class="text-xl font-semibold mb-2">Communauté</h3>
          <p class="text-gray-600">
            Rejoignez une communauté passionnée par la culture et les arts du spectacle.
          </p>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "AppHome"
};
</script>

<style scoped>
/* Vous pouvez ajouter ici des styles personnalisés si nécessaire */
</style>
