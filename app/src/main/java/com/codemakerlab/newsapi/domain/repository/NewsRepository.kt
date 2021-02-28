package com.codemakerlab.newsapi.domain.repository

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.utils.Resource
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getNewsHeadLines(country: String, page: Int): Resource<APIResponse>
    suspend fun getSearchedNews(country:String, searchQuery:String, page:Int): Resource<APIResponse>
    suspend fun saveNews(article: Article)
    suspend fun deleteNews(article: Article)
    fun getSavedNews():  Flow<List<Article>>

}