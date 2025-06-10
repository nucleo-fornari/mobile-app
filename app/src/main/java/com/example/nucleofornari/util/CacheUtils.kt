package com.example.nucleofornari.util

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object CacheUtils {
    const val PREF_NAME = "cache_prefs"
    val gson = Gson()

    fun <T> salvar(context: Context, chave: String, dado: T) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val json = gson.toJson(dado)
        prefs.edit()
            .putString(chave, json)
            .putLong("${chave}_timestamp", System.currentTimeMillis())
            .apply()
    }

    inline fun <reified T> ler(
        context: Context,
        chave: String,
        validadeMillis: Long
    ): T? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val timestamp = prefs.getLong("${chave}_timestamp", 0L)
        val agora = System.currentTimeMillis()
        if (agora - timestamp > validadeMillis) return null

        val json = prefs.getString(chave, null) ?: return null
        val tipo = object : TypeToken<T>() {}.type
        return gson.fromJson<T>(json, tipo)
    }

    fun remover(context: Context, chave: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit()
            .remove(chave)
            .remove("${chave}_timestamp")
            .apply()
    }
}

