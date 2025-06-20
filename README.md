# Ordexa - Backend Spring Boot

Ce projet est le backend complet de l'application Ordexa (gestion clients, produits, commandes, utilisateurs, stats, paramètres) en **Spring Boot**.

## Prérequis
- Java 17 ou plus
- Maven 3.8+

## Lancement rapide
```bash
# Clonez le repo
mvn clean install
mvn spring-boot:run
```
L'API démarre sur [http://localhost:8080](http://localhost:8080)

## Endpoints principaux
- `POST   /api/auth/login`         : Authentification (JWT)
- `GET    /api/clients`            : Liste/recherche clients
- `POST   /api/clients`            : Ajouter un client
- `GET    /api/produits`           : Liste produits
- `POST   /api/produits`           : Ajouter un produit
- `GET    /api/commandes`          : Liste commandes
- `POST   /api/commandes`          : Ajouter une commande
- `GET    /api/stats`              : Statistiques dashboard
- `GET    /api/parametres`         : Infos boutique
- ... (CRUD complet sur chaque entité)

## Swagger / Documentation API
- Accès : [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Testez tous les endpoints, authentifiez-vous, etc.

## Base de données
- Par défaut : **H2 en mémoire** (accès console : [http://localhost:8080/h2-console](http://localhost:8080/h2-console), user: `sa`, pass: vide)
- Pour PostgreSQL/MySQL : modifiez `application.properties`

## Authentification
- JWT sécurisé (Spring Security)
- Utilisateur admin par défaut :
  - email : `admin@ordexa.com`
  - mot de passe : `admin123`
- Changez le mot de passe dès la première connexion !

## Brancher le front Ordexa
- Activez CORS sur le backend (déjà configuré pour localhost)
- Utilisez les endpoints REST depuis vos pages HTML/JS
- Exemples d'intégration dans le README du front

## Support
Pour toute question ou bug, ouvrez une issue ou contactez l'équipe Ordexa. 