package com.codemakerlab.newsapi.domain.usecase

import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.domain.repository.NewsRepository

class SaveNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(article: Article): Unit {
        newsRepository.saveNews(article)
    }

}