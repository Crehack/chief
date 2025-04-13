package com.example.chief.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType



data class IngredientUI(
    var nom: String,
    var quantiteText: String,
    var unite: String
)


@Composable
fun AjoutRecetteScreen() {
    var titre by remember {mutableStateOf("") }
    var categorie by remember { mutableStateOf("") }
    var instructions by remember { mutableStateOf("") }

    val ingredients = remember { mutableStateListOf<IngredientUI>() }


    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(text = "Ajouter une recette", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = titre,
            onValueChange = { titre = it },
            label = { Text("Titre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = categorie,
            onValueChange = { categorie = it },
            label = { Text("Catégorie") },
            modifier = Modifier.fillMaxWidth()
        )

        Text(text = "Ingrédients", style = MaterialTheme.typography.titleMedium)

        ingredients.forEachIndexed { index, ingredient ->
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = ingredient.nom,
                    onValueChange = { newNom -> ingredients[index] = ingredient.copy(nom = newNom) },
                    label = { Text("Nom") },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = ingredient.quantiteText,
                    onValueChange = { newQte -> ingredients[index] = ingredient.copy(quantiteText = newQte) },
                    label = { Text("Quantité") },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    )
                )
                OutlinedTextField(
                    value = ingredient.unite,
                    onValueChange = { newUnite -> ingredients[index] = ingredient.copy(unite = newUnite) },
                    label = { Text("Unité") },
                    modifier = Modifier.weight(1f)
                )
            }
        }

    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {
            ingredients.add(IngredientUI("", "", ""))
        }) {
            Text("Ajouter un ingrédient")
        }


        Button(
            onClick = {
                println("Titre : $titre")
                println("Catégorie : $categorie")
                println("Instructions : $instructions")
                println("Ingrédients :")

                ingredients.forEach { ingredient ->
                    val quantite = ingredient.quantiteText.toFloatOrNull()
                    if (quantite != null) {
                        println("- ${ingredient.nom} : $quantite ${ingredient.unite}")
                    } else {
                        println("- ${ingredient.nom} : ERREUR DE QUANTITÉ (${ingredient.quantiteText})")
                    }
                }
            }
        ) {
            Text("Ajouter")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AjoutRecetteScreenPreview() {
    AjoutRecetteScreen()
}