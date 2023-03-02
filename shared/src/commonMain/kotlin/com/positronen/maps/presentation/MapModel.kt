package com.positronen.events.presentation

import com.positronen.maps.domain.model.Source

data class MapModel(
    val placesSource: Source = Source.INIT,
    val eventsSource: Source = Source.INIT
)