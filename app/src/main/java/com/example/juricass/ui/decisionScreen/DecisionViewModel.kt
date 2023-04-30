package com.example.juricass.ui.decisionScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.juricass.data.state.DecisionState
import com.example.juricass.network.JudilibreApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DecisionViewModel(decisionId: String?): ViewModel() {
    private val _decisionState = MutableStateFlow(DecisionState())
    val decisionState: StateFlow<DecisionState> = _decisionState.asStateFlow()
    //private val decisionId: String = checkNotNull(savedStateHandle["decisionId"])

    init {
        if(decisionId != null) getDecision(decisionId)
    }
    fun getDecision(id: String) {
        viewModelScope.launch {
            _decisionState.update { currentState -> currentState.copy(isLoading = true)}
            JudilibreApi.retrofitService.getDecision(id = id).onSuccess {
                _decisionState.update { currentState -> currentState.copy(decision = it) }
            }
            .onFailure {
                _decisionState.update { currentState -> currentState.copy(error = it.localizedMessage) }
                Log.e("API Error", it.toString())
            }
            _decisionState.update { currentState -> currentState.copy(isLoading = false)}
        }
    }

}

class DecisionViewModelFactory(private val decisionId: String) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T = DecisionViewModel(decisionId = decisionId) as T
}

