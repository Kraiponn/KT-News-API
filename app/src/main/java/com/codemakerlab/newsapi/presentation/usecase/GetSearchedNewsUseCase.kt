package com.codemakerlab.newsapi.presentation.usecase

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.presentation.repository.NewsRepository
import com.codemakerlab.newsapi.utils.Resource

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(searchQuery: String): Resource<APIResponse> {
        return newsRepository.getSearchedNews(searchQuery)
    }

}