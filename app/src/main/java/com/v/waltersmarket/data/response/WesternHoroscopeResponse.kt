package com.v.waltersmarket.data.response

data class WesternHoroscopeResponse(
    val planets: List<Planet>?,
    val houses: List<House>?,
    val ascendant: Double?,
    val midHeaven: Double?,
    val vertex: Double?,
    val lilith: Lilith,
    val aspects: List<Aspect>
)

data class Planet(
    val name: String?,
    val fullDegree: Double?,
    val normDegree: Double?,
    val speed: Double?,
    val isRetro: Boolean,
    val signId: Int?,
    val sign: String?,
    val house: Int?,
)

data class House(
    val house: Int?,
    val sign: String?,
    val degree: Double?,
)

data class Lilith(val lilith: Planet)

data class Aspect(
    val aspectingPlanet: String?,
    val aspectedPlanet: String?,
    val aspectingPlanetId: Int?,
    val aspectedPlanetId: Int?,
    val type: String?,
    val orb: Double?,
    val diff: Double?,
)
