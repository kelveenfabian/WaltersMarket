package com.v.waltersmarket.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v.waltersmarket.data.repository.AstrologyRepository
import com.v.waltersmarket.data.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.ui.model.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AstrologyViewModel(private val astrologyRepository: AstrologyRepository) : ViewModel() {

    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets: StateFlow<List<Planet>> = _planets.asStateFlow()

    var dayText by mutableStateOf("")
        private set
    fun updateDay(input: String) {
        dayText = input
    }
    var monthText by mutableStateOf("")
        private set
    fun updateMonth(input: String) {
        monthText = input
    }
    var yearText by mutableStateOf("")
        private set
    fun updateYear(input: String) {
        yearText = input
    }

    var lonText by mutableStateOf("")
        private set
    fun updateLongitude(input: String) {
        lonText = input
    }
    var latText by mutableStateOf("")
        private set
    fun updateLatitude(input: String) {
        latText = input
    }
    var hourText by mutableStateOf("")
        private set
    fun updateHour(input: String) {
        hourText = input
    }

    var minText by mutableStateOf("")
        private set
    fun updateMinute(input: String) {
        minText = input
    }

    var timeZoneText by mutableStateOf("")
        private set
    fun updateTimezone(input: String) {
        timeZoneText = input
    }



    fun fetchWesternHoroscopeData() {
        viewModelScope.launch {

            val requestData = WesternHoroscopeRequestData(
                day = dayText,
                month = monthText,
                year = yearText,
                lon = lonText,
                lat = latText,
                tzone = timeZoneText,
                hour = hourText,
                min = minText,
            )

            val response = astrologyRepository.getWesternHoroscope(body = requestData)

            if (response.planets != null) {
                _planets.value = response.planets.map {
                    Planet(
                        name = it.name,
                        fullDegree = it.fullDegree,
                        normDegree = it.normDegree,
                        speed = it.speed,
                        house = it.house,
                        sign = it.sign,
                        signId = it.signId,
                        isRetro = it.isRetro
                    )
                }
            }
        }
    }
}