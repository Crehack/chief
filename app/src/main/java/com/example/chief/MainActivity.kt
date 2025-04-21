package com.example.chief

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.chief.ui.navigation.Screen
import com.example.chief.ui.screen.AccueilScreen
import com.example.chief.ui.screen.AjoutRecetteScreen
import com.example.chief.ui.screen.ListeRecettesScreen
import com.example.chief.ui.theme.ChiefTheme
import com.example.chief.ui.viewmodel.RecetteViewModel
import com.example.chief.ui.viewmodel.RecetteViewModelFactory

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
    val context = LocalContext.current
    val viewModel: RecetteViewModel = viewModel(factory = RecetteViewModelFactory(context))
    NavHost(navController = navController, startDestination = Screen.Accueil.route) {
        composable(Screen.Accueil.route) {
            AccueilScreen(onAjouterClicked = {
                navController.navigate(Screen.AjoutRecette.route)
            } , onNaviguerListe = {navController.navigate(Screen.ListeRecette.route)})
        }


        composable(Screen.AjoutRecette.route) {
            AjoutRecetteScreen(
                onBack = { navController.popBackStack() },
                viewModel = viewModel
            )
        }
        composable(Screen.ListeRecette.route) {
            LaunchedEffect(Unit) {
                viewModel.chargerRecettes()
            }
            ListeRecettesScreen(viewModel.recettes, onBack = { navController.popBackStack() })
        }

    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    ChiefApp()
}