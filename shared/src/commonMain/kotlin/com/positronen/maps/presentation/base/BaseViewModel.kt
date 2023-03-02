package com.positronen.maps.presentation.base

import kotlinx.coroutines.CoroutineScope

expect abstract class BaseViewModel constructor() {
    protected val baseCoroutineScope: CoroutineScope
}
