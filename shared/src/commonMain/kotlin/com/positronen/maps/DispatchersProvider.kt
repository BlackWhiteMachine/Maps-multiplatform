package com.positronen.maps

import kotlinx.coroutines.CoroutineDispatcher

expect class DispatchersProvider constructor() {

    val DispatchersIO: CoroutineDispatcher
    val DispatchersDefault: CoroutineDispatcher
    val DispatchersMain: CoroutineDispatcher
    val DispatchersUnconfined: CoroutineDispatcher
}