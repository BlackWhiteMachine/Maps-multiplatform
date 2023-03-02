package com.positronen.maps

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class DispatchersProvider {

    actual val DispatchersIO: CoroutineDispatcher = Dispatchers.IO
    actual val DispatchersDefault: CoroutineDispatcher = Dispatchers.Default
    actual val DispatchersMain: CoroutineDispatcher = Dispatchers.Main
    actual val DispatchersUnconfined: CoroutineDispatcher = Dispatchers.Unconfined
}