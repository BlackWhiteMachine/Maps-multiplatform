package com.positronen.maps

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform