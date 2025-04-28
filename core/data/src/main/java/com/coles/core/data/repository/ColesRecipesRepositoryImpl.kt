package com.coles.core.data.repository

import android.content.Context
import com.coles.apiresponse.RecipeApiResponse
import com.coles.core.domain.repository.ColesRecipesRepository
import com.coles.core.domain.utils.Result
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
        context: Context,
        fileName: String
    ): Flow<Result<List<RecipeItemEntity>>> = withContext(Dispatchers.IO) {
        flow {
            emit(Result.Loading)

            val file = context.assets.open("$fileName")

            val bufferedReader = BufferedReader(InputStreamReader(file))
            val stringBuilder = StringBuilder()
            bufferedReader.useLines { lines ->
                lines.forEach {
                    stringBuilder.append(it)
                }
            }
            val jsonString = stringBuilder.toString()

            val type = object : TypeToken<List<RecipeApiResponse>>() {}.type
            val data: List<RecipeApiResponse> = Gson().fromJson(jsonString, type)

            val recipes = data.map { item ->
                RecipeItemEntity(
                    id = item.id,
                    title = item.title,
                    desc = item.desc,
                    url = item.url
                )
            }
            emit( Result.Success(recipes))
        } .catch { error ->
            //add custom error messages here
            emit(Result.Error(error.message.toString(), 2))
        }
    }
}