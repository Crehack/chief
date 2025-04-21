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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.chief.ui.viewmodel.RecetteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AjoutRecetteScreen(
    onBack: () -> Unit,
    viewModel: RecetteViewModel
) {
    // États principaux
    var titre by remember { mutableStateOf("") }
    var titreTouched by remember { mutableStateOf(false) }

    val categories by viewModel.categories.collectAsStateWithLifecycle()
    var expanded by remember { mutableStateOf(false) }
    var selectedCategorie by remember { mutableStateOf("") }
    var nouvelleCategorie by remember { mutableStateOf("") }

    var instructions by remember { mutableStateOf("") }
    var instructionsTouched by remember { mutableStateOf(false) }

    var nomIngredient by remember { mutableStateOf("") }
    var quantiteText by remember { mutableStateOf("") }
    var unite by remember { mutableStateOf("") }

    val ingredients = remember { mutableStateListOf<IngredientUI>() }

    val showNewCategorieField = selectedCategorie == "Autre…"
    val categorieFinale = if (showNewCategorieField) nouvelleCategorie else selectedCategorie

    val isIngredientValid = nomIngredient.isNotBlank() &&
            quantiteText.toFloatOrNull() != null &&
            unite.isNotBlank()

    val isRecetteValid = titre.isNotBlank() &&
            categorieFinale.isNotBlank() &&
            instructions.isNotBlank() &&
            ingredients.isNotEmpty()

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // En-tête avec bouton retour
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, "Retour", tint = MaterialTheme.colorScheme.surfaceTint)
            }
            Text("Nouvelle Recette", style = MaterialTheme.typography.headlineSmall)
        }

        // Titre
        OutlinedTextField(
            value = titre,
            onValueChange = {
                titre = it
                if (!titreTouched) titreTouched = true
            },
            label = { Text("Titre") },
            isError = titreTouched && titre.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )
        if (titreTouched && titre.isBlank()) {
            Text(
                "Le titre ne peut pas être vide",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Catégorie (dropdown)
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            OutlinedTextField(
                value = if (showNewCategorieField) nouvelleCategorie else selectedCategorie,
                onValueChange = {},
                readOnly = true,
                label = { Text("Catégorie") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded) },
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach {
                    DropdownMenuItem(
                        text = { Text(it) },
                        onClick = {
                            selectedCategorie = it
                            nouvelleCategorie = ""
                            expanded = false
                        }
                    )
                }
                DropdownMenuItem(
                    text = { Icon(Icons.Default.Add, contentDescription = "Ajouter") },
                    onClick = {
                        selectedCategorie = "Autre…"
                        expanded = false
                    }
                )
            }
        }

        if (showNewCategorieField) {
            OutlinedTextField(
                value = nouvelleCategorie,
                onValueChange = { nouvelleCategorie = it },
                label = { Text("Nouvelle catégorie") },
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Instructions
        OutlinedTextField(
            value = instructions,
            onValueChange = {
                instructions = it
                if (!instructionsTouched) instructionsTouched = true
            },
            label = { Text("Instructions") },
            isError = instructionsTouched && instructions.isBlank(),
            modifier = Modifier.fillMaxWidth()
        )
        if (instructionsTouched && instructions.isBlank()) {
            Text(
                "Les instructions ne peuvent pas être vides",
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall
            )
        }

        // Ingrédients
        Text("Ingrédients", style = MaterialTheme.typography.titleMedium)

        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = nomIngredient,
                onValueChange = { nomIngredient = it },
                label = { Text("Nom") },
                modifier = Modifier.weight(1f)
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
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    ingredients.add(IngredientUI(nomIngredient, quantiteText, unite))
                    nomIngredient = ""
                    quantiteText = ""
                    unite = ""
                },
                enabled = isIngredientValid
            ) {
                Icon(Icons.Default.Add, contentDescription = "Ajouter")
            }
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 200.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(ingredients) { ing ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = { ingredients.remove(ing) }) {
                        Icon(Icons.Default.Delete, "Supprimer", tint = MaterialTheme.colorScheme.surfaceTint)
                    }
                    Text("${ing.nom} : ${ing.quantiteText} ${ing.unite}")
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Bouton validation
        Button(
            onClick = {
                viewModel.ajouterCategorieSiNouvelle(categorieFinale)

                viewModel.ajouterRecette(
                    titre = titre,
                    categorie = categorieFinale,
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
