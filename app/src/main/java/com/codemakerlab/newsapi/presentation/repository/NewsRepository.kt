package com.codemakerlab.newsapi.presentation.repository

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadLines(): Resource<APIResponse>
    suspend fun getSearchedNews(searchQuery: String): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews():  Flow<List<Article>>

}