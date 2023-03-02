package com.positronen.maps.domain.interactor

import com.positronen.maps.domain.model.MapTileRegionModel
import com.positronen.maps.domain.model.PointDetailModel
import com.positronen.maps.domain.model.PointModel
import com.positronen.maps.domain.model.PointType
import kotlinx.coroutines.flow.Flow

interface MainInteractor {

    val defaultDataZoomLevel: Int

    fun places(visibleTilesList: List<MapTileRegionModel>): Flow<List<PointModel>>

    fun events(visibleTilesList: List<MapTileRegionModel>): Flow<List<PointModel>>

    fun point(id: String, pointType: PointType):  Flow<PointDetailModel>
}