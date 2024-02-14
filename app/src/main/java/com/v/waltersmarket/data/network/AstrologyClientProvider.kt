package com.v.waltersmarket.data.network

import com.v.waltersmarket.data.WesternHoroscopeResponse
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

object AstrologyClientProvider : AstrologyNetworkDataSource {
    private val westernHoroscopeClient = Retrofit.Builder()
        .baseUrl("https://json.astrologyapi.com/v1/")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(AstrologyClient::class.java)


    override suspend fun getWesternHoroscope(): WesternHoroscopeResponse =
        westernHoroscopeClient.getWesternHoroscope()

}

interface AstrologyClient {

    @GET("western_horoscope")
    suspend fun getWesternHoroscope() : WesternHoroscopeResponse
}