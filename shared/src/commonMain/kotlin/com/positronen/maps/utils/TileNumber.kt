package com.positronen.maps.utils

import com.positronen.maps.domain.model.MapRegionModel
import com.positronen.maps.domain.model.MapTileRegionModel
import kotlin.math.PI
import kotlin.math.asinh
import kotlin.math.atan
import kotlin.math.floor
import kotlin.math.pow
import kotlin.math.sinh
import kotlin.math.tan

fun getTileNumber(lat : Double, lon: Double, zoom : Int) : Pair<Int, Int> {
    val latRad = lat.toRadians()
    var xtile = floor( (lon + 180) / 360 * (1 shl zoom) ).toInt()
    var ytile = floor( (1.0 - asinh(tan(latRad)) / PI) / 2 * (1 shl zoom) ).toInt()

    if (xtile < 0) xtile = 0
    if (xtile >= (1 shl zoom)) xtile= (1 shl zoom) - 1
    if (ytile < 0) ytile = 0
    if (ytile >= (1 shl zoom)) ytile = (1 shl zoom) - 1

    return Pair(xtile, ytile)
}

fun getTilesList(visibleRegion: MapRegionModel, zoom: Int) : List<Pair<Int, Int>> {
    val (farLeftX, farLeftY) = getTileNumber(
        lat = visibleRegion.topLeftLatitude,
        lon = visibleRegion.topLeftLongitude,
        zoom = zoom
    )
    val (nearRightX, nearRightY) = getTileNumber(
        lat = visibleRegion.bottomRightLatitude,
        lon = visibleRegion.bottomRightLongitude,
        zoom = zoom
    )

    return mutableListOf<Pair<Int, Int>>().apply {
        for (y in farLeftY..nearRightY) {
            for (x in farLeftX..nearRightX) {
                add(x to y)
            }
        }
    }
}

fun getTopLeft(xTile: Int, yTile: Int, zoom: Int): Pair<Double, Double> {
    val n = PI - 2.0 * PI * yTile / 2.0.pow(zoom.toDouble())
    val lat = atan(sinh(n)).toDegrees()

    val lon = xTile / 2.0.pow(zoom.toDouble()) * 360.0 - 180

    return lat to lon
}

fun getTileRegion(xTile: Int, yTile: Int, zoom: Int): MapTileRegionModel {
    val (topLeftTileLat, topLeftTileLon) = getTopLeft(xTile, yTile, zoom)
    val (bottomRightTileLat, bottomRightTileLon) = getTopLeft(xTile + 1, yTile + 1, zoom)

    return MapTileRegionModel(
        xTile = xTile,
        yTile = yTile,
        mapRegionModel = MapRegionModel(
            topLeftLatitude = topLeftTileLat,
            topLeftLongitude = topLeftTileLon,
            bottomRightLatitude = bottomRightTileLat,
            bottomRightLongitude = bottomRightTileLon
        )
    )
}

fun Double.format(digits: Int): String = "%.${digits}f".format(this)

fun Double.toRadians(): Double = (this / 180) * PI
fun Double.toDegrees(): Double = (this * 180) / PI
