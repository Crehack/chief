package com.example.chief

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chief.data.model.Ingredient
import com.example.chief.data.model.Recette
import com.example.chief.ui.navigation.Screen
import com.example.chief.ui.screen.AccueilScreen
import com.example.chief.ui.screen.AjoutRecetteScreen
import com.example.chief.ui.screen.ListeRecettesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChiefApp()
        }
    }
}

@Composable
fun ChiefApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.Accueil.route) {
        composable(Screen.Accueil.route) {
            AccueilScreen(onAjouterClicked = {
                navController.navigate(Screen.AjoutRecette.route)
            } , onNaviguerListe = {navController.navigate(Screen.ListeRecette.route)})
        }
        composable(Screen.AjoutRecette.route) {
            AjoutRecetteScreen(onBack = {
                navController.popBackStack()
            })
        }
        composable(Screen.ListeRecette.route) {

            val recettes = listOf(
                Recette(1,"Pizza", "Plat", listOf(Ingredient("test" , 100 , "g")) ,"bla"),

            )
            ListeRecettesScreen(recettes, onBack = { navController.popBackStack() })
        }
    }
}
