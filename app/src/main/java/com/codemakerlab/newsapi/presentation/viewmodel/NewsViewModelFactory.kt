package com.codemakerlab.newsapi.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codemakerlab.newsapi.domain.usecase.GetNewsHeadLinesUseCase
import com.codemakerlab.newsapi.domain.usecase.GetSearchedNewsUseCase
import java.lang.IllegalArgumentException

class NewsViewModelFactory(
    private val app: Application,
    private val getNewsHeadLinesUseCase: GetNewsHeadLinesUseCase,
    private val getSearchedNewsUseCase: GetSearchedNewsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(app, getNewsHeadLinesUseCase, getSearchedNewsUseCase) as T
        }

        throw IllegalArgumentException("Could not find NewsViewModel class")
    }
}