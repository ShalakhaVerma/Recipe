package com.coles.feature.recipes.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.heading
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coles.designcomponents.components.ImagePlaceHolder
import com.coles.designcomponents.theme.ColesApplicationTheme
import com.coles.entity.Details
import com.coles.entity.Ingredients
import com.coles.entity.RecipeItemEntity
import com.coles.feature.recipes.SharedRecipesViewModel
import com.coles.feature.recipes.SharedRecipesViewModel.SelectedRecipeUiState
import com.coles.feature.recipeslist.R

@Composable
internal fun RecipeDetailRoute(
    viewModel: SharedRecipesViewModel, onBackBtnClick: () -> Unit
) {
    val selectedRecipeUiState by viewModel.selectedRecipeUiState.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        selectedRecipeUiState = selectedRecipeUiState
    )
}

@Composable
fun RecipeDetailScreen(
    selectedRecipeUiState: SelectedRecipeUiState,
) {
    ColesApplicationTheme(darkTheme = false) {
        when (selectedRecipeUiState) {
            is SelectedRecipeUiState.HasRecipe -> {
                RecipeDetail(
                    selectedRecipeUiState.item
                )

            }

            SelectedRecipeUiState.Loading -> TODO()
        }
    }
}

@Composable
fun RecipeDetail(
    item: RecipeItemEntity
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(top = 52.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {
        Column {
            Sublist1(item)
            item.ingredients?.let { list ->
                list.forEach { value ->
                    value.ingredients?.let {
                        Text(
                            text = it,
                            color = MaterialTheme.colorScheme.secondary,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Start
                        )
                    }
                }
            }
        }

    }
}

@Composable
fun Sublist1(item: RecipeItemEntity) {
    item.title?.let {
        Text(
            text = it,
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .semantics { heading() })
    }
    Spacer(modifier = Modifier.height(8.dp))

    item.desc?.let {
        Text(
            text = it,
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .semantics { contentDescription = it })
    }

    Spacer(modifier = Modifier.height(32.dp))

    Surface() {

        ImagePlaceHolder(
            modifier = Modifier.aspectRatio(3f / 2f)  // Ensure consistent aspect ratio
            , item.url
        )
    }
    Spacer(modifier = Modifier.height(32.dp))

    HorizontalDivider(
        thickness = 2.dp, modifier = Modifier.fillMaxWidth()
    )


    ConstraintLayout(modifier = Modifier.padding(top = 16.dp)) {

        val (divider1, servesLabelContainer, verticalDivider1, cookingLabelContainer, verticalDivider2, divider2, prepLabelContainer, ingredientsLabelText, ingredientsValueText) = createRefs()

        val amountNote = item.amountDetails?.note
        val amountLabel = item.amountDetails?.label
        if (!amountNote.isNullOrEmpty() && !amountLabel.isNullOrEmpty()) {
            ServeBox(
                amountLabel,
                amountNote,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(servesLabelContainer) {
                        start.linkTo(parent.start)
                        top.linkTo(divider1.bottom, 16.dp)
                    })

        }
        val prepNote = item.prepDetails?.note
        val prepLabel = item.prepDetails?.label
        if (!prepNote.isNullOrEmpty() && !prepLabel.isNullOrEmpty()) {
            ServeBox(
                prepLabel,
                prepNote,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(prepLabelContainer) {
                        start.linkTo(verticalDivider1.end)
                        top.linkTo(divider1.bottom, 16.dp)
                    })
        }

        val cookingNote = item.cookingDetails?.note
        val cookingLabel = item.cookingDetails?.label
        if (!cookingNote.isNullOrEmpty() && !cookingLabel.isNullOrEmpty()) {
            ServeBox(
                cookingLabel,
                cookingNote,
                modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(cookingLabelContainer) {
                        start.linkTo(verticalDivider2.end)
                        top.linkTo(divider1.bottom, 16.dp)
                    })
        }

        createHorizontalChain(
            servesLabelContainer,
            prepLabelContainer,
            cookingLabelContainer,
            chainStyle = ChainStyle.Spread
        )
        HorizontalDivider(
            thickness = 2.dp, modifier = Modifier
                .fillMaxWidth()
                .constrainAs(divider2) {
                    top.linkTo(servesLabelContainer.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                    centerHorizontallyTo(parent)
                })

        val ingredientsContentDesc = stringResource(R.string.ingredientsContDesc)
        Text(
            text = stringResource(R.string.ingredients),
            color = Color.Black,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 8.dp
                )
                .constrainAs(ingredientsLabelText) {
                    top.linkTo(divider2.bottom, 52.dp)
                    start.linkTo(parent.start)
                }
                .semantics { contentDescription = ingredientsContentDesc })
    }
}

@Composable
fun ServeBox(label: String, value: String, modifier: Modifier) {
    ConstraintLayout(modifier.semantics(mergeDescendants = true) {}) {

        val (serveLText, serveVText) = createRefs()
        Text(
            text = label,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(serveLText) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                })

        Text(
            text = value,
            color = MaterialTheme.colorScheme.scrim,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(serveVText) {
                start.linkTo(serveLText.start)
                end.linkTo(serveLText.end)
                width = Dimension.fillToConstraints
            })

        createVerticalChain(serveLText, serveVText, chainStyle = ChainStyle.Packed)
    }
}

@Preview
@Composable
fun RecipeDetailPreview() {
    val dummyItem = RecipeItemEntity(
        title = "Photo 1",
        desc = "desc 1",
        url = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU",
        amountDetails = Details("Serve", "2"),
        prepDetails = Details("Prep", "10mins"),
        cookingDetails = Details("Cooking", "6 hr"),
        ingredients = listOf(Ingredients("test"))
    )

    RecipeDetail(dummyItem)
}
