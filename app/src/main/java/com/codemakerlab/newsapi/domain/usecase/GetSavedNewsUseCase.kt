package com.codemakerlab.newsapi.domain.usecase

import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }

}