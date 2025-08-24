package com.example.superheroes_compose_navigation_3.features.Search

import com.example.data.search.SearchHeroesRepository
import com.example.superheroes_compose_navigation_3.helpers.DispatcherProvider
import com.example.superheroes_compose_navigation_3.helpers.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchHeroesRepository: SearchHeroesRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    operator fun invoke(searchQuery: String) = flow {
        emit(Results.Loading)
        val response = CoroutineScope(dispatcherProvider.io).async {
            searchHeroesRepository.searchHeroes(searchQuery)
        }
        val result = response.await()
        result.onSuccess { heroes ->
            emit(Results.Success(heroes.results))
        }
        result.onFailure {
            emit(Results.Error(it))
        }
    }
}