package com.example.chief.data.repository

import android.content.Context
import com.example.chief.data.model.Recette
import com.example.chief.data.storage.JsonStorage

class RecetteRepository(context: Context) {
    private val storage = JsonStorage(context)

    fun getAll(): List<Recette> = storage.charger()

    fun add(recette: Recette) {
        val current = storage.charger().toMutableList()
        current.add(recette)
        storage.sauvegarder(current)
    }
}
