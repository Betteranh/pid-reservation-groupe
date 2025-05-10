<template>
  <div class="container py-5">
    <h2 class="mb-4 text-primary">
      {{ locality?.postalCode }} {{ locality?.locality }}
    </h2>

    <div>
      <h4 class="mb-3 text-secondary">Lieux de spectacle :</h4>

      <ul v-if="locality?.places?.length > 0" class="list-group mb-4">
        <li v-for="place in locality.places" :key="place" class="list-group-item">
          {{ place }}
        </li>
      </ul>

      <div v-else class="alert alert-warning">
        Aucun lieu de spectacle associé à cette localité.
      </div>
    </div>

    <router-link to="/localities" class="btn btn-outline-primary">
      &larr; Retour à la liste des localités
    </router-link>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import { useRoute } from 'vue-router'
import api from '@/services/axios' // ← utilise ton instance ici

const route = useRoute()
const locality = ref(null)

onMounted(async () => {
  try {
    const response = await api.get(`/localities/${route.params.id}`)
    locality.value = response.data
  } catch (error) {
    console.error('Erreur de chargement de la localité', error)
  }
})
</script>

