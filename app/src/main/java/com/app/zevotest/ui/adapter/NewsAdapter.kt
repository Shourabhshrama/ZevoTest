package com.app.zevotest.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.zevotest.R
import com.app.zevotest.data.remote.models.Article
import com.app.zevotest.databinding.ItemPostBinding
import com.app.zevotest.ui.screens.NewsDetailActivity


class NewsAdapter(private val posts: List<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemPostBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_post, parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int = posts.size

    inner class NewsViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(news: Article) {
            binding.news = news
            binding.executePendingBindings()
            binding.root.setOnClickListener {
                val newsUrl = news.url
                val intent = Intent(binding.root.context, NewsDetailActivity::class.java)
                intent.putExtra(NewsDetailActivity.EXTRA_NEWS_URL, newsUrl)
                binding.root.context.startActivity(intent)
            }
        }


    }
}
