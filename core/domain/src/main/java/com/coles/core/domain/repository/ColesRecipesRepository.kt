package com.coles.core.domain.repository

import com.coles.core.domain.utils.Result
import com.coles.entity.RecipeItemEntity
import kotlinx.coroutines.flow.Flow

interface ColesRecipesRepository {
    suspend fun fetchRecipes(): List<RecipeItemEntity>
}