package com.example.mainactivity.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.coles.feature.recipes.list.navigation.navigateToRecipesListScreen
import com.coles.feature.recipes.list.navigation.recipesListScreen
import com.coles.feature.recipes.list.navigation.recipesListScreenRoute

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination:String = recipesListScreenRoute
){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        recipesListScreen(onItemClick = navController:: navigateToRecipesListScreen)
    }
}

fun NavController.popBackStackOrIgnore() {
    if (currentBackStackEntry?.lifecycle?.currentState == Lifecycle.State.RESUMED) {
        popBackStack()
    }
}