package com.codemakerlab.newsapi.domain.usecase

import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.domain.repository.NewsRepository

class DeleteSavedNewsUseCase(private val newsRepository: NewsRepository) {

    suspend fun execute(article: Article): Unit {
        newsRepository.deleteNews(article)
    }

}