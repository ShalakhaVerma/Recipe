package com.coles.feature.recipes

import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollToNodeAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.coles.feature.recipes.SharedRecipesViewModel.RecipesListUiState
import com.coles.feature.recipes.list.RecipesListScreen
import com.coles.testing.utils.data.recipeList
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipeListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun whenSuccessListOfRecipesIsShown() {
        composeTestRule.setContent {
            RecipesListScreen(
                recipesListUiState = RecipesListUiState.HasRecipes(recipeList),
                onItemClick = {}
            )
        }

        //  Check if all items are displayed
        recipeList.forEach { item ->
            item.title?.let {
                composeTestRule
                    .onNodeWithText(it)
                    .assertExists()
                    .assertIsDisplayed()
                    .assertHasClickAction()
            }
        }

        // Test Case 2: Scroll to a specific item and check if it's displayed
        val testRecipe = recipeList.first()
        composeTestRule
            .onAllNodes(hasScrollToNodeAction())
            .onFirst()
            .performScrollToNode(hasText(testRecipe.title!!))
            .assertIsDisplayed()

    }

    @Test
    fun whenSuccessButEmptyListErrorIsShown() {
        // Test Case 4: Test with an empty list
        composeTestRule.setContent {
            RecipesListScreen(
                recipesListUiState = RecipesListUiState.ListEmpty,
                onItemClick = {}
            )
        }
        composeTestRule.onNodeWithText("No Recipes Found")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun whenFailErrorMessageIsShown() {
        // Test Case 4: Test with an empty list
        composeTestRule.setContent {
            RecipesListScreen(
                recipesListUiState = RecipesListUiState.Error("Some error"),
                onItemClick = {}
            )
        }
        composeTestRule.onNodeWithText("Some error")
            .assertExists()
            .assertIsDisplayed()
    }

}