package com.example.superheroes_compose_navigation_3.helpers

sealed class Results<out T> {
    data class Success<out T>(val data: T) : Results<T>()
    data class Error(val exception: Throwable) : Results<Nothing>()
    data object Loading : Results<Nothing>()
}

