<template>
  <div class="bg-gray-100">
    <!-- Insertion du header -->
    <AppHeader />

    <!-- Contenu principal : on ajoute un padding-top pour laisser la place à la navbar fixe -->
    <main class="pt-20 pb-12 max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
      <!-- Zone de notifications -->
      <div id="notification">
        <div v-if="successMessage" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded mb-4">
          <p>{{ successMessage }}</p>
        </div>
        <div v-if="errorMessage" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded mb-4">
          <p>{{ errorMessage }}</p>
        </div>
      </div>

      <!-- Contenu spécifique à la page -->
      <slot></slot> <!-- Le contenu sera injecté ici -->
    </main>

    <!-- Insertion du footer -->
    <AppFooter />
  </div>
</template>

<script>
import AppHeader from './AppHeader.vue'
import AppFooter from './AppFooter.vue'

export default {
  components: {
    AppHeader,
    AppFooter
  },
  data() {
    return {
      successMessage: null, // Exemple de message de succès
      errorMessage: null, // Exemple de message d'erreur
    }
  }
}
</script>

<style scoped>
/* Ajoutez des styles personnalisés si nécessaire */
</style>
