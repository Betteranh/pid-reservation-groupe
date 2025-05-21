# Application Web de Gestion de réservation de spectacles

## Contexte du Projet

Ce projet a été réalisé dans le cadre du cours Projet d'intégration de Développement du Bachelier en Informatique de
Gestion, orientation développement d'applications à l'**ICC**. L'application permet aux utilisateurs de consulter la
liste des spectacles, lire leur fiche détaillée, réserver des places et laisser des commentaires.

## Membre:

- Nguyen Duy Anh

## Fonctionnalités Principales

- **Affectation de troupe à un artiste en tant qu'admin**

## Technologies et Outils Utilisés

- **Backend :**
    - Langage : Java
    - Framework : Spring Boot
    - Environnement de développement : IntelliJ IDEA
- **Base de Données :**
    - MySQL via Wampserver
- **Frontend :**
    - HTML, CSS, Tailwind CSS

## Installation et Exécution

### Prérequis

- JDK 11 ou supérieur
- IntelliJ IDEA
- Wampserver (pour MySQL)
- Maven

### Instructions

1. **Cloner le dépôt :**
   ```bash
   git clone https://github.com/Betteranh/pid-reservation-groupe.git
2. **Ouvrir le projet :**
   Importez le projet dans IntelliJ IDEA.
3. **Configurer la base de données :**
   Modifiez le fichier application.properties (ou application.yml) pour définir les paramètres de connexion à votre
   instance MySQL.
4. **Lancer l'application :**
   Via IntelliJ IDEA en exécutant la classe principale contenant la méthode main.
   Ou via Maven avec la commande :
   ```bash
   mvn spring-boot:run
5. **Accéder à l'application :**

- Ouvrez http://localhost:8080 dans votre navigateur.
- https://pid-reservation.onrender.com/

6. **Front-end en cours (Vue.js) :**
   https://github.com/CyberAkr/pid-reservation-grp-front
7. **Se connecter en Admin**
   Login : bob & Password : 12345678
8. **Consulter**
   Localhost:8080/artistszip



