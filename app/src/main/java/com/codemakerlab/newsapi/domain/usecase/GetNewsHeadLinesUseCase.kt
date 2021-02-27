package com.codemakerlab.newsapi.domain.usecase

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.domain.repository.NewsRepository
import com.codemakerlab.newsapi.utils.Resource

class GetNewsHeadLinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(country: String, page: Int): Resource<APIResponse> {
        return newsRepository.getNewsHeadLines(country, page)
    }

}