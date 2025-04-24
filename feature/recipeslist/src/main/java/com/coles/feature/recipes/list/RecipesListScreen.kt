package com.coles.feature.recipes.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coles.designcomponents.components.ErrorMessageCompose
import com.coles.designcomponents.components.ScaffoldTopAppbar
import com.coles.designcomponents.theme.ColesApplicationTheme
import com.coles.entity.RecipeItemEntity
import com.coles.feature.recipes.list.RecipesListViewModel.RecipesListUiState
import com.coles.feature.recipes.list.components.RecipeColumn

@Composable
internal fun RecipesListRoute(
    viewModel: RecipesListViewModel = hiltViewModel(),
    onItemClick: () -> Unit
) {
    val recipesListUiState by viewModel.recipesListUiState.collectAsStateWithLifecycle()

    RecipesListScreen(
        recipesListUiState = recipesListUiState,
        onItemClick = onItemClick,
    )
}

@Composable
fun RecipesListScreen(
    recipesListUiState: RecipesListUiState,
    onItemClick: () -> Unit
) {
    ColesApplicationTheme(darkTheme = false,dynamicColor = false ) {
        ScaffoldTopAppbar(
            title = "Recipes",
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) { contentPadding ->
            when (recipesListUiState) {
                is RecipesListUiState.Error -> {
                    ErrorMessageCompose(
                        message = recipesListUiState.message,
                    )
                }

                is RecipesListUiState.Loading -> CircularProgressIndicator()
                is RecipesListUiState.HasRecipes -> RecipeItem(
                    recipesListUiState.list,
                    onItemClick,
                    contentPadding
                )

                RecipesListUiState.ListEmpty -> Text(text = "No Recipes Found")
            }
        }
    }
}

@Composable
fun RecipeItem(
    list: List<RecipeItemEntity>,
    onItemClick: () -> Unit,
    contentPadding: PaddingValues
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeLayout(list, onItemClick, contentPadding)
        }

        else -> {
            PortraitLayout(list, onItemClick, contentPadding)
        }
    }
}

@Composable
fun PortraitLayout(
    list: List<RecipeItemEntity>,
    onItemClick: () -> Unit,
    contentPadding: PaddingValues
) {
        LazyColumn(
            contentPadding = contentPadding
        ) {
            items(items = list) { item ->
                RecipeColumn(item = item)
            }
        }
}

@Composable
fun LandscapeLayout(
    list: List<RecipeItemEntity>,
    onItemClick: () -> Unit,
    contentPadding: PaddingValues
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = contentPadding,
        modifier = Modifier
            .fillMaxSize()  // Adjust height for better scroll experience
            .background(Color.White, RoundedCornerShape(12.dp))
    ) {
        items(items = list, key = { it.id }) { item ->

            RecipeColumn(item = item)
        }
    }
}


@Preview
@Composable
fun RecipeItemPreview() {
    val recipeList = listOf(
        RecipeItemEntity(
            "1",
            "Photo 1",
            "Desc 1 kmlkska",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU"
        ),
        RecipeItemEntity(
            "2",
            "Photo 2",
            "Desc 2 dadkdm",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU"
        ),
        RecipeItemEntity(
            "3",
            "Photo 3",
            "Desc 3 ndndjkqnwd ndndsna",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU"
        )
    )

    ScaffoldTopAppbar(
        title = "Recipes",
        containerColor = MaterialTheme.colorScheme.primaryContainer,
    ) {
        RecipeItem(
            list = recipeList,
            onItemClick = {},
            contentPadding = PaddingValues(20.dp)
        )
    }
}

