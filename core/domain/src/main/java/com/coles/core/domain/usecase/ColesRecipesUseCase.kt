package com.coles.core.domain.usecase

import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.core.domain.utils.ApiUseCaseNonParams
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class ColesRecipesUseCase @Inject constructor(
    private val repository: ColesRecipesRepository
): ApiUseCaseNonParams<List<RecipeItemEntity>> {
    override suspend fun execute(): Flow<Result<List<RecipeItemEntity>>> {
        return repository.fetchRecipes()
    }
}