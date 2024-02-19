package com.v.waltersmarket.data.authmanager

import okhttp3.Credentials

object AstrologyAuthenticationManager {
    private const val username = "628365"
    private const val password = "7f090bd0eb27540b9a65dd18aca41609"

    fun getCredentials() : String = Credentials.basic(username, password)
}