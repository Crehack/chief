package com.example.chief.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.chief.data.model.Recette


@Composable
fun ListeRecettes(recettes: List<Recette> , navController: NavController) {
    LazyColumn {
        items(recettes) { recette ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                onClick = { navController.navigate("detailRecette/${recette.titre}") }
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = recette.titre, style = MaterialTheme.typography.titleLarge)
                    Text(text = "Catégorie : ${recette.categorie}")
                    Text(text = "Ingrédients : ${recette.ingredients.size}")
                }
            }
        }
    }
}

@Composable
fun ListeRecettesScreen(recettes: List<Recette>, navController: NavController, onBack: () -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {

        IconButton(onClick = onBack) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Retour",
                tint = MaterialTheme.colorScheme.surfaceTint
            )

        }
        ListeRecettes(recettes, navController)

    }
}
