package com.positronen.maps.data.service

import com.positronen.maps.data.model.DataResponse
import com.positronen.maps.data.model.EventsV1Response
import com.positronen.maps.data.model.PlaceV2Response

interface MainService {

    suspend fun places(
        distanceFilter: String?,
        start: Int,
        limit: Int
    ): DataResponse<List<PlaceV2Response>>

    suspend fun place(id: String): PlaceV2Response

    suspend fun events(
        distanceFilter: String?,
        start: Int,
        limit: Int
    ): DataResponse<List<EventsV1Response>>

    suspend fun event(id: String): EventsV1Response

    suspend fun activities(
        start: Int,
        limit: Int
    ): DataResponse<List<PlaceV2Response>>

    suspend fun activity(id: String): PlaceV2Response

}