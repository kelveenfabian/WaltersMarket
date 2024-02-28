package com.v.waltersmarket.data.network

import com.v.waltersmarket.data.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.data.response.WesternHoroscopeResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

object AstrologyClientProvider : AstrologyNetworkDataSource {
    private const val BASE_URL = "https://json.astrologyapi.com/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().setLevel(Level.BASIC)

    private val client = OkHttpClient().newBuilder().addInterceptor(loggingInterceptor)

    private val westernHoroscopeClient = Retrofit.Builder()
        .client(client.build())
        .baseUrl(BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()
        .create(AstrologyClient::class.java)

    override suspend fun getWesternHoroscope(auth: String, body: WesternHoroscopeRequestData): WesternHoroscopeResponse =
        westernHoroscopeClient.getWesternHoroscope(auth, body)

}

interface AstrologyClient {
    @POST("western_horoscope")
    suspend fun getWesternHoroscope(@Header("Authorization") auth: String, @Body body: WesternHoroscopeRequestData) : WesternHoroscopeResponse
}
