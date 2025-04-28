package com.coles.feature.recipes

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coles.core.domain.usecase.ColesRecipesUseCase
import com.coles.core.domain.utils.Result
import com.coles.entity.RecipeItemEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SharedRecipesViewModel @Inject constructor(
    private val application: Application,
    private val recipesListUseCase: ColesRecipesUseCase
) : ViewModel() {

    private val _recipesListUiState =
        MutableStateFlow<RecipesListUiState>(RecipesListUiState.Loading)
    val recipesListUiState get() = _recipesListUiState.asStateFlow()

    private val _selectedRecipeUiState =
        MutableStateFlow<SelectedRecipeUiState>(SelectedRecipeUiState.Loading)
    val selectedRecipeUiState get() = _selectedRecipeUiState.asStateFlow()


    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            recipesListUseCase.execute(application).collect { response ->
                when (response) {
                    is Result.Error -> _recipesListUiState.value =
                        RecipesListUiState.Error(response.message)

                    is Result.Loading -> _recipesListUiState.value =
                        RecipesListUiState.Loading

                    is Result.Success -> {
                        if (response.data.isEmpty()) {
                            _recipesListUiState.value = RecipesListUiState.ListEmpty
                            return@collect
                        }
                        _recipesListUiState.value = RecipesListUiState.HasRecipes(response.data)
                    }
                }
            }
        }
    }

    sealed interface RecipesListUiState {
        data object Loading : RecipesListUiState
        data class HasRecipes(val list: List<RecipeItemEntity>) : RecipesListUiState
        data object ListEmpty : RecipesListUiState
        data class Error(val message: String) : RecipesListUiState
    }

    fun setSelectedItem(item: RecipeItemEntity) {
        _selectedRecipeUiState.value = SelectedRecipeUiState.HasRecipe(item)
    }

    sealed interface SelectedRecipeUiState {
        data object Loading : SelectedRecipeUiState
        data class HasRecipe(val item: RecipeItemEntity) : SelectedRecipeUiState
    }
}