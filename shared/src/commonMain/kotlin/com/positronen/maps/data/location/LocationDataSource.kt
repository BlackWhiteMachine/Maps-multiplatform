package com.positronen.maps.data.location

import kotlinx.coroutines.flow.Flow

interface LocationDataSource {

    fun location(): Flow<Pair<Double, Double>>
}