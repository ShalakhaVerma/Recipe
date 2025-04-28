package com.coles.feature.recipes

import android.app.Application
import android.content.Context
import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.core.domain.utils.Result
import com.coles.entity.RecipeItemEntity
import com.google.gson.JsonIOException
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeColesRecipesRepository: ColesRecipesRepository {

    private val recipeItemFlow: MutableSharedFlow<Result<List<RecipeItemEntity>>> =
        MutableSharedFlow(replay = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    override suspend fun getDataFromJson(
        context: Context,
        filename: String
    ): Flow<Result<List<RecipeItemEntity>>> = recipeItemFlow


    /**
     * A test-only API to allow controlling the list of topics from tests.
     */
    fun setItems(items: Result<List<RecipeItemEntity>>) {
        recipeItemFlow.tryEmit(items)
    }

    fun throwError() {
        recipeItemFlow.tryEmit(Result.Error("JsonIOException", 1))
    }
}