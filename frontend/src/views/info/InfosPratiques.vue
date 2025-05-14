<template>
  <div class="space-y-10">

    <!-- Titre -->
    <header class="text-center">
      <h1 class="text-4xl font-extrabold text-gray-800">Infos pratiques</h1>
      <p class="mt-2 text-gray-600">Tout ce qu’il faut savoir avant de réserver</p>
    </header>

    <!-- Grille 2 colonnes sur md+, 1 sur mobile -->
    <div class="grid grid-cols-1 md:grid-cols-2 gap-8">

      <!-- Horaires & Lieu -->
      <section class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold mb-4 text-blue-600">🕒 Horaires &amp; Lieu</h2>
        <p class="text-gray-700 leading-relaxed">
          Les détails (date, heure, lieu) sont indiqués sur chaque page de spectacle.<br>
          <router-link to="/shows" class="text-blue-500 hover:underline">Voir tous les spectacles</router-link>
        </p>
      </section>

      <!-- Billetterie -->
      <section class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold mb-4 text-blue-600">🎟️ Billetterie</h2>
        <ul class="space-y-2 text-gray-700">
          <li class="flex items-start"><span class="mr-2 text-green-500">✔</span>Achat en ligne sécurisé</li>
          <li class="flex items-start"><span class="mr-2 text-green-500">✔</span>Retrait à la billetterie 1 h avant</li>
          <li class="flex items-start"><span class="mr-2 text-green-500">✔</span>Billets dématérialisés (QR code par e-mail)</li>
        </ul>
      </section>

      <!-- Paiement -->
      <section class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold mb-4 text-blue-600">💳 Modes de paiement</h2>
        <p class="text-gray-700 mb-3">Paiement via Stripe :</p>
        <ul class="space-y-2 text-gray-700">
          <li class="flex items-start"><span class="mr-2 text-green-500">✔</span>Visa, MasterCard, Bancontact</li>
          <li class="flex items-start"><span class="mr-2 text-green-500">✔</span>PayPal</li>
          <li class="flex items-start">
            <span class="mr-2 text-green-500">✔</span>Virement bancaire<br>
            <small class="text-gray-500">
              Envoyez la preuve à
              <a href="mailto:contact@spectacles.be" class="text-blue-500 hover:underline">contact@spectacles.be</a>
            </small>
          </li>
        </ul>
      </section>

      <!-- Conditions d'annulation -->
      <section class="bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold mb-4 text-blue-600">❌ Conditions d’annulation</h2>
        <ul class="space-y-2 text-gray-700">
          <li><strong>Jusqu’à 48 h avant :</strong> gratuit</li>
          <li><strong>Entre 48 h et 24 h :</strong> 50 % du montant</li>
          <li><strong>Moins de 24 h :</strong> aucune annulation</li>
        </ul>
      </section>

      <!-- Contact -->
      <section class="md:col-span-2 bg-white shadow rounded-lg p-6">
        <h2 class="text-2xl font-semibold mb-4 text-blue-600">📞 Contact &amp; FAQ</h2>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4 text-gray-700">
          <div>
            <p><strong>Téléphone :</strong> +32 2 123 45 67</p>
            <p><strong>Email :</strong>
              <a href="mailto:contact@spectacles.be" class="text-blue-500 hover:underline">
                contact@spectacles.be
              </a>
            </p>
          </div>
          <div>
            <p>Pour toutes questions, consultez notre
              <router-link to="/faq" class="text-blue-500 hover:underline">FAQ</router-link>.
            </p>
          </div>
        </div>
      </section>

    </div>
  </div>
</template>

<script>
export default {
  name: "InfosPratiques"
};
</script>

<style scoped>
/* Ajoutez ici vos styles personnalisés si nécessaire */
</style>
