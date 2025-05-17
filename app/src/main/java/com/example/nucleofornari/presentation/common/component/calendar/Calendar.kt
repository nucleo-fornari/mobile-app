package com.example.nucleofornari.presentation.common.component.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.example.nucleofornari.presentation.common.theme.AzulClaro
import com.example.nucleofornari.presentation.common.theme.AzulPrincipal
import com.example.nucleofornari.util.UiState
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.yearMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun Calendar(viewModel: CalendarViewModel) {
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var displayedMonth by remember { mutableStateOf(today.yearMonth) }

    val calendarState = rememberCalendarState(
        startMonth = today.yearMonth.minusMonths(6),
        endMonth = today.yearMonth.plusMonths(6),
        firstVisibleMonth = today.yearMonth
    )

    LaunchedEffect(Unit) {
        viewModel.listarEventos()
    }

    LaunchedEffect(calendarState) {
        snapshotFlow { calendarState.firstVisibleMonth }
            .collect { month ->
                displayedMonth = month.yearMonth
            }
    }

    val eventosState by viewModel.listEventosState.collectAsState()

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        Text(
            text = displayedMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy", Locale("pt", "BR"))),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
                val isFromCurrentMonth = day.date.yearMonth == displayedMonth

                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.size(40.dp)) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    day.date == selectedDate -> AzulClaro
                                    day.date == today -> AzulPrincipal
                                    else -> Color.Transparent
                                }
                            )
                            .clickable(enabled = isFromCurrentMonth) {
                                if (isFromCurrentMonth) {
                                    selectedDate = day.date
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.date.dayOfMonth.toString(),
                            color = when {
                                day.date == selectedDate -> AzulPrincipal
                                day.date == today -> Color.White
                                !isFromCurrentMonth -> Color.Gray
                                else -> Color.Black
                            }
                        )
                    }

                    if (eventosState is UiState.Success) {
                        val eventosDoDia = (eventosState as? UiState.Success)?.data?.get(day.date).orEmpty()
                        if (eventosDoDia.isNotEmpty()) {
                            Box(
                                modifier = Modifier
                                    .size(6.dp)
                                    .clip(CircleShape)
                                    .background(AzulPrincipal)
                            )
                        }
                    }
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        selectedDate?.let {
            Button(onClick = { showDialog = true }, colors = ButtonDefaults.buttonColors(containerColor = AzulPrincipal)) {
                Text(text = "Adicionar Evento")
            }
        }

        if (showDialog && selectedDate != null) {
            var eventTitulo by remember { mutableStateOf("") }
            var eventDescricao by remember { mutableStateOf("") }
            var eventDataHora by remember { mutableStateOf("00:00") }

            AlertDialog(
                onDismissRequest = { showDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false),
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(16.dp)),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Adicionar evento", fontWeight = FontWeight.Bold)
                        IconButton(onClick = { showDialog = false }) {
                            Icon(Icons.Default.Close, contentDescription = "Fechar")
                        }
                    }
                },
                text = {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            selectedDate!!.format(DateTimeFormatter.ofPattern("dd 'de' MMMM", Locale("pt", "BR"))),
                            color = Color.Gray,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        OutlinedTextField(
                            value = eventTitulo,
                            onValueChange = { eventTitulo = it },
                            label = { Text("Título") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = eventDataHora,
                            onValueChange = {
                                val filtered = it.filter { char -> char.isDigit() || char == ':' }
                                if (filtered.length <= 5 && Regex("^\\d{0,2}:?\\d{0,2}$").matches(filtered)) {
                                    eventDataHora = if (filtered.length == 2 && !filtered.contains(":")) "$filtered:" else filtered
                                }
                            },
                            label = { Text("Hora") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            trailingIcon = {
                                Icon(imageVector = Icons.Default.AccessTime, contentDescription = "Hora")
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = eventDescricao,
                            onValueChange = { eventDescricao = it },
                            label = { Text("Descrição") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            selectedDate?.let { date ->
                                val dataHora = try {
                                    val (hora, minuto) = eventDataHora.split(":").map { it.toInt() }
                                    date.atTime(hora, minuto)
                                } catch (e: Exception) {
                                    null
                                }

                                if (dataHora != null) {
                                    viewModel.criarEvento(
                                        titulo = eventTitulo,
                                        descricao = eventDescricao,
                                        data = dataHora,
                                        tipo = "PUBLICACAO"
                                    )
                                }
                            }
                            eventTitulo = ""
                            eventDescricao = ""
                            eventDataHora = "00:00"
                            showDialog = false
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("SALVAR")
                    }
                },
                dismissButton = {}
            )
        }

        selectedDate?.let { date ->
            when (eventosState) {
                is UiState.Loading -> CircularProgressIndicator()
                is UiState.Error -> Text("Erro ao carregar eventos: ${(eventosState as UiState.Error).message}")
                is UiState.Success -> {
                    val eventosDoDia = (eventosState as? UiState.Success)?.data?.get(date).orEmpty()
                    if (eventosDoDia.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            "Eventos para ${date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))}:",
                            fontWeight = FontWeight.Bold
                        )
                        eventosDoDia.forEach { evento ->
                            Text("- ${evento.titulo}")
                        }
                    } else {
                        Text("Nenhum evento para o dia selecionado.")
                    }
                }
                else -> Text("Sem eventos marcados.")
            }
        }
    }
}

//@Composable
//@Preview(showBackground = true)
//fun CalendarPreview() {
//    Calendar()
//}