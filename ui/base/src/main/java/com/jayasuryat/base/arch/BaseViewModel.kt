package com.jayasuryat.base.arch

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob

public open class BaseViewModel : ViewModel() {

    @Suppress("PropertyName")
    protected val TAG: String by lazy { javaClass.simpleName }

    protected val _obsIsDataLoading: MutableLiveData<Boolean> = MutableLiveData()
    public val obsIsDataLoading: LiveData<Boolean> get() = _obsIsDataLoading

    private val jobDelegate: Lazy<Job> = lazy { SupervisorJob() }
    private val job by jobDelegate
    protected val ioScope: CoroutineScope by lazy { CoroutineScope(Dispatchers.IO + job) }

    protected suspend fun doWhileLoading(logic: suspend () -> Unit) {
        _obsIsDataLoading.postValue(true)
        logic()
        _obsIsDataLoading.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        if (jobDelegate.isInitialized()) jobDelegate.value.cancel()
    }
}