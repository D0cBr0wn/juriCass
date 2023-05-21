package com.example.juricass.ui.homeScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.juricass.data.helpers.convertDatesForQuery
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
            _homeState.update { currentState -> currentState.copy(isLoading = true, error = null, searchPage = null) }

            JudilibreApi.retrofitService.search(
                query = _homeState.value.searchQuery,
                startDate = _homeState.value.startDate,
                endDate = _homeState.value.endDate,
                exact = _homeState.value.exact
            ).onSuccess {
                _homeState.update { currentState -> currentState.copy(searchPage = it, isLoading = false, menuExpanded = false) }
            }
            .onFailure {
                _homeState.update { currentState -> currentState.copy(error = it.localizedMessage, isLoading = false, menuExpanded = false) }
                Log.e("API Error", it.toString())
            }
        }
    }

    fun setSearchQuery(query: String) {
        _homeState.update { currentState -> currentState.copy(searchQuery = query) }
    }

    fun setStartDate(date: LocalDate?) {
        var formattedDate =  if(date !== null) convertDatesForQuery(date) else null
        _homeState.update { currentState -> currentState.copy(startDate = formattedDate) }
    }

    fun setEndDate(date: LocalDate?) {
        var formattedDate =  if(date !== null) convertDatesForQuery(date) else null
        _homeState.update { currentState -> currentState.copy(endDate = formattedDate) }
    }
    fun setExact(exact: Boolean) {
        val operator = if(exact) "exact" else null
        _homeState.update { currentState -> currentState.copy(exact = operator) }
    }

    fun resetFields() {
        setSearchQuery("")
        setStartDate(null)
        setEndDate(null)
        setExact(false)
    }

    fun onMenuTriggerClick() {
        _homeState.update { currentState -> currentState.copy(menuExpanded = !_homeState.value.menuExpanded) }
    }

    fun onCloseMenu() {
        _homeState.update { currentState -> currentState.copy(menuExpanded = false) }
    }
}