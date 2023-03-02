package com.positronen.maps

interface UUIDProvider {
    val randomUUID: String
}

expect fun getUUID(): String