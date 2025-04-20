package com.example.chief.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.chief.ui.viewmodel.RecetteViewModel


@Composable
fun AjoutRecetteScreen(onBack: () -> Unit, viewModel: RecetteViewModel) {
    var titre by remember { mutableStateOf("") }
    var titreTouched by remember { mutableStateOf(false) }

    var categorie by remember { mutableStateOf("") }
    var categorieTouched by remember { mutableStateOf(false) }

    var instructions by remember { mutableStateOf("") }
    var instructionsTouched by remember { mutableStateOf(false) }

    var nomIngredient by remember { mutableStateOf("") }
    var quantiteText by remember { mutableStateOf("") }
    var unite by remember { mutableStateOf("") }

    val ingredients = remember { mutableStateListOf<IngredientUI>() }



    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Retour",
                    tint = MaterialTheme.colorScheme.surfaceTint
                )

            }
            Text("Nouvelle Recette", style = MaterialTheme.typography.headlineSmall)
        }


        OutlinedTextField(
            value = titre,
            onValueChange = { titre = it
                if (!titreTouched) titreTouched = true },
            label = { Text("Titre") },
            isError = titreTouched && titre.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )

        if (titreTouched && titre.isBlank()) {
            Text(
                text = "Le titre ne peut pas être vide",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = categorie,
            onValueChange = { categorie = it
                if (!categorieTouched) categorieTouched = true},
            label = { Text("Catégorie") },
            modifier = Modifier.fillMaxWidth(),
            isError = categorieTouched && categorie.isBlank()
        )

        if (categorieTouched && categorie.isBlank()) {
            Text(
                text = "La categorie ne peut pas être vide",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        OutlinedTextField(
            value = instructions,
            onValueChange = { instructions = it
                if (!instructionsTouched)  instructionsTouched = true},
            label = { Text("Instructions") },
            modifier = Modifier.fillMaxWidth(),
            isError = instructionsTouched && instructions.isBlank()
        )

        if (instructionsTouched && instructions.isBlank()) {
            Text(
                text = "Les instructions ne peuvent pas être vides",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Text("Ingrédients", style = MaterialTheme.typography.titleMedium)

        // Ligne avec 3 champs
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            OutlinedTextField(
                value = nomIngredient,
                onValueChange = { nomIngredient = it },
                label = { Text("Nom") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
            OutlinedTextField(
                value = quantiteText,
                onValueChange = { quantiteText = it },
                label = { Text("Quantité") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
            OutlinedTextField(
                value = unite,
                onValueChange = { unite = it },
                label = { Text("Unité") },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Text)
            )
        }

        // Bouton ajouter
        val isIngredientValid = nomIngredient.isNotBlank()
                && quantiteText.toFloatOrNull() != null
                && unite.isNotBlank()
        Button(
            onClick = {
                val quantite = quantiteText.toFloatOrNull()
                if (nomIngredient.isNotBlank() && quantite != null && unite.isNotBlank()) {
                    ingredients.add(IngredientUI(nomIngredient, quantiteText, unite))
                    nomIngredient = ""
                    quantiteText = ""
                    unite = ""
                }
            }, enabled = isIngredientValid
        ) {
            Text("Ajouter l’ingrédient")
        }

        // Liste des ingrédients
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(ingredients) { ing ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { ingredients.remove(ing) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Supprimer",
                            tint = MaterialTheme.colorScheme.surfaceTint
                        )
                    }
                    Text(text = "${ing.nom} : ${ing.quantiteText} ${ing.unite}")

                }
            }
        }
        val isRecetteValid = titre.isNotBlank() &&
                categorie.isNotBlank() &&
                instructions.isNotBlank() &&
                ingredients.isNotEmpty()

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                viewModel.ajouterRecette(
                    titre = titre,
                    categorie = categorie,
                    instructions = instructions,
                    ingredients = ingredients.map {
                        Triple(it.nom, it.quantiteText.toFloat(), it.unite)
                    }
                )
                onBack()
            },
            enabled = isRecetteValid,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Ajouter la recette")
        }
    }
}



// Classe temporaire d’ingrédient pour l’UI
data class IngredientUI(
    val nom: String,
    val quantiteText: String,
    val unite: String
)
