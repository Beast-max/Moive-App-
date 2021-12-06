package com.example.movieapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.movieapp.R
import com.example.myapplication.Response.Movie
import kotlinx.android.synthetic.main.popular.view.*

class LatestRecyclerView(private var latest:MutableList<Movie>,private var detailsonclick:(movie:Movie)->Unit) : RecyclerView.Adapter<LatestRecyclerView.ItemViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):  ItemViewHolder{
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.popular,parent,false)
        return ItemViewHolder(itemView)

    }

    inner class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun getItemCount(): Int {
       return latest.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun appendMovie(latest:List<Movie>)
    {
        this.latest.addAll(latest)
        notifyItemRangeInserted(
            this.latest.size,
            latest.size - 1
        )
        notifyDataSetChanged()

    }

    @SuppressLint("CheckResult")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load("https://image.tmdb.org/t/p/w342${latest[position].posterPath}")
            .transform(CenterCrop())
            .into(holder.itemView.item_movie_poster)
            holder.itemView.setOnClickListener { detailsonclick.invoke(latest[position]) }}
}