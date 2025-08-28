package com.example.superheroes_compose_navigation_3.features.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.heroes.HeroResponse
import com.example.superheroes_compose_navigation_3.helpers.Results
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeroViewModel @Inject constructor(private val getHeroUseCase: GetHeroUseCase) : ViewModel() {

    private val _hero = MutableStateFlow<HeroResponse?>(null)
    val hero: StateFlow<HeroResponse?> = _hero
    val isLoading = mutableStateOf(false)
    val error = mutableStateOf<String?>(null)

    fun getHero(id: String) {
        viewModelScope.launch {
            getHeroUseCase(id).collect { results ->
                when (results) {
                    is Results.Error -> {
                        error.value = results.exception.message
                        isLoading.value = false
                    }
                    Results.Loading -> {
                        error.value = null
                        isLoading.value = true
                    }
                    is Results.Success<HeroResponse> -> {
                        _hero.value = results.data
                        error.value = null
                        isLoading.value = false
                    }
                }
            }
        }
    }
}