package com.example.chief.data.model

data class Recette(
    val id: Int,
    val titre: String,
    val categorie: String,
    val ingredients: List<Ingredient>,
    val instructions: String

)
