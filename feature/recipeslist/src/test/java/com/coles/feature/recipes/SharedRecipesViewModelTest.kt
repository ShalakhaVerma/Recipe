package com.coles.feature.recipes

import android.app.Application
import com.coles.core.domain.usecase.ColesRecipesUseCase
import com.coles.core.domain.utils.Result
import com.coles.entity.RecipeItemEntity
import com.coles.feature.recipes.SharedRecipesViewModel.RecipesListUiState
import com.coles.testing.MainDispatcherRule
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.test.assertIsNot

class SharedRecipesViewModelTest {

    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private val fakeColesRecipesRepository = FakeColesRecipesRepository()
    private lateinit var recipeUseCase: ColesRecipesUseCase
    private val context:  Application = mock(Application::class.java)

    private lateinit var viewModel: SharedRecipesViewModel
    @Before
    fun setup() {
        recipeUseCase = ColesRecipesUseCase(fakeColesRecipesRepository)
        viewModel = SharedRecipesViewModel(context, recipeUseCase)
    }

    @org.junit.Test
    fun recipesListUiState_whenInitialized_thenShowLoading() = runBlocking {
        assertEquals(RecipesListUiState.Loading, viewModel.recipesListUiState.value)
    }

    @Test
    fun recipesListUiState_whenSuccess_matchesListFromRepository() = runBlocking {
        fakeColesRecipesRepository.setItems(Result.Success(testInputItems))
        recipeUseCase.execute(context)
        val state = viewModel.recipesListUiState.value
        assertEquals(state, RecipesListUiState.HasRecipes(testInputItems))
        assertIsNot<RecipesListUiState.Loading>(state)
    }

    @Test
    fun recipesListUiState_whenError_matchesListFromRepository() = runTest {
        fakeColesRecipesRepository.throwError()
        recipeUseCase.execute(context)
        val state = viewModel.recipesListUiState.value
        assertEquals(state, RecipesListUiState.Error("JsonIOException"))
        assertIsNot<RecipesListUiState.Loading>(state)
    }


    val testInputItems = listOf(
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


}