package com.v.waltersmarket.data.repository

import com.v.waltersmarket.data.authmanager.AstrologyAuthenticationManager
import com.v.waltersmarket.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.data.network.AstrologyClientProvider
import com.v.waltersmarket.data.network.AstrologyNetworkDataSource
import com.v.waltersmarket.data.response.WesternHoroscopeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AstrologyRepositoryImpl(
    private val ioDispatchers: CoroutineDispatcher,
    private val astrologyDataSource: AstrologyNetworkDataSource,
) : AstrologyRepository {
    private val authentication = AstrologyAuthenticationManager.getCredentials()
    override suspend fun getWesternHoroscope(body: WesternHoroscopeRequestData) = withContext(ioDispatchers) {
        astrologyDataSource.getWesternHoroscope(authentication, body)
    }
}

interface AstrologyRepository {
    suspend fun getWesternHoroscope(body: WesternHoroscopeRequestData): WesternHoroscopeResponse
}

object AstrologyRepositoryFactory {
    fun getAstrologyRepository() : AstrologyRepository = AstrologyRepositoryImpl(
        ioDispatchers = Dispatchers.IO,
        astrologyDataSource = AstrologyClientProvider
    )
}