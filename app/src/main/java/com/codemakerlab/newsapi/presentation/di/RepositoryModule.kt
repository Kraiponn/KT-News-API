package com.codemakerlab.newsapi.presentation.di

import com.codemakerlab.newsapi.data.repository.NewsRepositoryImpl
import com.codemakerlab.newsapi.data.repository.dataSource.NewsRemoteDataSource
import com.codemakerlab.newsapi.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        newsRemoteDataSource: NewsRemoteDataSource
    ): NewsRepository {
        return NewsRepositoryImpl(newsRemoteDataSource)
    }

}