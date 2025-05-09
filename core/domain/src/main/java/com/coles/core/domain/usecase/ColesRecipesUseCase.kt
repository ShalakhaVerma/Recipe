package com.coles.core.domain.usecase

import android.app.Application
import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.core.domain.utils.ApiUseCaseNonParams
import com.coles.core.domain.utils.Result
import com.coles.entity.RecipeItemEntity
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow


class ColesRecipesUseCase @Inject constructor(
    private val repository: ColesRecipesRepository
) : ApiUseCaseNonParams<List<RecipeItemEntity>> {
    override suspend fun execute(application: Application): Flow<Result<List<RecipeItemEntity>>> {
        return repository.getDataFromJson(application, "recipes.json")
    }
}
