package com.coles.apiresponse

data class Recipes(
    val recipes: List<RecipeApiResponse>
)

data class RecipeApiResponse(
    val dynamicTitle: String, val dynamicDescription: String,
    val dynamicThumbnail: String, val dynamicThumbnailAlt: String,
    val ingredients: List<Ingredients>, val recipeDetails: RecipeDetails
)

data class RecipeDetails(
    val amountLabel: String, val amountNumber: Int,
    val prepLabel: String, val prepNote: String,
    val cookingLabel: String, val cookingTime: String,
    val cookTimeAsMinutes: Int, val prepTimeAsMinutes: Int,
)

data class Ingredients(val ingredient: String)
