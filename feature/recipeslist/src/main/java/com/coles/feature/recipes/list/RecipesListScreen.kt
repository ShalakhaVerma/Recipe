package com.coles.feature.recipes.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coles.designcomponents.components.ErrorMessageCompose
import com.coles.designcomponents.components.ImagePlaceHolder
import com.coles.designcomponents.components.ScaffoldTopAppbar
import com.coles.designcomponents.theme.ColesApplicationTheme
import com.coles.entity.Details
import com.coles.entity.Ingredients
import com.coles.entity.RecipeItemEntity
import com.coles.feature.recipes.SharedRecipesViewModel
import com.coles.feature.recipes.SharedRecipesViewModel.RecipesListUiState
import com.coles.feature.recipeslist.R

private lateinit var recipesListViewModel: SharedRecipesViewModel

@Composable
internal fun RecipesListRoute(
    viewModel: SharedRecipesViewModel,
    onItemClick: () -> Unit
) {
    recipesListViewModel = viewModel
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
    ColesApplicationTheme(darkTheme = false, dynamicColor = false) {
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
            RecipeColumn(item = item, onItemClick = onItemClick)
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
        items(list) { item ->

            RecipeColumn(item = item, onItemClick)
        }
    }
}

@Composable
fun RecipeColumn(item: RecipeItemEntity, onItemClick: () -> Unit) {
    val readRecipeLabel = stringResource(id = R.string.card_click_label)
    Card(
        modifier = Modifier
            .semantics{onClick(label = readRecipeLabel, action = null)}
            .clickable {
                recipesListViewModel.setSelectedItem(item)
                onItemClick()
            }
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
        ) {
            ImagePlaceHolder(
                modifier = Modifier
                ,
                item.url
            )

            Spacer(modifier = Modifier.height(8.dp))
            item.title?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.semantics { contentDescription = it }
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            item.desc?.let {
                Text(
                    text = it,
                    textAlign = TextAlign.Left,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.semantics { contentDescription = it }
                )
            }
        }
    }
}

@Preview
@Composable
fun RecipeItemPreview() {
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
private val recipeList: List<RecipeItemEntity> = listOf(
    RecipeItemEntity(
        title = "Photo 1",
        desc = "Desc 1 kmlkska",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "1"),
        prepDetails = Details("Prep", "15mins"),
        cookingDetails = Details("Cooking", "8 hr"),
        ingredients = listOf(Ingredients("teest"))),
    RecipeItemEntity(
        title = "Photo 2",
        desc = "Desc 2 dadkdm",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "2"),
        prepDetails = Details("Prep", "10mins"),
        cookingDetails = Details("Cooking", "6 hr"),
        ingredients = listOf(Ingredients("teest"))),
    RecipeItemEntity(
        title = "Photo 3",
        desc = "Desc 3 ndndjkqnwd ndndsna",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "3"),
        prepDetails = Details("Prep", "30mins"),
        cookingDetails = Details("Cooking", "4 hr"),
        ingredients = listOf(Ingredients("teest"))),

)

