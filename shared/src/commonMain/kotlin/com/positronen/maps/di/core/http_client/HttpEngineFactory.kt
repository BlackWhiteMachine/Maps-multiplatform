package com.positronen.maps.di.core.http_client

import io.ktor.client.engine.*

internal expect class HttpEngineFactory constructor() {
    fun createEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
}