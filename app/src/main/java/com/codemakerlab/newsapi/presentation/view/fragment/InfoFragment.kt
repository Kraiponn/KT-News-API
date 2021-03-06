package com.codemakerlab.newsapi.presentation.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.navigation.fragment.navArgs
import com.codemakerlab.newsapi.R
import com.codemakerlab.newsapi.databinding.FragmentInfoBinding

class InfoFragment : Fragment() {
    private lateinit var binding: FragmentInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        val args: InfoFragmentArgs by navArgs()
        val article = args.selectedArticle
        binding.wvInfo.apply {
            // this: WebView
            webViewClient = WebViewClient()
            if(article.url != "") {
                loadUrl(article.url)
            }
        }
    }

}