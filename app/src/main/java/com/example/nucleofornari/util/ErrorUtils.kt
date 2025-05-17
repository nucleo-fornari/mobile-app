package com.example.nucleofornari.util

import com.example.nucleofornari.data.model.SpringErrorResponse
import com.google.gson.Gson
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody

object ErrorUtils {
    fun parseErrorMessage(errorBody: ResponseBody): String {
        return try {
            val gson = Gson()
            val errorResponse = gson.fromJson(errorBody.charStream(), SpringErrorResponse::class.java)
            errorResponse.message ?: "Erro desconhecido"
        } catch (ex: Exception) {
            "Erro desconhecido"
        }
    }
}