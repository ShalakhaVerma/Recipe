package com.coles.feature.recipes

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.coles.feature.recipes.SharedRecipesViewModel.RecipesListUiState
import com.coles.feature.recipes.data.recipeList
import com.coles.feature.recipes.list.RecipesListScreen
import org.junit.Rule
import org.junit.Test

class RecipeListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun recipeItemsTitle_whenScreenIsSuccess_isShown() {
        composeTestRule.setContent {
            RecipesListScreen(
                recipesListUiState = RecipesListUiState.HasRecipes(recipeList),
                onItemClick = {}
            )
        }
        val testRecipe = recipeList.first()

        composeTestRule
            .onAllNodes(hasScrollToNodeAction())
            .onFirst()
            .performScrollToNode(hasText(testRecipe.title!!))

        recipeList.forEach { item ->
            item.title?.let {
                composeTestRule
                    .onNodeWithText(it)
                    .assertExists()
                    .assertHasClickAction()
            }
        }

    }
}