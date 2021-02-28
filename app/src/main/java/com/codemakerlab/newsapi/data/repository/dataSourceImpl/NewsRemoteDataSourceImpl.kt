package com.codemakerlab.newsapi.data.repository.dataSourceImpl

import com.codemakerlab.newsapi.data.api.NewsAPIService
import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.data.repository.dataSource.NewsRemoteDataSource
import retrofit2.Response

class NewsRemoteDataSourceImpl(
    private val newsAPIService: NewsAPIService
): NewsRemoteDataSource {

    override suspend fun getTopHeadLines(country: String, page: Int): Response<APIResponse> {
        return newsAPIService.getTopHeadLines(country, page)
    }

    override suspend fun getSearchedNews(
        country: String,
        searchQuery: String,
        page: Int
    ): Response<APIResponse> {
        return newsAPIService.getSearchedTopHeadLines(country, searchQuery, page)
    }

}