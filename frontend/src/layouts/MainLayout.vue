<template>
  <div class="d-flex flex-column min-vh-100">
    <Header />
    <main class="container py-5 mt-5">
      <slot />
    </main>
    <Footer />
  </div>
</template>

<script>
import Header from './AppHeader.vue'
import Footer from './AppFooter.vue'

export default {
  name: 'MainLayout',
  components: {
    Header,
    Footer
  }
}
</script>
