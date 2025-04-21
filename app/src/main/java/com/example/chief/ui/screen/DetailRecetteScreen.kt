package com.example.chief.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.chief.ui.viewmodel.RecetteViewModel

@Composable
fun DetailRecetteScreen(
    titre: String,
    viewModel: RecetteViewModel,
    onBack: () -> Unit
) {
    val recette = viewModel.recettes.find { it.titre == titre }

    if (recette == null) {
        Text("Recette introuvable", modifier = Modifier.padding(16.dp))
        return
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Row {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Retour",
                        tint = MaterialTheme.colorScheme.surfaceTint
                    )
                }

                Text(
                    text = recette.titre,
                    style = MaterialTheme.typography.headlineLarge,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                        .padding(start = 8.dp)
                )
            }

        }

        item {
            Text(
                text = "📝 Ingrédients",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        items(recette.ingredients) { ing ->
            Text(
                text = "- ${ing.nom} : ${ing.quantite} ${ing.unite}",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = "📖 Instructions",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = recette.instructions,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}
