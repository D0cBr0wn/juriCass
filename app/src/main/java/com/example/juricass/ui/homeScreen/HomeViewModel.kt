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
import java.time.LocalDate

class HomeViewModel(): ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState: StateFlow<HomeState> = _homeState.asStateFlow()

    init {
        homeSearch()
    }

    fun homeSearch() {
        viewModelScope.launch {
            _homeState.update { currentState -> currentState.copy(isLoading = true) }
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

    fun setSearchQuery(query: String) {
        _homeState.update { currentState -> currentState.copy(searchQuery = query) }
    }

    fun setStartDate(date: LocalDate?) {
        var formattedDate = ""
        if(date != null) formattedDate = date.toString()// TODO : proper convesrion
        _homeState.update { currentState -> currentState.copy(startDate = formattedDate) }
    }

    fun setEndDate(date: LocalDate?) {
        var formattedDate = ""
        if(date != null) formattedDate = date.toString()// TODO : proper convesrion
        _homeState.update { currentState -> currentState.copy(endDate = formattedDate) }
    }
    fun setExact(exact: Boolean) {
        _homeState.update { currentState -> currentState.copy(exact = exact) }
    }

    fun resetFields() {
        setSearchQuery("")
        setStartDate(null)
        setEndDate(null)
        setExact(false)
    }
}