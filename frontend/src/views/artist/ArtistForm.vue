<template>
  <div class="container py-5">
    <h1 class="mb-4 text-center">{{ isEdit ? 'Modifier' : 'Ajouter' }} un artiste</h1>

    <form @submit.prevent="handleSubmit" class="bg-white border rounded shadow p-4">
      <div class="mb-3">
        <label class="form-label">Prénom</label>
        <input v-model="artist.firstname" type="text" class="form-control" required />
      </div>
      <div class="mb-3">
        <label class="form-label">Nom</label>
        <input v-model="artist.lastname" type="text" class="form-control" required />
      </div>
      <div class="d-flex gap-2">
        <button type="submit" class="btn btn-success">{{ isEdit ? 'Enregistrer' : 'Ajouter' }}</button>
        <router-link to="/artists" class="btn btn-secondary">Annuler</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import artistService from '@/services/artistService';

export default {
  name: 'ArtistForm',
  data() {
    return {
      artist: {
        firstname: '',
        lastname: ''
      },
      isEdit: false
    };
  },
  async mounted() {
    const id = this.$route.params.id;
    if (id) {
      this.isEdit = true;
      try {
        const res = await artistService.getById(id);
        this.artist = res.data;
      } catch (e) {
        alert('Erreur de chargement des données.');
      }
    }
  },
  methods: {
    async handleSubmit() {
      try {
        if (this.isEdit) {
          await artistService.update(this.artist.id, this.artist);
        } else {
          await artistService.create(this.artist);
        }
        this.$router.push({ name: 'ArtistList' });
      } catch (e) {
        alert('Erreur lors de l\'enregistrement.');
      }
    }
  }
};
</script>
