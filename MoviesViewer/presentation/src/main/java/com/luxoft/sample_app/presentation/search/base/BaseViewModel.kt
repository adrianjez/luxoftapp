package com.luxoft.sample_app.presentation.search.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luxoft.domain.model.ResultWrapper
abstract class BaseViewModel : ViewModel() {
    sealed class LoadingState {
        object Displayed : LoadingState()
        object Gone : LoadingState()
    }

    val messageLiveData = MutableLiveData<String>()
    val loadingState = MutableLiveData<LoadingState>()

    protected fun <T> handleErrorIfNeeded(
            resultWrapper: ResultWrapper<T>,
            successCallback: (data: T) -> Unit
    ) {
        when (resultWrapper) {
            is ResultWrapper.NetworkError -> {
                messageLiveData.postValue("Network Error")
                loadingState.value = LoadingState.Gone
            }
            is ResultWrapper.GenericError -> {
                messageLiveData.postValue(resultWrapper.error)
                loadingState.value = LoadingState.Gone
            }
            is ResultWrapper.Success -> {
                successCallback(resultWrapper.value)
                loadingState.value = LoadingState.Gone
            }
        }

    }
}