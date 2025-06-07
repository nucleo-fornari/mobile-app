package com.example.nucleofornari.util

import com.example.nucleofornari.domain.model.SpringErrorResponse
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody

object ErrorUtils {
    fun parseErrorMessage(errorBody: ResponseBody): String {
        return try {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBody.charStream(), com.example.nucleofornari.domain.model.SpringErrorResponse::class.java)
            errorResponse.text ?: "Erro desconhecido"
        } catch (ex: Exception) {
            "Erro desconhecido"
        }
    }
}