package com.positronen.maps.domain

import com.positronen.maps.domain.model.MapTileRegionModel
import com.positronen.maps.domain.model.PointDetailModel
import com.positronen.maps.domain.model.PointModel
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    suspend fun places(tileRegion: MapTileRegionModel): List<PointModel>

    fun place(id: String): Flow<PointDetailModel>

    suspend fun events(tileRegion: MapTileRegionModel): List<PointModel>

    fun event(id: String): Flow<PointDetailModel>

    fun activities(start: Int, limit: Int): Flow<List<PointModel>>

    fun activity(id: String): Flow<PointDetailModel>
}