package com.example.chief

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.chief.data.model.Ingredient
import com.example.chief.data.model.Recette
import com.example.chief.ui.screen.ListeRecettes
import com.example.chief.ui.theme.ChiefTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val recettesTest = listOf(
                Recette(
                    id = 1,
                    titre = "Pâtes",
                    categorie = "Italien",
                    ingredients = listOf(
                        Ingredient("Pâtes", 200.0, "g")
                    ),
                    instructions = "Faire cuire"
                )

            )
            ListeRecettes(recettes = recettesTest)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val recettesTest = listOf(
        Recette(
            id = 1,
            titre = "Pâtes",
            categorie = "Italien",
            ingredients = listOf(
                Ingredient("Pâtes", 200.0, "g")
            ),
            instructions = "Faire cuire"
        )

    )
    ChiefTheme {
        ListeRecettes(recettes = recettesTest)
    }
}