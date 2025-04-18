package com.example.chief.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccueilScreen(onAjouterClicked: () -> Unit , onNaviguerListe : () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenue sur Chief", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onAjouterClicked) {
            Text("Ajouter une recette")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = onNaviguerListe) {
            Text("Voir les recettes")
        }

    }
}
