package com.positronen.maps.android.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.positronen.maps.di.Inject
import com.positronen.maps.presentation.main.MainViewModel
import java.security.Provider
//import javax.inject.Inject
//import javax.inject.Provider

class ViewModelFactory (
 //   private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return Inject.instance<MainViewModel>() as T
    }

//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        val viewModelProvider = viewModels[modelClass]
//            ?: throw IllegalArgumentException("model class $modelClass not found")
//        return viewModelProvider.get() as T
//    }
}
