<template>
  <div class="container py-5">
    <!-- Section Hero -->
    <section
        class="rounded-3 overflow-hidden mb-5"
        style="background-image: url('/images/spectacle-hero.jpg'); background-size: cover; background-position: center; height: 400px;"
    >
      <div class="h-100 d-flex flex-column justify-content-center align-items-center text-center bg-dark bg-opacity-50">
        <h1 class="text-white fw-bold display-4 mb-3">Bienvenue sur notre plateforme</h1>
        <p class="text-light fs-5 mb-4">Réservez vos places pour les spectacles les plus en vogue</p>
        <router-link to="/shows" class="btn btn-primary btn-lg">
          Découvrir les spectacles
        </router-link>
      </div>
    </section>

    <!-- Section Fonctionnalités -->
    <section class="mt-5">
      <div class="row g-4">
        <!-- Réservation en ligne -->
        <div class="col-md-4">
          <div class="bg-white rounded shadow p-4 text-center h-100">
            <div class="mb-3">
              <i class="fas fa-ticket-alt text-primary fs-1"></i>
            </div>
            <h3 class="h5 mb-2">Réservation en ligne</h3>
            <p>Accédez rapidement à vos réservations et achetez vos billets en quelques clics.</p>
          </div>
        </div>

        <!-- Évènements à venir -->
        <div class="col-md-4">
          <div class="bg-white rounded shadow p-4 text-center h-100">
            <div class="mb-3">
              <i class="fas fa-calendar-alt text-primary fs-1"></i>
            </div>
            <h3 class="h5 mb-2">Évènements à venir</h3>
            <p>Consultez notre calendrier pour ne rien manquer des prochains spectacles.</p>
          </div>
        </div>

        <!-- Communauté -->
        <div class="col-md-4">
          <div class="bg-white rounded shadow p-4 text-center h-100">
            <div class="mb-3">
              <i class="fas fa-users text-primary fs-1"></i>
            </div>
            <h3 class="h5 mb-2">Communauté</h3>
            <p>Rejoignez une communauté passionnée par la culture et les arts du spectacle.</p>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HomePage'
}
</script>

<style scoped>
/* Styles personnalisés si nécessaires */
</style>
