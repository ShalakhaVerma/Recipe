package com.coles.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ColesRecipesRepository {
    suspend fun fetchRecipes(): Flow<Result<List<RecipeItemEntity>>>
}