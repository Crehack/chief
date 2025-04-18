package com.example.chief.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import com.example.chief.ui.navigation.Screen


@Composable
fun AjoutRecetteScreen(onBack : () -> Unit) {
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
        // Bouton final (ajouter la recette)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                // Logique d’enregistrement ici plus tard
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
