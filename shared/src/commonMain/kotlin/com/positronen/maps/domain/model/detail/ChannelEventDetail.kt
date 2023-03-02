package com.positronen.maps.domain.model.detail

sealed class ChannelEventDetail{
    data class ShareText(val text: String): ChannelEventDetail()
}
