package com.coles.feature.recipes.list.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.coles.feature.recipes.list.RecipesListRoute
import com.coles.feature.recipes.SharedRecipesViewModel

const val recipesListScreenRoute = "recipesListScreen"

fun NavController.navigateToRecipesListScreen() {
    navigate(recipesListScreenRoute)
}

fun NavGraphBuilder.recipesListScreen(
    onItemClick: () -> Unit
) {
    composable(route = recipesListScreenRoute) { backStackEntry ->
        val viewModel: SharedRecipesViewModel = hiltViewModel(backStackEntry)
        RecipesListRoute(
            viewModel,
            onItemClick = onItemClick
        )
    }
}
