package com.codemakerlab.newsapi.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.codemakerlab.newsapi.R
import com.codemakerlab.newsapi.databinding.FragmentNewsHeadLinesBinding

class NewsHeadLinesFragment : Fragment() {
    private lateinit var binding: FragmentNewsHeadLinesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsHeadLinesBinding.inflate(
            inflater,
            container,
            false
        )

        return binding.root
    }

}