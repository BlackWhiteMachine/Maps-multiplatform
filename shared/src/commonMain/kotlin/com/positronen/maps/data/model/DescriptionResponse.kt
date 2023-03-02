package com.positronen.maps.data.model

import kotlinx.serialization.Serializable

@Serializable
data class DescriptionResponse(
    val intro: String,
    val body: String,
    val images: List<ImageResponse>
)