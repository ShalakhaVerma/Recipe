package com.coles.feature.recipes.detail.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.coles.feature.recipes.detail.RecipeDetailRoute
import com.coles.feature.recipes.SharedRecipesViewModel

const val recipeDetailScreenRoute = "recipeDetailScreen"

fun NavController.navigateToRecipesDetailScreen() {
    navigate(recipeDetailScreenRoute)
}

fun NavGraphBuilder.recipeDetailScreen(
    navController: NavHostController,
    onBackBtnClick: () -> Unit,
) {
    composable(route = recipeDetailScreenRoute) {
        val viewModel: SharedRecipesViewModel =
            if (navController.previousBackStackEntry != null) hiltViewModel(
                navController.previousBackStackEntry!!
            ) else hiltViewModel()

        RecipeDetailRoute(
            viewModel,
            onBackBtnClick = onBackBtnClick
        )
    }
}