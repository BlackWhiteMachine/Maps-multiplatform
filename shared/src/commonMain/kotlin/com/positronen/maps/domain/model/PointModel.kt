package com.positronen.maps.domain.model

data class PointModel(
    val id: String,
    val pointType: PointType,
    val name: String,
    val description: String?,
    val location: LocationModel
)
