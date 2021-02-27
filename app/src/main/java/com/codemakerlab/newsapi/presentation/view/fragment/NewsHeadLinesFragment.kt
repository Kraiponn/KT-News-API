package com.codemakerlab.newsapi.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
                            newsAdapter.differ.submitList(apiResponse.articles.toList())
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
        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}