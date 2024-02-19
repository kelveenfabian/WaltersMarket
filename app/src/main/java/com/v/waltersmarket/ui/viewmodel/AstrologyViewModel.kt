package com.v.waltersmarket.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.v.waltersmarket.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.data.repository.AstrologyRepository
import com.v.waltersmarket.ui.model.Planet
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AstrologyViewModel(private val astrologyRepository: AstrologyRepository) : ViewModel() {

    private val _planets = MutableStateFlow<List<Planet>>(emptyList())
    val planets: StateFlow<List<Planet>>
        get() = _planets

    fun fetchWesternHoroscopeData(requestData: WesternHoroscopeRequestData) {
        viewModelScope.launch {
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