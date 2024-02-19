package com.v.waltersmarket.data.network

import com.v.waltersmarket.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.data.response.WesternHoroscopeResponse

interface AstrologyNetworkDataSource {

    suspend fun getWesternHoroscope(auth: String, body: WesternHoroscopeRequestData) : WesternHoroscopeResponse
}