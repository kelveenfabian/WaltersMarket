package com.v.waltersmarket.data.repository

import com.v.waltersmarket.data.authmanager.AstrologyAuthenticationManager
import com.v.waltersmarket.data.authmanager.Authentication
import com.v.waltersmarket.data.requestdata.WesternHoroscopeRequestData
import com.v.waltersmarket.data.network.AstrologyClientProvider
import com.v.waltersmarket.data.network.AstrologyNetworkDataSource
import com.v.waltersmarket.data.response.WesternHoroscopeResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AstrologyRepositoryImpl(
    private val ioDispatchers: CoroutineDispatcher,
    private val astrologyDataSource: AstrologyNetworkDataSource,
    private val authentication: Authentication,
) : AstrologyRepository {

    override suspend fun getWesternHoroscope(body: WesternHoroscopeRequestData) = withContext(ioDispatchers) {
        astrologyDataSource.getWesternHoroscope(authentication.getCredentials(), body)
    }
}

interface AstrologyRepository {
    suspend fun getWesternHoroscope(body: WesternHoroscopeRequestData): WesternHoroscopeResponse
}

object AstrologyRepositoryFactory {
    fun getAstrologyRepository() : AstrologyRepository = AstrologyRepositoryImpl(
        ioDispatchers = Dispatchers.IO,
        astrologyDataSource = AstrologyClientProvider,
        authentication = AstrologyAuthenticationManager
    )
}