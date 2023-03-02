package com.positronen.maps.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    val lat: Double,
    val lon: Double,
    val address: AddressResponse
)
