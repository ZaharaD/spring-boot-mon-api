# 🚀 Ordexa - Système de Gestion Complet

## 📋 Description

Ordexa est un système de gestion moderne et complet pour la gestion des clients, produits, commandes et statistiques. L'application offre une interface utilisateur intuitive avec un design unifié et des fonctionnalités avancées.

## ✨ Fonctionnalités

### 🎯 **Gestion Complète**
- **Clients** : CRUD complet avec noms maliens authentiques
- **Produits** : Gestion des stocks et prix en temps réel
- **Commandes** : Système complet avec filtrage, export CSV et modals
- **Statistiques** : Graphiques interactifs et analyses détaillées
- **Paramètres** : Gestion des utilisateurs et configuration système

### 🎨 **Interface Moderne**
- Design unifié et responsive
- Animations fluides et transitions
- Interface adaptée mobile, tablette et desktop
- Système de notifications intégré

### 🔐 **Sécurité**
- Authentification JWT sécurisée
- Gestion des sessions
- Protection des routes

## 🚀 Installation et Démarrage

### Prérequis
- Python 3.x
- Navigateur web moderne (Chrome, Firefox, Safari, Edge)

### Installation
1. Clonez le repository
2. Naviguez vers le dossier du projet
3. Lancez le serveur local :
```bash
python -m http.server 8000
```

### Accès
1. Ouvrez votre navigateur
2. Allez à `http://localhost:8000`
3. Connectez-vous avec :
   - **Email :** `admin@ordexa.ml`
   - **Mot de passe :** `admin123`

## 📁 Structure du Projet

```
projet3/
├── index.html              # Page d'accueil
├── page1.html              # Page de connexion
├── page2.html              # Dashboard principal
├── client.html             # Gestion des clients
├── produits.html           # Gestion des produits
├── commandes.html          # Gestion des commandes
├── stats.html              # Statistiques
├── parametres.html         # Paramètres système
├── common.css              # Styles communs
├── page1.css               # Styles page connexion
├── page1.js                # JavaScript page connexion
├── pom.xml                 # Configuration Maven
└── src/                    # Backend Java
    └── main/
        └── java/
            └── com/
                └── ordexa/
                    ├── controller/    # Contrôleurs REST
                    ├── model/         # Modèles de données
                    ├── service/       # Services métier
                    ├── repository/    # Accès aux données
                    └── security/      # Configuration sécurité
```

## 🎯 Utilisation

### Dashboard
- Vue d'ensemble des statistiques
- Graphiques interactifs
- Dernières commandes et produits populaires
- Bouton d'actualisation

### Gestion des Clients
- Liste complète des clients
- Ajout, modification et suppression
- Filtrage et recherche
- Navigation fluide

### Gestion des Produits
- Catalogue complet
- Gestion des stocks
- Prix et descriptions
- Interface intuitive

### Gestion des Commandes
- Liste des commandes récentes
- Filtrage par statut et date
- Export des données en CSV
- Modals pour actions détaillées
- Calcul automatique des totaux

### Statistiques
- Graphiques détaillés
- Analyses des ventes
- Tendances mensuelles
- Visualisations interactives

### Paramètres
- Gestion des utilisateurs
- Changement de mot de passe
- Sauvegarde et restauration
- Configuration système

## 🔧 Configuration

### Variables CSS
L'application utilise des variables CSS pour un design cohérent :
```css
:root {
  --primary: #4f46e5;
  --primary-dark: #3730a3;
  --success: #16a34a;
  --danger: #dc2626;
  --warning: #f59e42;
  --info: #0ea5e9;
}
```

### Authentification
- Système JWT pour la sécurité
- Tokens avec expiration automatique
- Gestion des sessions locales

## 📱 Responsive Design

L'application s'adapte automatiquement à tous les écrans :
- **Desktop** : Interface complète avec sidebar
- **Tablette** : Interface adaptée avec menu hamburger
- **Mobile** : Interface optimisée pour petits écrans

## 🎨 Design System

- **Couleurs** : Palette cohérente avec bleu principal
- **Typographie** : Segoe UI, Arial, sans-serif
- **Animations** : Transitions fluides et feedback utilisateur
- **Icônes** : Font Awesome 6.4.0

## 🔒 Sécurité

- Authentification JWT sécurisée
- Validation des tokens
- Protection contre les accès non autorisés
- Nettoyage automatique des sessions expirées

## 📊 Données

L'application utilise des données statiques avec des noms maliens authentiques :
- Clients : Fanta Diarra, Moussa Keita, Aminata Traoré, etc.
- Produits : Riz local, Millet, Sorgho, etc.
- Commandes : Données réalistes avec statuts

## 🚀 Déploiement

### Serveur Local
```bash
python -m http.server 8000
```

### Serveur de Production
L'application peut être déployée sur n'importe quel serveur web supportant les fichiers statiques.

## 📞 Support

Pour toute question ou problème :
1. Vérifiez que tous les fichiers sont présents
2. Assurez-vous que Python est installé
3. Vérifiez que le port 8000 est disponible
4. Consultez la console du navigateur (F12) pour les erreurs

## 📄 Licence

Ce projet est développé pour usage interne et commercial.

---

**Ordexa - Système de Gestion Moderne et Professionnel** 🎯 