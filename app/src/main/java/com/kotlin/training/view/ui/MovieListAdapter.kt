package com.kotlin.training.view.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.kotlin.training.R
import com.kotlin.training.data.room.entity.Movie
import com.kotlin.training.databinding.ItemMovieBinding
import com.kotlin.training.utils.Constants
import com.kotlin.training.viewmodel.MovieViewModel
import com.squareup.picasso.Picasso
import java.util.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private lateinit var postList: List<Movie>
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemMovieBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_movie,
            parent,
            false
        )
        context = parent.context

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(postList[position])
    }

    override fun getItemCount(): Int {
        return if (::postList.isInitialized) postList.size else 0
    }

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val viewModel = MovieViewModel()

        fun bind(post: Movie) {
            viewModel.bind(post)
            binding.viewModel = viewModel

            Picasso.with(binding.root.context).load(
                    Constants.IMAGE_URL+post.poster_path)
                    .into(binding.poster)

        }
    }

    fun updateMovieList(postList: List<Movie>) {
        this.postList = sort(postList)
        notifyDataSetChanged()
    }


    fun filter(
        query: String,
        originalList: List<Movie>
    ): List<Movie> {
        var month: List<Movie> = originalList
        var filteredModelList: List<Movie> = month.filter { movie ->
            movie.title.toLowerCase(Locale.getDefault()).contains(query.toLowerCase(Locale.getDefault()))
        }
        return filteredModelList
    }

    private fun sort(list: List<Movie>): List<Movie> {
        return list.toMutableList().sortedByDescending { it.vote_average }
    }

    private fun reduce() {
        val strings = listOf("a", "b", "c", "d")
        println(strings.reduce { acc, string -> acc + string }) // abcd
        println(strings.reduceIndexed { index, acc, string -> acc + string + index }) // ab1c2d3
    }

}