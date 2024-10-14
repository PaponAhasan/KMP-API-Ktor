package org.kmp.kmp_ktor.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.kmp.kmp_ktor.networking.repository.Graph
import org.kmp.kmp_ktor.networking.repository.Repository
import org.kmp.kmp_ktor.networking.util.Response

class HomeViewModel(
    private val repository: Repository = Graph.repository
) : ViewModel() {
    private val _homeState = MutableStateFlow(HomeState())
    val homeState = _homeState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.fetchMeals().collect { result ->
                when (result) {
                    is Response.Loading -> {
                        _homeState.update {
                            it.copy(
                                isLoading = true, error = null
                            )
                        }
                    }

                    is Response.Success -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false, error = null,
                                meals = result.data.meals
                            )
                        }
                    }

                    is Response.Error -> {
                        _homeState.update {
                            it.copy(
                                isLoading = false, error = result.error?.message
                            )
                        }
                    }
                }
            }
        }
    }
}