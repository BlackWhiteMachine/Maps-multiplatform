package com.positronen.maps.data.converter

import com.positronen.maps.data.model.LocationResponse
import com.positronen.maps.data.model.EventsV1Response
import com.positronen.maps.data.model.ImageResponse
import com.positronen.maps.data.model.PlaceV2Response
import com.positronen.maps.domain.model.ImageModel
import com.positronen.maps.domain.model.LocationModel
import com.positronen.maps.domain.model.PointDetailModel
import com.positronen.maps.domain.model.PointModel
import com.positronen.maps.domain.model.PointType

class MainConverterImpl : MainConverter {

    override fun convertResponsePlaces(places: List<PlaceV2Response>): List<PointModel> {
        return places.mapNotNull(::convertResponsePlace)
    }

    override fun convertResponsePlaceDetail(place: PlaceV2Response): PointDetailModel? {
        val name = place.name.fi ?: return null
        val location = place.location ?: return null

        return PointDetailModel(
            id = place.id,
            pointType = PointType.PLACE,
            name = name,
            description = place.description?.body?.ifEmpty { place.description.intro },
            images = place.description?.images?.map(::mapImages) ?: emptyList(),
            location = convertLocation(location),
            infoUrl = place.infoUrl,
        )
    }

    override fun convertResponseEvents(events: List<EventsV1Response>): List<PointModel> {
        return events.mapNotNull(::convertResponseEvent)
    }

    override fun convertResponseEventDetail(event: EventsV1Response): PointDetailModel? {
        val name = event.name.fi ?: return null
        val location = event.location ?: return null

        return PointDetailModel(
            id = event.id,
            pointType = PointType.EVENT,
            name = name,
            description = event.description?.body?.ifEmpty { event.description.intro },
            images = event.description?.images?.map(::mapImages) ?: emptyList(),
            location = convertLocation(location),
            infoUrl = event.infoUrl,
        )
    }

    private fun convertResponsePlace(place: PlaceV2Response): PointModel? {
        val name = place.name.fi ?: return null
        val location = place.location ?: return null

        return PointModel(
            id = place.id,
            pointType = PointType.PLACE,
            name = name,
            description = place.description?.intro,
            location = convertLocation(location)
        )
    }

    private fun convertResponseEvent(place: EventsV1Response): PointModel? {
        val name = place.name.fi ?: return null
        val location = place.location ?: return null

        return PointModel(
            id = place.id,
            pointType = PointType.EVENT,
            name = name,
            description = place.description?.intro,
            location = convertLocation(location)
        )
    }

    private fun mapImages(imageResponse: ImageResponse): ImageModel =
        ImageModel(
            url = imageResponse.url,
            copyrightHolder = imageResponse.copyrightHolder,
            license = imageResponse.licenseType.name
        )

    private fun convertLocation(location: LocationResponse): LocationModel {
        val address = location.address

        val constructedAddress = "${address.postalCode}, " +
                "${address.locality}, " +
                "${address.neighbourhood}, " +
                "${address.streetAddress}, " +
                String.format("%.7f", location.lat) +
                "," +
                String.format("%.7f", location.lon)

        return LocationModel(
            latitude = location.lat,
            longitude = location.lon,
            address = constructedAddress
        )
    }
}