package com.coles.core.data.repository

import android.content.Context
import com.coles.apiresponse.Recipes
import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.core.domain.utils.Result
import com.coles.entity.Details
import com.coles.entity.Ingredients
import com.coles.entity.RecipeItemEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class ColesRecipesRepositoryImpl @Inject constructor() : ColesRecipesRepository {
    override suspend fun getDataFromJson(
        context: Context, fileName: String
    ): Flow<Result<List<RecipeItemEntity>>> = withContext(Dispatchers.IO) {
        flow {
            emit(Result.Loading)

            val file = context.assets.open(fileName)

            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            val jsonString = stringBuilder.toString()

            val type = object : TypeToken<Recipes>() {}.type
            val data: Recipes = Gson().fromJson(jsonString, type)

            val recipes = data.recipes.map { item ->
                RecipeItemEntity(
                    title = item.dynamicTitle,
                    desc = item.dynamicDescription,
                    url = prefixIfNotEmpty(item.dynamicThumbnail, "https://coles.com.au/"),
                    amountDetails = Details(
                        label = item.recipeDetails.amountLabel,
                        note = item.recipeDetails.amountNumber.toString()
                    ),
                    prepDetails = Details(
                        label = item.recipeDetails.prepLabel, note = item.recipeDetails.prepNote
                    ),
                    cookingDetails = Details(
                        label = item.recipeDetails.cookingLabel,
                        note = item.recipeDetails.cookingTime
                    ),
                    ingredients = item.ingredients.toConvertedData()
                )
            }
            emit(Result.Success(recipes))
        }.catch { error ->
            //add custom error messages here
            emit(Result.Error(error.message.toString(), 2))
        }
    }
}

fun prefixIfNotEmpty(text: String?, prefix: String): String {
    return if (!text.isNullOrEmpty()) {
        "$prefix$text"
    } else {
        "" // Or handle the empty case as needed, e.g., return null or a default value
    }
}

fun List<com.coles.apiresponse.Ingredients>.toConvertedData(): List<Ingredients> {
    return this.map { item ->
        Ingredients(item.ingredient)
    }
}


