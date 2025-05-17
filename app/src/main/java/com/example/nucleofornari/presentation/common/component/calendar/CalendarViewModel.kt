package com.example.nucleofornari.presentation.common.component.calendar

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nucleofornari.data.model.SessaoUsuario
import com.example.nucleofornari.data.model.evento.EventoCriacaoReqDto
import com.example.nucleofornari.data.model.evento.EventoRespostaDto
import com.example.nucleofornari.data.remote.service.EventoApiService
import com.example.nucleofornari.util.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CalendarViewModel(
    private val sessaoUsuario: SessaoUsuario,
    private val api: EventoApiService
) : ViewModel() {

    private val _createEventoState = MutableStateFlow<UiState<EventoRespostaDto?>>(UiState.Empty)
    val createEventoState: StateFlow<UiState<EventoRespostaDto?>> = _createEventoState

    private val _listEventosState = MutableStateFlow<UiState<Map<LocalDate, List<EventoRespostaDto>>>>(UiState.Empty)
    val listEventosState: StateFlow<UiState<Map<LocalDate, List<EventoRespostaDto>>>> = _listEventosState

    fun criarEvento(titulo: String, descricao: String, data: LocalDateTime, tipo: String) {
        viewModelScope.launch {
            _createEventoState.value = UiState.Loading
            try {
                val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                val req = EventoCriacaoReqDto(
                    titulo = titulo,
                    descricao = descricao,
                    data = data.format(formatter),
                    tipo = tipo,
                    usuarioId = sessaoUsuario.userId,
                    salas = sessaoUsuario.salaId?.let { listOf(it) }
                )
                val response = api.criarEvento(req)
                _createEventoState.value = UiState.Success(response)
                listarEventos()
            } catch (e: Exception) {
                _createEventoState.value = UiState.Error("Erro ao criar evento: ${e.message}")
            }
        }
    }

    fun listarEventos() {
        viewModelScope.launch {
            _listEventosState.value = UiState.Loading
            try {
                val eventos = api.listarEventos()
                val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
                val agrupados = eventos.mapNotNull { evento ->
                    evento.data?.let {
                        try {
                            val date = LocalDateTime.parse(it, formatter).toLocalDate()
                            evento to date
                        } catch (e: Exception) {
                            null
                        }
                    }
                }.groupBy { it.second }.mapValues { entry -> entry.value.map { it.first } }

                _listEventosState.value = UiState.Success(agrupados)
            } catch (e: Exception) {
                _listEventosState.value = UiState.Error("Erro ao carregar eventos: ${e.message}")
            }
        }
    }

    fun resetCreateEventoState() {
        _createEventoState.value = UiState.Empty
    }
}
