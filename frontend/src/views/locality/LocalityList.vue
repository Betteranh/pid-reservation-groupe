<template>
  <div class="container py-5">
    <h1 class="mb-4 text-primary">Liste des localités</h1>

    <div v-if="localities.length === 0" class="alert alert-info text-center">
      Aucune localité enregistrée.
    </div>

    <div v-else class="list-group shadow-sm">
      <a
          v-for="locality in localities"
          :key="locality.id"
          :href="`/localities/${locality.id}`"
          class="list-group-item list-group-item-action d-flex justify-content-between align-items-center"
      >
        <span class="fw-semibold">
          {{ locality.postalCode }} {{ locality.locality }}
        </span>
        <span class="text-primary">Voir &raquo;</span>
      </a>
    </div>
  </div>
</template>


<script setup>
import { ref, onMounted } from 'vue'
import api from '@/services/axios' // ← ton instance

const localities = ref([])

onMounted(async () => {
  try {
    const response = await api.get('/localities')
    localities.value = response.data
  } catch (error) {
    console.error('Erreur de chargement des localités', error)
  }
})
</script>

