package com.positronen.maps

import com.positronen.maps.di.Inject
import com.positronen.maps.di.core.coreModule
import com.positronen.maps.di.mainModule
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.direct
import org.kodein.di.singleton

object PlatformSDK {

    fun init(
        configuration: PlatformConfiguration
    ) {
        val sharedModule = DI.Module(
            name = "shared",
            init = {
                bind<DispatchersProvider>() with singleton { DispatchersProvider() }
                bind<PlatformConfiguration>() with singleton { configuration }
            }
        )

        Inject.createDependencies(
            DI {
                importAll(
                    sharedModule,
                    coreModule,
                    mainModule
                )
            }.direct
        )
    }
}