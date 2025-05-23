package com.example.nucleofornari.data.remote.service

import com.example.nucleofornari.data.model.chamado.ChamadoDto
import com.example.nucleofornari.data.model.chamado.TipoChamadoDto
import com.example.nucleofornari.data.model.evento.EventoDto
import com.example.nucleofornari.data.model.sala.SalaDto
import com.example.nucleofornari.data.model.usuario.AlunoAndSalaIdDto
import com.example.nucleofornari.data.model.usuario.ProfessorResponseDto
import com.example.nucleofornari.data.model.usuario.UsuarioCreateDto
import com.example.nucleofornari.data.model.usuario.UsuarioLoginDto
import com.example.nucleofornari.data.model.usuario.UsuarioResponseDto
import com.example.nucleofornari.data.model.usuario.UsuarioTokenDto
import com.example.nucleofornari.data.remote.TokenInterceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface UsuarioApiService {

    @POST("usuarios/login")
    suspend fun login(@Body usuarioLoginDto: UsuarioLoginDto): UsuarioTokenDto

    @POST("chamados")
    suspend fun createChamado(@Body chamadoDto: ChamadoDto, @Query("idUsuario") id: Int): ChamadoDto

    @GET("tipos-chamado")
    suspend fun findTiposChamado(): List<TipoChamadoDto>

    @GET("chamados")
    suspend fun listChamados(@Query("idUser") id: Int): List<ChamadoDto>

    @GET("avaliacao/pdf/{id}")
    suspend fun getAvaliacaoPdf(@Path("id") id: Int): Response<ResponseBody>

    @GET("eventos/sala/{id}")
    suspend fun getEventosPorSala(@Path("id") id: Int): List<EventoDto>

    @GET("salas/{id}")
    suspend fun getSalaPorId(@Path("id") id: Int): SalaDto

    @GET("usuarios/professores")
    fun getProfessoresSemSala(): List<UsuarioResponseDto>

    @GET("usuarios")
    fun getUsuarios(): List<UsuarioResponseDto>

    @GET("usuarios/{id}")
    suspend fun getUsuarioPorId(@Path("id") id: Int): UsuarioResponseDto

    @POST("usuarios/funcionario")
    fun criarFuncionario(@Body usuarioCreateDto: UsuarioCreateDto): UsuarioResponseDto

    @PUT("usuarios/{id}")
    fun atualizarUsuario(@Path("id") id: Int, @Body usuarioCreateDto: UsuarioCreateDto): UsuarioResponseDto

    @DELETE("usuarios/{id}")
    fun deletarUsuario(@Path("id") id: Int): Call<Unit>

    @PATCH("usuarios/professor/{id}/sala/{salaId}")
    fun associarProfessorSala(@Path("id") id: Int, @Path("salaId") salaId: Int): ProfessorResponseDto

    @PATCH("usuarios/professor/{id}/sala/remover")
    fun removerProfessorDaSala(@Path("id") id: Int): ProfessorResponseDto

    @GET("usuarios/aluno-e-sala/{id}")
    fun getAlunoESala(@Path("id") id: Int): List<AlunoAndSalaIdDto>

    @PATCH("usuarios/esqueci-senha")
    suspend fun esqueciSenha(@Query("email") email: String)

    @PATCH("usuarios/token-redefinicao-senha")
    suspend fun tokenRedefinicaoSenha(@Query("token") token: String)

    @PUT("usuarios/redefinir-senha")
    suspend fun redefinirSenha(@Query("token") token: String, @Query("email") email: String, @Query("senha") senha: String)
}

object UsuarioApi {

    private val BASE_URL = "http://10.18.32.247:8080/api/"

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