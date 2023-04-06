package com.gunjan.newsfeed.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gunjan.newsfeed.databinding.ListItemBinding
import com.gunjan.newsfeed.model.remote.Category

class NewsAdapter(private val categories: List<Category>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsDetail = categories[position]
        holder.binding.apply {
            textViewTitle.text = newsDetail.webTitle
            textViewDescription.text = newsDetail.webUrl
        }
    }
}