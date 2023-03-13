package com.example.juricass

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
            val healthResult = JudilibreApi.retrofitService.healthcheck()
            _mainActivityState.update { currentState -> currentState.copy(healthCheck = healthResult) }
        }
    }
}