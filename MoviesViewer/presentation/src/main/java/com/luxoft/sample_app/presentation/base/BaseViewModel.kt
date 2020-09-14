package com.luxoft.sample_app.presentation.base

import android.content.res.Resources
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luxoft.domain.model.ResultWrapper
import com.luxoft.sample_app.R

abstract class BaseViewModel constructor(private val resources: Resources) : ViewModel() {

    val errorMessage = ObservableField<String>()
    val hasError = ObservableField(false)

    val isLoadingDisplayed = MutableLiveData(false)

    fun retry() {}

    protected fun <T> handleErrorIfNeeded(
            resultWrapper: ResultWrapper<T>,
            successCallback: (data: T) -> Unit
    ) {
        when (resultWrapper) {
            is ResultWrapper.NetworkError -> {
                hasError.set(true)
                errorMessage.set(resources.getString(R.string.network_error))
            }
            is ResultWrapper.GenericError -> {
                hasError.set(true)
                errorMessage.set(resultWrapper.error)
            }
            is ResultWrapper.Success -> {
                hasError.set(false)
                successCallback(resultWrapper.value)
            }
        }
        isLoadingDisplayed.postValue(false)
    }
}