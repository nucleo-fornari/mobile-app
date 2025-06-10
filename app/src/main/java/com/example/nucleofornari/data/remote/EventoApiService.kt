package com.example.nucleofornari.data.remote

import com.example.nucleofornari.data.network.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface EventoApiService {

    @POST("eventos")
    suspend fun criarEvento(@Body evento: com.example.nucleofornari.domain.model.evento.EventoCriacaoReqDto): com.example.nucleofornari.domain.model.evento.EventoRespostaDto

    @GET("eventos/sala/{id}")
    suspend fun listarEventosPorSala(@Path("id") id: Int): List<com.example.nucleofornari.domain.model.evento.EventoRespostaDto>

    @GET("eventos")
    suspend fun listarEventos(): List<com.example.nucleofornari.domain.model.evento.EventoRespostaDto>

    @GET("eventos/publicacoes")
    suspend fun listarPublicacoes(): List<com.example.nucleofornari.domain.model.evento.EventoRespostaDto>

    @GET("eventos/publicacoes/usuario/{id}")
    suspend fun listarPublicacoesPorUsuario(@Path("id") id: Int): List<com.example.nucleofornari.domain.model.evento.EventoRespostaDto>

    @PUT("eventos/{id}/sala")
    suspend fun vincularPublicacaoComSalas(
        @Path("id") id: Int,
        @Body salas: List<Int>
    ): com.example.nucleofornari.domain.model.evento.EventoRespostaDto

    @DELETE("eventos/{id}")
    suspend fun deletarEvento(@Path("id") id: Int): Void

    @PUT("eventos/{id}")
    suspend fun atualizarEvento(
        @Path("id") id: Int,
    ): com.example.nucleofornari.domain.model.evento.EventoRespostaDto
}

object EventoApi {

    private val BASE_URL = "https://nucleofornari.serveminecraft.net/api/"

    fun getApi(token: String): EventoApiService {

        val logInterceptor = HttpLoggingInterceptor()
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val clienteHttp = OkHttpClient.Builder()
            .addInterceptor(logInterceptor)
            .addInterceptor(TokenInterceptor(token)) // interceptor de token
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp) // interceptor de log, opcional
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EventoApiService::class.java)
    }
}