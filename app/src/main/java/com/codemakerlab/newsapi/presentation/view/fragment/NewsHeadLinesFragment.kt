package com.codemakerlab.newsapi.presentation.view.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codemakerlab.newsapi.R
import com.codemakerlab.newsapi.data.model.APIResponse
import com.codemakerlab.newsapi.databinding.FragmentNewsHeadLinesBinding
import com.codemakerlab.newsapi.presentation.adapter.NewsAdapter
import com.codemakerlab.newsapi.presentation.view.MainActivity
import com.codemakerlab.newsapi.presentation.viewmodel.NewsViewModel
import com.codemakerlab.newsapi.utils.Resource

class NewsHeadLinesFragment : Fragment() {
    private lateinit var viewModel: NewsViewModel
    private lateinit var newsAdapter: NewsAdapter
    private lateinit var binding: FragmentNewsHeadLinesBinding
    private var country = "us"
    private var page = 1
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage = false
    private var pages = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_head_lines, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsHeadLinesBinding.bind(view)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).newsAdapter
        newsAdapter.setOnItemClickListener {
            // it: Article
            val bundle = Bundle().apply {
                //this: Bundle
                putSerializable("selected_article", it)
            }

            findNavController().navigate(
                R.id.action_newsHeadLinesFragment_to_infoFragment,
                bundle
            )
        }

        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadLines(country, page)
        viewModel.newsHeadLines.observe(viewLifecycleOwner,
            Observer { resource: Resource<APIResponse> ->
                when (resource) {
                    is Resource.Success -> {
                        hideProgressBar()
                        resource.data?.let { apiResponse: APIResponse ->
                            Log.d("MyTag", "Came here ${apiResponse.articles.toList().size}")
                            newsAdapter.differ.submitList(apiResponse.articles.toList())

                            pages = if (apiResponse.totalResults % 20 == 0) {
                                apiResponse.totalResults / 20
                            } else {
                                apiResponse.totalResults / 20 + 1
                            }

                            isLastPage = page == pages
                        }
                    }
                    is Resource.Loading -> {
                        showProgressBar()
                    }
                    is Resource.Error -> {
                        hideProgressBar()
                        resource.message?.let {
                            Toast.makeText(
                                activity,
                                "An error occurred: $it",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            })
    }

    private fun initRecyclerView() {
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@NewsHeadLinesFragment.onScrollListener)
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.INVISIBLE
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager
            val sizeOfTheCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfTheCurrentList
            val shouldPaginate = !isLoading && !isLastPage && hasReachedToEnd && isScrolling
            if(shouldPaginate) {
                page++
                viewModel.getNewsHeadLines(country, page)
                isScrolling = false
            }
        }
    }

}