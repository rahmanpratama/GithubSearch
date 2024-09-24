package io.github.rahman.githubsearch.base

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.github.rahman.githubsearch.helper.EventData
import kotlinx.coroutines.CoroutineExceptionHandler
import java.net.SocketTimeoutException
import java.net.UnknownHostException

open class BaseViewModel : ViewModel() {

    val liveError: MutableLiveData<EventData<String>> by lazy { MutableLiveData<EventData<String>>() }
    val liveTimeout: MutableLiveData<EventData<String>> by lazy { MutableLiveData<EventData<String>>() }
    val liveErrorNetwork: MutableLiveData<EventData<String>> by lazy { MutableLiveData<EventData<String>>() }

    protected val exceptionHandler = CoroutineExceptionHandler { _, ex ->
        Log.e("Error","Error: ${ex.message}")
        when {
            ex is SocketTimeoutException -> {
                liveTimeout.value = EventData(
                    message = "Timeout"
                )
            }
            ex is UnknownHostException -> {
                liveErrorNetwork.value = EventData(
                    message = ex.message!!
                )
            }
            ex.message?.isNotEmpty() == true -> {
                liveError.value = EventData(
                    message = ex.message!!
                )
            }
        }
    }
}
