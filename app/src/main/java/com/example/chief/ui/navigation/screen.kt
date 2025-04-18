package com.example.chief.ui.navigation

sealed class Screen(val route: String) {
    object Accueil : Screen("accueil")
    object AjoutRecette : Screen("ajout_recette")
    object ListeRecette  : Screen("liste_recette")
}
