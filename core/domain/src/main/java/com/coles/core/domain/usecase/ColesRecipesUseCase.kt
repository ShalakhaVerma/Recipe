package com.coles.core.domain.usecase

import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.core.domain.utils.ApiUseCaseNonParams
import com.coles.entity.RecipeItemEntity
import jakarta.inject.Inject

class ColesRecipesUseCase @Inject constructor(
    private val repository: ColesRecipesRepository
): ApiUseCaseNonParams<List<RecipeItemEntity>> {
    override suspend fun execute(): List<RecipeItemEntity>{
        return repository.fetchRecipes()
    }
}
