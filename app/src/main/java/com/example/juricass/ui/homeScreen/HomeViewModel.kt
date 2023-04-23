package com.example.juricass.ui.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juricass.data.state.HomeState
import com.example.juricass.network.JudilibreApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URLEncoder

class HomeViewModel(): ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    fun homeSearch() {
        viewModelScope.launch {
            _homeState.update { currentState -> currentState.copy(isLoading = true) }
            //val encodedQuery = URLEncoder.encode("propriété", "UTF-8") + "&resolve_references=true"
            JudilibreApi.retrofitService.search(query = "propriété").onSuccess {
                _homeState.update { currentState -> currentState.copy(searchPage = it) }
            }
                .onFailure {
                    _homeState.update { currentState -> currentState.copy(error = it.localizedMessage) }
                    Log.e("API Error", it.toString())
                }
            _homeState.update { currentState -> currentState.copy(isLoading = false)}
        }
    }
}