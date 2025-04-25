# 📱 Chief - Application de gestion de recettes

**Chief** est une application Android développée en **Kotlin** avec **Jetpack Compose**.  
Elle permet de gérer des recettes de cuisine localement, de manière simple, rapide et intuitive.

---

## ✨ Fonctionnalités

-  **Accueil** : point d’entrée avec navigation simple
-  **Ajout de recette** :
  - Titre
  - Catégorie (auto-remplie si déjà utilisée)
  - Liste d’ingrédients (nom, quantité, unité)
  - Instructions
-  **Liste des recettes** : affichage sous forme de cartes cliquables
-  **Détail d’une recette** : ingrédients et instructions visibles en un clic
-  **Sauvegarde locale** :
  - Stockage dans un fichier **JSON** dans le stockage interne
  - Pas de base de données ou injection de dépendances

---

##  Architecture

- **Jetpack Compose** pour toute l’UI
- **Navigation Compose** pour la navigation entre écrans
- **ViewModel** pour la logique métier et la persistance
- **Stockage local** en JSON dans le `filesDir`
- **Aucune dépendance externe lourde** (pas de Room, pas de Hilt)

---

##  Structure du projet

```
com.example.chief/
│
├── MainActivity.kt               # Point d'entrée de l'app
│
├── data/
│   └── model/
│       └── Recette.kt           # Modèle de données Recette et Ingrédient
│
├── ui/
│   ├── screen/                  # Tous les écrans composables
│   ├── navigation/             # Routes de navigation
│   └── theme/                  # Thème Material 3
│
└── ui/
    └── viewmodel/
        └── RecetteViewModel.kt # Logique de chargement/sauvegarde JSON
```

---

##  Lancer l'application

1. Cloner le dépôt
2. Ouvrir dans **Android Studio**
3. Lancer l'application sur un **émulateur** ou **appareil physique**

> Nécessite Android Studio Flamingo+ avec Kotlin et Compose configurés

---

## 📌 TODO à venir

-  Ajout / Suppression d’ingrédients dynamiques ✔️
-  Navigation et écran de détail ✔️
- ⏳ Edition d'une recette existante
- ⏳ Tri et filtre des recettes
- ⏳ Synchronisation distante (Git, Firebase…)

---

## 🛠️ Dépendances principales

- `androidx.compose.*`
- `androidx.lifecycle.viewmodel.compose`
- `androidx.navigation:navigation-compose`

---

##  Licence

Projet personnel réalisé dans un cadre d’apprentissage.  
Libre à vous de vous en inspirer ou le modifier.

---
