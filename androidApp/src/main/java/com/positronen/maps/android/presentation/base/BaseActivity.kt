package com.positronen.maps.android.presentation.base

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.positronen.maps.di.Inject
import java.lang.reflect.ParameterizedType

abstract class BaseActivity<VM : ViewModel> : AppCompatActivity() {

    private val viewModelFactory: ViewModelProvider.Factory = ViewModelFactory()

    private val _viewModel by lazy {
        ViewModelProvider(
            this,
            viewModelFactory
        )[getViewModelClass()]
    }

    open val viewModel: VM
        get() = _viewModel

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelClass(): Class<VM> {
        val type = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments.toList().last()
        return type as Class<VM>
    }

    protected val baseCoroutineScope: LifecycleCoroutineScope
        get() = lifecycleScope
}