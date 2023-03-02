package com.positronen.maps.android.presentation.base

import android.os.Bundle
import com.positronen.maps.presentation.mvi.BaseEvent
import com.positronen.maps.presentation.mvi.BaseIntent
import com.positronen.maps.presentation.mvi.BaseMVIViewModel
import com.positronen.maps.presentation.mvi.BaseState

abstract class BaseMVIActivity<
        SB : BaseState,
        EM: BaseEvent,
        IB: BaseIntent,
        VM : BaseMVIViewModel<SB, EM, IB>
        >
    : BaseActivity<VM>() {

    abstract fun handleState(state: SB)

    abstract fun handleEvent(state: EM)

    protected val mviViewModel: BaseMVIViewModel<SB, EM, IB>
        get() = viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseCoroutineScope.launchWhenStarted {
            viewModel.stateFlow.collect(::handleState)
        }

        baseCoroutineScope.launchWhenStarted {
            viewModel.sharedFlow.collect(::handleEvent)
        }
    }
}