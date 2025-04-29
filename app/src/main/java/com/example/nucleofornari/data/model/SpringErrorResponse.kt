package com.example.nucleofornari.data.model

data class SpringErrorResponse(
    val timestamp: String?,
    val status: Int?,
    val error: String?,
    val message: String?,
    val path: String?
)