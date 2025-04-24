package com.coles.core.domain.utils

import android.app.Application

interface UseCase

interface ApiUseCaseParams<Params, Type> : UseCase {
    suspend fun execute(params: Params): Result<Type>
}

interface ApiUseCaseNonParams<Type> : UseCase {
    suspend fun execute(application: Application): Result<Type>
}
