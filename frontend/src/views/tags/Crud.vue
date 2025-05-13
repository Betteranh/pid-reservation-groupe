<template>
  <div class="max-w-4xl mx-auto py-10 px-4">
    <h1 class="text-3xl font-bold text-gray-800 mb-6">Gestion des Tags</h1>

    <!-- Formulaire d'ajout/modification -->
    <form @submit.prevent="handleSubmit" class="mb-8 flex gap-3">
      <input
          v-model="tagForm.tag"
          type="text"
          placeholder="Nom du tag"
          required
          class="border px-3 py-1 rounded w-full"
      />
      <button
          type="submit"
          class="bg-blue-600 text-white px-4 py-1 rounded hover:bg-blue-700"
      >
        {{ tagForm.id ? 'Modifier' : 'Ajouter' }}
      </button>
      <button
          v-if="tagForm.id"
          type="button"
          @click="resetForm"
          class="text-sm text-gray-500 underline"
      >
        Annuler
      </button>
    </form>

    <!-- Liste des tags -->
    <table class="w-full table-auto border-collapse">
      <thead>
      <tr class="bg-gray-100 text-left">
        <th class="px-4 py-2">ID</th>
        <th class="px-4 py-2">Tag</th>
        <th class="px-4 py-2 text-right">Actions</th>
      </tr>
      </thead>
      <tbody>
      <tr
          v-for="tag in tags"
          :key="tag.id"
          class="border-t hover:bg-gray-50"
      >
        <td class="px-4 py-2">{{ tag.id }}</td>
        <td class="px-4 py-2">{{ tag.tag }}</td>
        <td class="px-4 py-2 text-right space-x-2">
          <button @click="editTag(tag)" class="text-blue-600 hover:underline text-sm">
            Modifier
          </button>
          <button @click="deleteTag(tag.id)" class="text-red-600 hover:underline text-sm">
            Supprimer
          </button>
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import api from '@/services/api';

export default {
  name: 'TagManager',
  data() {
    return {
      tags: [],
      tagForm: {
        id: null,
        tag: ''
      }
    };
  },
  mounted() {
    this.fetchTags();
  },
  methods: {
    async fetchTags() {
      try {
        const response = await api.get('/tags');
        this.tags = response.data;
      } catch (error) {
        console.error('Erreur chargement tags:', error);
      }
    },
    async handleSubmit() {
      try {
        if (this.tagForm.id) {
          await api.put(`/tags/${this.tagForm.id}`, this.tagForm);
        } else {
          await api.post('/tags', this.tagForm);
        }
        this.resetForm();
        this.fetchTags();
      } catch (error) {
        console.error('Erreur sauvegarde tag:', error);
      }
    },
    editTag(tag) {
      this.tagForm = { ...tag };
    },
    async deleteTag(id) {
      if (!confirm('Confirmer la suppression ?')) return;
      try {
        await api.delete(`/tags/${id}`);
        this.fetchTags();
      } catch (error) {
        console.error('Erreur suppression tag:', error);
      }
    },
    resetForm() {
      this.tagForm = { id: null, tag: '' };
    }
  }
};
</script>

<style scoped>
input:focus {
  outline: none;
  border-color: #2563eb;
}
</style>
