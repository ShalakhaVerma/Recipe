package com.coles.apiresponse

data class Recipes(
    val recipes: List<RecipeApiResponse>
)

data class RecipeApiResponse(val id: String, val title: String, val url: String, val desc: String)
