package com.codemakerlab.newsapi.presentation.usecase

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.presentation.repository.NewsRepository
import com.codemakerlab.newsapi.utils.Resource

class SaveNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(article: Article): Unit {
        newsRepository.saveNews(article)
    }

}