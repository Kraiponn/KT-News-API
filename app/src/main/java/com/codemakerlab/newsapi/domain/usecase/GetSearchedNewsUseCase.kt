package com.codemakerlab.newsapi.domain.usecase

import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.domain.repository.NewsRepository
import com.codemakerlab.newsapi.utils.Resource

class GetSearchedNewsUseCase(
    private val newsRepository: NewsRepository
) {

    suspend fun execute(country:String, searchQuery:String, page:Int): Resource<APIResponse> {
        return newsRepository.getSearchedNews(country, searchQuery, page)
    }

}