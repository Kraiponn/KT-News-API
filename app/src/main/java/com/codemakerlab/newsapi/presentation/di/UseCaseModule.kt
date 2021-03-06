package com.codemakerlab.newsapi.presentation.di

import com.codemakerlab.newsapi.domain.repository.NewsRepository
import com.codemakerlab.newsapi.domain.usecase.GetNewsHeadLinesUseCase
import com.codemakerlab.newsapi.domain.usecase.GetSearchedNewsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Singleton
    @Provides
    fun provideGetNewsHeadLinesUseCase(
        newsRepository: NewsRepository
    ): GetNewsHeadLinesUseCase {
        return GetNewsHeadLinesUseCase(newsRepository)
    }

    @Singleton
    @Provides
    fun provideGetSearchedNewsUseCase(
        newsRepository: NewsRepository
    ): GetSearchedNewsUseCase {
        return GetSearchedNewsUseCase(newsRepository)
    }

}