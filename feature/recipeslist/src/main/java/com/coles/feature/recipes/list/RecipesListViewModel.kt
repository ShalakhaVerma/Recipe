package com.coles.feature.recipes.list

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coles.core.domain.usecase.ColesRecipesUseCase
import com.coles.entity.RecipeItemEntity
import com.coles.feature.recipes.list.RecipesListViewModel.RecipesListUiState.Error
import com.coles.feature.recipes.list.RecipesListViewModel.RecipesListUiState.HasRecipes
import com.coles.feature.recipes.list.RecipesListViewModel.RecipesListUiState.ListEmpty
import com.coles.feature.recipes.list.RecipesListViewModel.RecipesListUiState.Loading
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val application: Application,
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

            recipesListUseCase.execute(application).let { response ->
                when (response) {
                    is com.coles.core.domain.utils.Result.Error -> _recipesListUiState.value =
                        Error(response.message)

                    is com.coles.core.domain.utils.Result.Loading -> _recipesListUiState.value =
                        Loading

                    is com.coles.core.domain.utils.Result.Success -> {
                        if (response.data.isEmpty()) {
                            _recipesListUiState.value = ListEmpty
                        }
                        _recipesListUiState.value = HasRecipes(response.data)
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
}