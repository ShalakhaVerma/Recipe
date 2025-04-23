package com.coles.core.data.repository

import com.coles.apiresponse.RecipeApiResponse
import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.entity.RecipeItemEntity
import jakarta.inject.Inject

class ColesRecipesRepositoryImpl @Inject constructor() : ColesRecipesRepository {
    override suspend fun fetchRecipes(): List<RecipeItemEntity> {
        return recipeList.map {
            RecipeItemEntity(
                id = it.id,
                title = it.description,
                url = it.url
            )
        }

    }

    val recipeList = listOf(
        RecipeApiResponse(
            1,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
            "Photo 1"
        ),
        RecipeApiResponse(
            2,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
            "Photo 2"
        ),
        RecipeApiResponse(
            3,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
            "Photo 3"
        ),
        RecipeApiResponse(
            4,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
            "Photo 4"
        ),
        RecipeApiResponse(
            5,
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
            "Photo 5"
        )
    )
}