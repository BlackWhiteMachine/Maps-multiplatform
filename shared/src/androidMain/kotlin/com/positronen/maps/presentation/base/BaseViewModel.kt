package com.positronen.maps.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual abstract class BaseViewModel : ViewModel() {

    protected actual val baseCoroutineScope: CoroutineScope
        get() = viewModelScope
}