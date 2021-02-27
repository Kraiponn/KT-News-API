package com.codemakerlab.newsapi.presentation.di

import com.codemakerlab.newsapi.data.api.NewsAPIService
import com.codemakerlab.newsapi.data.repository.dataSource.NewsRemoteDataSource
import com.codemakerlab.newsapi.data.repository.dataSourceImpl.NewsRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewsRemoteDataSource(newsAPIService: NewsAPIService): NewsRemoteDataSource {
        return NewsRemoteDataSourceImpl(newsAPIService)
    }

}