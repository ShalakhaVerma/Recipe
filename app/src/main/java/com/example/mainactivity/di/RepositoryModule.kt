package com.example.mainactivity.di

import com.coles.core.data.repository.ColesRecipesRepositoryImpl
import com.coles.core.domain.repository.ColesRecipesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindColesRecipesRepository(colesRecipesRepositoryImpl: ColesRecipesRepositoryImpl): ColesRecipesRepository
}