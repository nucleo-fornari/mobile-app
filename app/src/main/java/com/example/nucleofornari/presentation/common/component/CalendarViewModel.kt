package com.example.nucleofornari.presentation.common.component

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import java.time.LocalDate

class CalendarViewModel : ViewModel() {
    private val _events = mutableStateMapOf<LocalDate, MutableList<String>>()
    val events: Map<LocalDate, List<String>> get() = _events

    fun addEvent(date: LocalDate, eventText: String) {
        _events.getOrPut(date) { mutableListOf() }.add(eventText)
    }
}
