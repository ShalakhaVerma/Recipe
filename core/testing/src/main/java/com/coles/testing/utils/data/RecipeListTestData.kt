package com.coles.testing.utils.data

import com.coles.entity.Details
import com.coles.entity.Ingredients
import com.coles.entity.RecipeItemEntity

val recipeList: List<RecipeItemEntity> = listOf(
    RecipeItemEntity(
        title = "Photo 1",
        desc = "Desc 1 kmlkska",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "1"),
        prepDetails = Details("Prep", "15mins"),
        cookingDetails = Details("Cooking", "8 hr"),
        ingredients = listOf(Ingredients("teest"))),
    RecipeItemEntity(
        title = "Photo 2",
        desc = "Desc 2 dadkdm",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "2"),
        prepDetails = Details("Prep", "10mins"),
        cookingDetails = Details("Cooking", "6 hr"),
        ingredients = listOf(Ingredients("teest"))),
    RecipeItemEntity(
        title = "Photo 3",
        desc = "Desc 3 ndndjkqnwd ndndsna",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "3"),
        prepDetails = Details("Prep", "30mins"),
        cookingDetails = Details("Cooking", "4 hr"),
        ingredients = listOf(Ingredients("teest")))
)