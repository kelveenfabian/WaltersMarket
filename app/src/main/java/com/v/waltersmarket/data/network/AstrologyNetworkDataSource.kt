package com.v.waltersmarket.data.network

import com.v.waltersmarket.data.WesternHoroscopeResponse

interface AstrologyNetworkDataSource {

    suspend fun getWesternHoroscope() : WesternHoroscopeResponse
}