package com.example.superheroes_compose_navigation_3.features.details

import com.example.data.get_hero.HeroRepository
import com.example.superheroes_compose_navigation_3.helpers.DispatcherProvider
import com.example.superheroes_compose_navigation_3.helpers.Results
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetHeroUseCase @Inject constructor(
    private val heroRepository: HeroRepository,
    private val dispatcherProvider: DispatcherProvider
) {
    operator fun invoke(id: String) = flow {
        emit(Results.Loading)
        val response = CoroutineScope(dispatcherProvider.io).async {
            heroRepository.getHero(id)
        }
        val result = response.await()
        result.onSuccess { hero ->
            emit(Results.Success(hero))
        }
        result.onFailure {
            emit(Results.Error(it))
        }
    }
}