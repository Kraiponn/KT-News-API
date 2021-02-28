package com.codemakerlab.newsapi.presentation.di

import android.app.Application
import com.codemakerlab.newsapi.domain.usecase.GetNewsHeadLinesUseCase
import com.codemakerlab.newsapi.domain.usecase.GetSearchedNewsUseCase
import com.codemakerlab.newsapi.presentation.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FactoryModule {

    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
        getSearchedNewsUseCase: GetSearchedNewsUseCase
    ): NewsViewModelFactory {
        return NewsViewModelFactory(
            application,
            getNewsHeadLinesUseCase,
            getSearchedNewsUseCase
        )
    }

}