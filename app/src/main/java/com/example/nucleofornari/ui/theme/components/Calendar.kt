package com.example.nucleofornari.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.nucleofornari.ui.theme.AzulClaro
import com.example.nucleofornari.ui.theme.AzulPrincipal
import com.example.nucleofornari.ui.theme.Success
import com.kizitonwose.calendar.compose.HorizontalCalendar
import com.kizitonwose.calendar.compose.rememberCalendarState
import com.kizitonwose.calendar.core.daysOfWeek
import com.kizitonwose.calendar.core.yearMonth
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

@Composable
fun Calendar(viewModel: CalendarViewModel = viewModel()) {
    val today = LocalDate.now()
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var showDialog by remember { mutableStateOf(false) }
    var eventText by remember { mutableStateOf("") }
    var displayedMonth by remember { mutableStateOf(today.yearMonth) }

    val calendarState = rememberCalendarState(
        startMonth = today.yearMonth.minusMonths(6),
        endMonth = today.yearMonth.plusMonths(6),
        firstVisibleMonth = today.yearMonth
    )

    LaunchedEffect(calendarState) {
        snapshotFlow { calendarState.firstVisibleMonth }
            .collect { month ->
                displayedMonth = month.yearMonth
            }
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp)) {
        // Título do mês
        Text(
            text = displayedMonth.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        HorizontalCalendar(
            state = calendarState,
            dayContent = { day ->
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
                            .clickable { selectedDate = day.date },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = day.date.dayOfMonth.toString(),
                            color = if (day.date == selectedDate) AzulPrincipal else if (day.date == today) Color.White else Color.Black
                        )
                    }

                    if (viewModel.events[day.date].orEmpty().isNotEmpty()) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(AzulPrincipal)
                        )
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

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Novo Evento") },
                text = {
                    Column {
                        Text("Digite o evento:")
                        TextField(value = eventText, onValueChange = { eventText = it }, modifier = Modifier.fillMaxWidth())
                    }
                },
                confirmButton = {
                    Button(onClick = {
                        selectedDate?.let { date ->
                            viewModel.addEvent(date, eventText)
                        }
                        eventText = ""
                        showDialog = false
                    }) {
                        Text("Salvar")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }

        selectedDate?.let { date ->
            viewModel.events[date]?.let { eventList ->
                if (eventList.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("Eventos para ${date.format(DateTimeFormatter.ofPattern("d MMM yyyy"))}:", fontWeight = FontWeight.Bold)
                    eventList.forEach { event ->
                        Text("- $event")
                    }
                }
            }
        }
    }
}










@Composable
@Preview(showBackground = true)
fun CalendarPreview() {
    Calendar()
}