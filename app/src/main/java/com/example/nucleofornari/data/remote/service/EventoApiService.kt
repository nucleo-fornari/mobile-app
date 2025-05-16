package com.example.nucleofornari.data.remote.service

import com.example.nucleofornari.data.model.evento.EventoCriacaoReqDto
import com.example.nucleofornari.data.model.evento.EventoRespostaDto
import com.example.nucleofornari.data.remote.TokenInterceptor
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
import retrofit2.http.*

interface EventoApiService {

    @POST("eventos")
    suspend fun criarEvento(@Body evento: EventoCriacaoReqDto): EventoRespostaDto

    @GET("eventos/sala/{id}")
    suspend fun listarEventosPorSala(@Path("id") id: Int): List<EventoRespostaDto>

    @GET("eventos")
    suspend fun listarEventos(): List<EventoRespostaDto>

    @GET("eventos/publicacoes")
    suspend fun listarPublicacoes(): List<EventoRespostaDto>

    @GET("eventos/publicacoes/usuario/{id}")
    suspend fun listarPublicacoesPorUsuario(@Path("id") id: Int): List<EventoRespostaDto>

    @PUT("eventos/{id}/sala")
    suspend fun vincularPublicacaoComSalas(
        @Path("id") id: Int,
        @Body salas: List<Int>
    ): EventoRespostaDto

    @DELETE("eventos/{id}")
    suspend fun deletarEvento(@Path("id") id: Int): Void

    @PUT("eventos/{id}")
    suspend fun atualizarEvento(
        @Path("id") id: Int,
        @Body eventoAtualizado: EventoCriacaoReqDto
    ): EventoRespostaDto
}

object EventoApi {

    private val BASE_URL = "http://10.18.32.247:8080/api/"

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