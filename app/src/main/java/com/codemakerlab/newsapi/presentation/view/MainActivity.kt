package com.codemakerlab.newsapi.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.codemakerlab.newsapi.R
import com.codemakerlab.newsapi.databinding.ActivityMainBinding
import com.codemakerlab.newsapi.presentation.adapter.NewsAdapter
import com.codemakerlab.newsapi.presentation.viewmodel.NewsViewModel
import com.codemakerlab.newsapi.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: NewsViewModelFactory
    @Inject
    lateinit var newsAdapter: NewsAdapter

    lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.bottomNavigationView.setupWithNavController(
//            navHostFragment.findNavController()
//        )

        val navHost =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNavigationView.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, factory)
            .get(NewsViewModel::class.java)

    }
}