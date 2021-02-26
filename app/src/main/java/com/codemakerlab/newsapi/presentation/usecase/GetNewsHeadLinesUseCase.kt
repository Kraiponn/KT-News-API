package com.codemakerlab.newsapi.presentation.usecase

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.presentation.repository.NewsRepository
import com.codemakerlab.newsapi.utils.Resource

class GetNewsHeadLinesUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(): Resource<APIResponse> {
        return newsRepository.getNewsHeadLines()
    }

}