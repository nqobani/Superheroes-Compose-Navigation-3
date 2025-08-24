package com.example.superheroes_compose_navigation_3.features.Search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.heroes.Result
import com.example.superheroes_compose_navigation_3.helpers.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchHeroesViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
): ViewModel() {

    var searchQuery = mutableStateOf("")

    private val _heroes = MutableStateFlow<List<Result>>(emptyList())
    val heroes: StateFlow<List<Result>> = _heroes

    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun searchHeroes(search: String) {
        viewModelScope.launch {
            searchUseCase(search).collect { results ->
                when(results) {
                    is Results.Success -> {
                        error.value = null
                        _heroes.value = results.data
                        isLoading.value = false
                    }
                    is Results.Error -> {
                        error.value = results.exception.message
                        isLoading.value = false
                    }
                    is Results.Loading -> {
                        error.value = null
                        isLoading.value = true
                    }
                }
            }
        }
    }
}