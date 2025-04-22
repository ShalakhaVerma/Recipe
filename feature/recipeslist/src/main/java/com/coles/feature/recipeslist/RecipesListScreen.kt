
package com.coles.feature.recipeslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coles.feature.recipeslist.RecipesListViewModel.RecipesListUiState
import androidx.hilt.navigation.compose.hiltViewModel
import com.coles.designcomponents.components.ScaffoldTopAppbar
import com.coles.designcomponents.components.ImagePlaceHolder

import com.coles.designcomponents.theme.ColesApplicationTheme
import com.coles.designcomponents.theme.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview

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
    onItemClick: () -> Unit,
) {
    ScaffoldTopAppbar(
        title = "Recipes",
        containerColor = MaterialTheme.colorContainer
    ) {
        when (recipesListUiState) {
            is RecipesListUiState.Error -> Text(text = "Error")
            is RecipesListUiState.Loading -> CircularProgressIndicator()
            is RecipesListUiState.HasRecipes -> RecipeItem()
            RecipesListUiState.ListEmpty -> Text(text = "No Recipes Found")
        }
    }
}

    @Composable
    fun RecipeItem() {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .height(400.dp)  // Adjust height for better scroll experience
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(8.dp)
        ) {
            items(photoList, key = { it.id }) { photo ->
                PhotoItem(photo = photo)
            }
        }
    }

    @Composable
    fun PhotoItem(photo: Photo) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .background(Color.LightGray)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ImagePlaceHolder(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)  // Ensure consistent aspect ratio
                        .clip(RoundedCornerShape(8.dp)),
                    photo.url
                )

                Spacer(modifier = Modifier.height(4.dp))
                Text(text = photo.description)
            }
        }
    }

    @Preview
    @Composable
    fun RecipesListScreenPreview() {
         RecipeItem()
    }


