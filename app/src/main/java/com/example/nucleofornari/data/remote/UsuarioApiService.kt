package com.example.nucleofornari.data.remote

import com.example.nucleofornari.data.model.usuario.AlunoAndSalaIdDto
import com.example.nucleofornari.data.model.usuario.ProfessorResponseDto
import com.example.nucleofornari.data.model.usuario.UsuarioCreateDto
import com.example.nucleofornari.data.model.usuario.UsuarioLoginDto
import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto
import com.example.nucleofornari.data.model.usuario.UsuarioTokenDto
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UsuarioApiService {

    @POST("usuarios/login")
    suspend fun login(@Body usuarioLoginDto: UsuarioLoginDto): UsuarioTokenDto

    @GET("usuarios/professores")
    fun getProfessoresSemSala(): List<UsuarioResponseDto>

    @GET("usuarios")
    fun getUsuarios(): List<UsuarioResponseDto>

    @GET("usuarios/{id}")
    fun getUsuarioPorId(@Path("id") id: Int): UsuarioResponseDto
    
    @POST("usuarios/funcionario")
    fun criarFuncionario(@Body usuarioCreateDto: UsuarioCreateDto): UsuarioResponseDto

    @PUT("usuarios/{id}")
    fun atualizarUsuario(@Path("id") id: Int, @Body usuarioCreateDto: UsuarioCreateDto): UsuarioResponseDto

    @DELETE("usuarios/{id}")
    fun deletarUsuario(@Path("id") id: Int): Call<Void>

    @PATCH("usuarios/professor/{id}/sala/{salaId}")
    fun associarProfessorSala(@Path("id") id: Int, @Path("salaId") salaId: Int): ProfessorResponseDto

    @PATCH("usuarios/professor/{id}/sala/remover")
    fun removerProfessorDaSala(@Path("id") id: Int): ProfessorResponseDto

    @GET("usuarios/aluno-e-sala/{id}")
    fun getAlunoESala(@Path("id") id: Int): List<AlunoAndSalaIdDto>

    @PATCH("usuarios/esqueci-senha")
    fun esqueciSenha(@Query("email") email: String): Void

    @PATCH("usuarios/token-redefinicao-senha")
    fun tokenRedefinicaoSenha(@Query("token") token: String): Void

    @PUT("usuarios/redefinir-senha")
    fun redefinirSenha(@Query("token") token: String, @Query("email") email: String, @Query("senha") senha: String): Void
}

object UsuarioApi {

    private val BASE_URL = "http://192.168.0.110:8080/"

    fun getApi(token: String): UsuarioApiService {

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
            .create(UsuarioApiService::class.java)
    }
}