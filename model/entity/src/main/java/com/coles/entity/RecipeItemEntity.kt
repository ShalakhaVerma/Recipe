package com.coles.entity

data class RecipeItemEntity(
    val title: String?,
    val desc: String?,
    val url: String?,
    val amountDetails: Details?,
    val prepDetails: Details?,
    val cookingDetails: Details?,
    val ingredients: List<Ingredients>?
)
data class Details(val label: String?, val note: String?)
data class Ingredients(val ingredients: String?)