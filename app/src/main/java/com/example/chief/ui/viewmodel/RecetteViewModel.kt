package com.example.chief.ui.viewmodel

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chief.data.model.Recette
import com.example.chief.data.repository.RecetteRepository
import kotlinx.coroutines.flow.MutableStateFlow

class RecetteViewModel(private val repository: RecetteRepository) : ViewModel() {
    private val _recettes = mutableStateListOf<Recette>()
    val recettes: List<Recette> = _recettes

    fun ajouterRecette(
        titre: String,
        categorie: String,
        instructions: String,
        ingredients: List<Triple<String, Float, String>>
    ) {
        val recette = Recette(
            titre = titre,
            categorie = categorie,
            instructions = instructions,
            ingredients = ingredients.map { (nom, qte, unite) ->
                com.example.chief.data.model.Ingredient(nom, qte, unite)
            }
        )
        repository.add(recette)
        _recettes.add(recette)
    }

    fun chargerRecettes() {
        _recettes.clear()
        _recettes.addAll(repository.getAll())
    }

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories = _categories



    init {
        chargerCategories()
    }

    fun chargerCategories() {
        val toutesLesRecettes = repository.getAll()
        val distinctes = toutesLesRecettes.map { it.categorie }.distinct()
        categories.value = distinctes
    }
    fun ajouterCategorieSiNouvelle(nouvelleCategorie: String) {
        val currentCategories = _categories.value
        if (nouvelleCategorie.isNotBlank() && !currentCategories.contains(nouvelleCategorie)) {
            _categories.value = currentCategories + nouvelleCategorie
        }
    }


}

class RecetteViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val repository = RecetteRepository(context)
        return RecetteViewModel(repository) as T
    }
}

