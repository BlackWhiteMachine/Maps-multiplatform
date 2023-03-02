package com.positronen.maps.di.core

import com.positronen.maps.di.core.http_client.httpClientModule
import org.kodein.di.DI

val coreModule = DI.Module("coreModule") {
    importAll(
     //   serializationModule,
//        databaseModule,
        httpClientModule,
    //    settingsModule
    )
}