package com.codemakerlab.newsapi.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codemakerlab.newsapi.data.model.Article
import com.codemakerlab.newsapi.databinding.NewsRowItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val callBack = object : DiffUtil.ItemCallback<Article>(){
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }

    val differ: AsyncListDiffer<Article> = AsyncListDiffer(this, callBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsRowItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


    /**************************
     * viewHolder Class
     */
    inner class NewsViewHolder(
        private val binding: NewsRowItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(article: Article) {
            binding.tvTitle.text = article.title
            binding.tvDescription.text = article.description
            binding.tvPublishedAt.text = article.publishedAt
            binding.tvSource.text = article.source.name
            Glide.with(binding.ivArticleImage.context)
                .load(article.urlToImage)
                .into(binding.ivArticleImage)

            binding.root.setOnClickListener {
                // it: View
                onItemClickListener?.let {
                    // it: (Article) -> Unit
                    it(article)
                }
            }
        }

    }

    private var onItemClickListener : ((Article)->Unit)? = null

    fun setOnItemClickListener(listener: (Article) -> Unit) {
        onItemClickListener = listener
    }

}