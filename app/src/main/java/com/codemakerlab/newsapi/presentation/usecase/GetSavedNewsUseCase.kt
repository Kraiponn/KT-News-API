package com.codemakerlab.newsapi.presentation.usecase

import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.presentation.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetSavedNewsUseCase(private val newsRepository: NewsRepository) {

    fun execute(): Flow<List<Article>> {
        return newsRepository.getSavedNews()
    }

}