package com.positronen.maps.presentation.detail

import com.positronen.maps.domain.model.PointDetailModel
import com.positronen.maps.domain.model.PointType
import com.positronen.maps.domain.model.detail.ChannelEventDetail
import com.positronen.maps.domain.interactor.MainInteractor
import com.positronen.maps.presentation.base.BaseViewModel
import com.positronen.maps.utils.Logger
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val interactor: MainInteractor
) : BaseViewModel() {

    private val dataMutableStateFlow = MutableStateFlow<PointDetailModel?>(null)
    val dataFlow: Flow<PointDetailModel>
        get() = dataMutableStateFlow.mapNotNull { it }

    val showProgressBarFlow: Flow<Boolean>
        get() = dataMutableStateFlow.map { it == null }

    private val eventChannel = Channel<ChannelEventDetail>()
    val eventFlow: Flow<ChannelEventDetail>
        get() = eventChannel.receiveAsFlow()

    fun onViewInit(id: String, pointType: PointType) {
        baseCoroutineScope.launch {
            interactor.point(id, pointType)
                .catch { error ->
                    Logger.exception(Exception(error.message))
                }
                .collect { place ->
                    dataMutableStateFlow.value = place
                }
        }
    }

    fun onShareAddressClicked() {
        val address: String = dataMutableStateFlow.value?.location?.address ?: return

        baseCoroutineScope.launch {
            eventChannel.send(ChannelEventDetail.ShareText(address))
        }
    }
}