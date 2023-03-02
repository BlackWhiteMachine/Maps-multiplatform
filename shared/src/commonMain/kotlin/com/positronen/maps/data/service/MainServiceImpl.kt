package com.positronen.maps.data.service

import com.positronen.maps.data.model.DataResponse
import com.positronen.maps.data.model.EventsV1Response
import com.positronen.maps.data.model.PlaceV2Response
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class MainServiceImpl(
    private val client: HttpClient
): MainService {

    override suspend fun places(
        distanceFilter: String?,
        start: Int,
        limit: Int
    ): DataResponse<List<PlaceV2Response>> =
        client.get {
            url {
                path("/v2/places/")
            }
            parameter("distance_filter", distanceFilter)
            parameter("start", start)
            parameter("limit", limit)
        }.body()

    override suspend fun place(id: String): PlaceV2Response =
        client.get {
            url {
                path("/v2/place/$id")
            }
        }.body()

    override suspend fun events(
        distanceFilter: String?,
        start: Int,
        limit: Int
    ): DataResponse<List<EventsV1Response>> =
        client.get {
            url {
                path("/v1/events/")
            }
            parameter("distance_filter", distanceFilter)
            parameter("start", start)
            parameter("limit", limit)
        }.body()

    override suspend fun event(id: String): EventsV1Response =
        client.get {
            url {
                path("/v1/event/$id")
            }
        }.body()

    override suspend fun activities(start: Int, limit: Int): DataResponse<List<PlaceV2Response>> =
        client.get {
            url {
                path("/v2/activities/")
            }
            parameter("start", start)
            parameter("limit", limit)
        }.body()

    override suspend fun activity(id: String): PlaceV2Response =
        client.get {
            url {
                path("/v2/activity/$id")
            }
        }.body()

}