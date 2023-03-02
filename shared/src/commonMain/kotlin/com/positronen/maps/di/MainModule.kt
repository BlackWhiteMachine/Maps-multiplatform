package com.positronen.maps.di

import com.positronen.maps.domain.MainRepository
import com.positronen.maps.domain.interactor.MainInteractorImpl
import com.positronen.maps.domain.interactor.MainInteractor
import com.positronen.maps.presentation.detail.DetailViewModel
import com.positronen.maps.presentation.main.MainViewModel
import com.positronen.maps.data.location.LocationDataSource
import com.positronen.maps.data.location.LocationDataSourceImpl
import com.positronen.maps.data.repository.MainRepositoryImpl
import com.positronen.maps.data.service.MainService
import com.positronen.maps.data.service.MainServiceImpl
import org.kodein.di.DI
import org.kodein.di.bind
import org.kodein.di.instance
import org.kodein.di.provider
import org.kodein.di.singleton

val mainModule = DI.Module("mainModule")  {

    bind<MainInteractor>() with singleton {
        MainInteractorImpl(instance())
    }

    bind<MainRepository>() with singleton {
        MainRepositoryImpl(instance())
    }

    bind<MainService>() with provider {
        MainServiceImpl(instance())
    }

    bind<LocationDataSource>() with provider {
        LocationDataSourceImpl()//instance())
    }

    bind<MainViewModel>() with singleton {
        MainViewModel(instance(), instance(), instance())
    }

    bind<DetailViewModel>() with singleton {
        DetailViewModel(instance())
    }
}