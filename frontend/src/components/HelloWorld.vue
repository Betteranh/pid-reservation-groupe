<template>
  <div class="hello">
    <h1>Liste des artistes</h1>
    <ul>
      <li v-for="artist in artists" :key="artist.id">
        {{ artist.name }}
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  data() {
    return {
      artists: []
    };
  },
  mounted() {
    fetch('/api/artists', {
      headers: {
        'Accept': 'application/json'
      }
    })
        .then(response => {
          if (!response.ok) {
            throw new Error('Erreur lors de la récupération des artistes');
          }
          return response.json(); // Assure-toi de récupérer du JSON
        })
        .then(data => {
          console.log(data);  // Affiche les données dans la console pour vérifier
          this.artists = data; // Récupère les artistes
        })
        .catch(error => {
          console.error("Erreur lors du chargement des artistes :", error);
        });
  }}
</script>
