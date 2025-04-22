package com.coles.feature.recipeslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import com.coles.core.domain.usecase.ColesRecipesUseCase
import com.coles.entity.RecipeItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val recipesListUseCase: ColesRecipesUseCase
) : ViewModel() {

    private val _recipesListUiState =
        MutableStateFlow<RecipesListUiState>(RecipesListUiState.Loading)
    val recipesListUiState get() = _recipesListUiState.asStateFlow()

    init {
        fetchRecipes()
    }

    private fun fetchRecipes() {
        viewModelScope.launch {
            recipesListUseCase.execute().let { response ->
                _recipesListUiState.value = RecipesListUiState.HasRecipes(response)
            }
        }
    }

    sealed interface RecipesListUiState {
        data object Loading : RecipesListUiState
        data class HasRecipes(val carList: List<RecipeItemEntity>) : RecipesListUiState
        data object ListEmpty : RecipesListUiState
        data class Error(val message: String) : RecipesListUiState
    }
}