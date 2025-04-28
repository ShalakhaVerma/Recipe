package com.coles.core.domain.repository

import android.content.Context
import com.coles.core.domain.utils.Result
import com.coles.entity.RecipeItemEntity
import kotlinx.coroutines.flow.Flow

interface ColesRecipesRepository {
    suspend fun getDataFromJson(context: Context, filename: String): Flow<Result<List<RecipeItemEntity>>>
}