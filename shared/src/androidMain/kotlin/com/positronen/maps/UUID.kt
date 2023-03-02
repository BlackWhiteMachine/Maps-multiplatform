package com.positronen.maps

import java.util.*

class AndroidUUID : UUIDProvider {
    override val randomUUID: String
        get() = UUID.randomUUID().toString()
}

actual fun getUUID(): String = AndroidUUID().randomUUID