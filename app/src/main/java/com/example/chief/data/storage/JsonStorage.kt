package com.example.chief.data.storage

import android.content.Context
import com.example.chief.data.model.Recette
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.io.File


class JsonStorage(private val context: Context) {
    private val gson = Gson()
    private val fileName = "recettes.json"

    private fun getFile(): File = File(context.filesDir, fileName)

    fun sauvegarder(recettes: List<Recette>) {
        val json = gson.toJson(recettes)
        getFile().writeText(json)
    }

    fun charger(): List<Recette> {
        val file = getFile()
        if (!file.exists()) return emptyList()
        val type = object : TypeToken<List<Recette>>() {}.type
        return gson.fromJson(file.readText(), type)
    }
}