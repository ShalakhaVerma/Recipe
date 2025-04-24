package com.coles.feature.recipes.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coles.designcomponents.components.ScaffoldTopAppbar
import com.coles.designcomponents.theme.ColesApplicationTheme
import com.coles.entity.RecipeItemEntity
import com.coles.feature.recipes.list.RecipesListViewModel
import com.coles.feature.recipes.list.RecipesListViewModel.RecipesListUiState

@Composable
internal fun RecipeDetailRoute(
    viewModel: RecipesListViewModel = hiltViewModel(),
    onItemClick: () -> Unit
) {
    val recipesListUiState by viewModel.recipesListUiState.collectAsStateWithLifecycle()

    RecipeDetailScreen(
        recipesListUiState = recipesListUiState,
    )
}

val dummyItem = RecipeItemEntity(
    "1",
    "Photo 1",
    "desc 1",
    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ1jl_IhNcfipvMyNeo3nqLEWtYTi4V8EqmxgijwFXZd0_MPv1m95PZzB9-5K1IoLpARU0&usqp=CAU"
)

@Composable
fun RecipeDetailScreen(
    recipesListUiState: RecipesListUiState,
) {
    ColesApplicationTheme(darkTheme = false) {
        ScaffoldTopAppbar(
            title = "Recipes",
            containerColor = MaterialTheme.colorScheme.onPrimaryContainer
        ) { contentPadding ->

            RecipeDetail(
                dummyItem,
                contentPadding
            )
        }
    }
}

@Composable
fun RecipeDetail(
    item: RecipeItemEntity,
    contentPadding: PaddingValues
) {
    Surface(color = Color.White) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),

            ) {
            val (
                titleText, descText, recipeImage, divider1, servesLabelContainer, verticalDivider1,
                cookingLabelContainer, verticalDivider2, divider2,
                prepLabelContainer, _, _, _,
                ingredientsLabelText, ingredientsValueText
            ) = createRefs()

            val rightGuideline = createGuidelineFromStart(0.8f)

            item.title?.let {
                Text(
                    text = "Pork, fennel and Sausages with dndnkj dsmnd polenta",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(titleText) {
                            top.linkTo(parent.top, 16.dp)
                            start.linkTo(parent.start, 40.dp)
                            centerHorizontallyTo(parent)
                        }
                )
            }

            Text(
                text = "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn" +
                        "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn" +
                        "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn" +
                        "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn" +
                        "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(descText) {
                        top.linkTo(titleText.bottom, 32.dp)
                        start.linkTo(parent.start, 40.dp)
                        centerHorizontallyTo(parent)
                    }
            )

            Image(
                painter = painterResource(id = com.coles.designcomponents.R.drawable.ic_launcher_foreground),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                alignment = Alignment.Center,
                modifier = Modifier
                    .constrainAs(recipeImage) {
                        start.linkTo(parent.start, 24.dp)
                        top.linkTo(descText.bottom, 32.dp)
                        end.linkTo(rightGuideline, 16.dp)
                        width = Dimension.fillToConstraints
                        centerHorizontallyTo(parent)
                    }
                    .aspectRatio(3f / 2f),
            )

            HorizontalDivider(
                thickness = 2.dp, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(divider1) {
                        top.linkTo(recipeImage.bottom, 32.dp)
                        start.linkTo(parent.start, 40.dp)
                        centerHorizontallyTo(parent)
                    })


            ServeBox(
                "Serve", "8", modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(servesLabelContainer) {
                        start.linkTo(parent.start)
                        top.linkTo(divider1.bottom, 16.dp)
                    })

            ServeBox(
                "Prep", "15m", modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(prepLabelContainer) {
                        start.linkTo(verticalDivider1.end)
                        top.linkTo(divider1.bottom, 16.dp)
                    })

            ServeBox(
                "Cooking", "4h30m", modifier = Modifier
                    .wrapContentSize()
                    .constrainAs(cookingLabelContainer) {
                        start.linkTo(verticalDivider2.end)
                        top.linkTo(divider1.bottom, 16.dp)
                    })

            createHorizontalChain(
                servesLabelContainer,
                prepLabelContainer,
                cookingLabelContainer, chainStyle = ChainStyle.Spread
            )

            HorizontalDivider(
                thickness = 2.dp, modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(divider2) {
                        top.linkTo(servesLabelContainer.bottom, 16.dp)
                        start.linkTo(parent.start, 16.dp)
                        centerHorizontallyTo(parent)
                    })

            Text(
                text = "Ingredients",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(ingredientsLabelText) {
                        top.linkTo(divider2.bottom, 52.dp)
                        start.linkTo(parent.start)
                    }
            )

            Text(
                text = "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn" +
                        "This hdsjn asnkjdsnfkjcdsm bcsdkcbkjds ksdjnckn ",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(ingredientsValueText) {
                        top.linkTo(ingredientsLabelText.bottom, 16.dp)
                        start.linkTo(parent.start)
                    }
            )

        }
    }


}

@Composable
fun ServeBox(label: String, value: String, modifier: Modifier) {
    ConstraintLayout(modifier)
    {
        val (serveLText, serveVText) = createRefs()
        Text(
            text = label,
            color = Color(0xDD000000),
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(serveLText) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 16.dp)
                    end.linkTo(parent.end, 16.dp)
                }
        )

        Text(
            text = value,
            color = Color(0x8A000000),
            fontWeight = FontWeight.Medium,
            modifier = Modifier.constrainAs(serveVText) {
                start.linkTo(serveLText.start)
                end.linkTo(serveLText.end)
                width = Dimension.fillToConstraints
            }
        )

        createVerticalChain(serveLText, serveVText, chainStyle = ChainStyle.Packed)
    }
}


@Preview
@Composable
fun RecipeDetailPreview() {
    RecipeDetail(dummyItem, contentPadding = PaddingValues(20.dp))
}