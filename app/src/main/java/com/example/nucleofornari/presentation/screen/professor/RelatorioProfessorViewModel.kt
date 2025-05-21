package com.example.nucleofornari.presentation.screen.professor

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.aluno.AlunoResponseDto
import com.example.nucleofornari.data.remote.service.UsuarioApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

class RelatorioProfessorViewModel (
    private val sessaoUsuario: SessaoUsuario,
    private val api: UsuarioApiService
) : ViewModel() {

    var uiStateAlunosComRelatorio by mutableStateOf<UiState<List<AlunoResponseDto>>>(UiState.Loading)
        private set

    init {
        getAlunosComRelatorio()
    }

    private fun getAlunosComRelatorio() {
        viewModelScope.launch {
            try {
                val salaId = sessaoUsuario.salaId
                when {
                    salaId != null -> {
                        val sala = api.getSalaPorId(salaId)
                        val alunosComAvaliacoes = sala.alunos.filter { it.avaliacoes.isNotEmpty() }
                        uiStateAlunosComRelatorio = UiState.Success(alunosComAvaliacoes)
                    }
                    else -> {
                        uiStateAlunosComRelatorio = UiState.Error("ID da sala não encontrado para o professor logado.")
                    }
                }
            } catch (e: Exception) {
                uiStateAlunosComRelatorio = UiState.Error("Erro ao carregar alunos da sala: ${e.message}")
            }

        }
    }

    fun downloadAvaliacaoPdf(avaliacaoId: Int, context: Context) {
        val outputDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = api.getAvaliacaoPdf(avaliacaoId)
                if (response.isSuccessful) {
                    response.body()?.let { responseBody ->
                        savePdfToFile(responseBody, outputDir, "avaliacao_$avaliacaoId.pdf", context)
                    }
                } else {
                    Log.e("Download", "Erro na resposta: ${response.code()} - ${response.message()}")
                }
            } catch (e: Exception) {
                Log.e("Download", "Erro ao baixar PDF: ${e.message}")
            }
        }
    }

    private fun savePdfToFile(
        body: ResponseBody,
        outputDir: File,
        fileName: String,
        context: Context
    ) {
        try {
            val file = File(outputDir, fileName)
            val inputStream: InputStream = body.byteStream()
            val outputStream: OutputStream = FileOutputStream(file)

            inputStream.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                    openFileWithIntent(context, file)
                }
            }
        } catch (e: Exception) {
            Log.e("Download", "Erro ao salvar PDF: ${e.message}")
        }
    }

    fun openFileWithIntent(context: Context, file: File) {
        try {
            val uri: Uri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.provider", // ex: com.example.app.provider
                file
            )

            val intent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(uri, "application/pdf")
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }

            context.startActivity(intent)

        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "Nenhum app para abrir esse tipo de arquivo.", Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            Toast.makeText(context, "Erro ao abrir arquivo: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }


}