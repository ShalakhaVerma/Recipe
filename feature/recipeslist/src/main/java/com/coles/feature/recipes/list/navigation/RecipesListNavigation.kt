package com.coles.feature.recipes.list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.coles.feature.recipes.list.RecipesListRoute

const val recipesListScreenRoute = "recipesListScreen"

fun NavController.navigateToRecipesListScreen() {
    navigate(recipesListScreenRoute)
}

fun NavGraphBuilder.recipesListScreen(
    onItemClick: () -> Unit
) {
    composable(route = recipesListScreenRoute) {
        RecipesListRoute(
            onItemClick = onItemClick
        )
    }
}
