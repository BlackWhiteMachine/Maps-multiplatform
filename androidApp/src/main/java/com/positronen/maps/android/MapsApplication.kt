package com.positronen.maps.android

import android.app.Application
import com.positronen.maps.PlatformConfiguration
import com.positronen.maps.PlatformSDK

class MapsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        initPlatformSDK()
    }

    private fun initPlatformSDK() =
        PlatformSDK.init(
            configuration = PlatformConfiguration(androidContext = applicationContext)
        )
}