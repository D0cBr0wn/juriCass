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
                _mainActivityState.update { currentState -> currentState.copy(healthCheck = it.status) }
            }
            .onFailure {
                _mainActivityState.update { currentState -> currentState.copy(error = it.localizedMessage) }
                Log.e("API Error", it.toString())
            }
            _mainActivityState.update { currentState -> currentState.copy(isLoading = false)}
        }
    }

    fun getDecision(id: String) {
        viewModelScope.launch {
            _mainActivityState.update { currentState -> currentState.copy(isLoading = true) }
            JudilibreApi.retrofitService.getDecision(id).onSuccess {
                _mainActivityState.update { currentState -> currentState.copy(decision = it) }
            }
                .onFailure {
                    _mainActivityState.update { currentState -> currentState.copy(error = it.localizedMessage) }
                    Log.e("API Error", it.toString())
                }
            _mainActivityState.update { currentState -> currentState.copy(isLoading = false)}
        }
    }

    fun search(query: String) {
        viewModelScope.launch {
            _mainActivityState.update { currentState -> currentState.copy(isLoading = true) }
            JudilibreApi.retrofitService.search(query = query).onSuccess {
                _mainActivityState.update { currentState -> currentState.copy(searchPage = it) }
            }
                .onFailure {
                    _mainActivityState.update { currentState -> currentState.copy(error = it.localizedMessage) }
                    Log.e("API Error", it.toString())
                }
            _mainActivityState.update { currentState -> currentState.copy(isLoading = false)}
        }
    }
}