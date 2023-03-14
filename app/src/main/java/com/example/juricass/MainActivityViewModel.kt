package com.example.juricass

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juricass.data.state.MainActivityState
import com.example.juricass.network.JudilibreApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivityViewModel(): ViewModel() {
    private val _mainActivityState = MutableStateFlow(MainActivityState())
    val mainActivityState: StateFlow<MainActivityState> = _mainActivityState.asStateFlow()
    fun getHealthCheck() {
        viewModelScope.launch {
            _mainActivityState.update { currentState -> currentState.copy(isLoading = true) }
            JudilibreApi.retrofitService.healthcheck().onSuccess {
                Log.e("success", "success" + it)
                _mainActivityState.update { currentState -> currentState.copy(healthCheck = it.status) }
            }
            .onFailure {
                Log.e("failure", it.toString())
            }
            _mainActivityState.update { currentState -> currentState.copy(isLoading = false)}
        }
    }
}