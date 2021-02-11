package com.example.filtermovies.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.filtermovies.POSTER_BASE_URL
import com.example.filtermovies.R
import com.example.filtermovies.databinding.HomeFragmentBinding
import com.example.filtermovies.databinding.MovieItemBinding
import com.example.filtermovies.loadImage
import com.example.filtermovies.model.Movie
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.movie_item.view.*


class HomeAdapter(val listener :onItemClickListenner ): ListAdapter< Movie, HomeAdapter.HomeViewHolder>(DiffCallback) {

    inner class HomeViewHolder(private val binding: MovieItemBinding)
        : RecyclerView.ViewHolder(binding.root){

        fun bindPost( movie : Movie)  {

            binding.apply {
                val moviePosterURL = POSTER_BASE_URL + movie.poster_path
                    Glide
                        .with(itemView)
                        .load(moviePosterURL)
                        .error(R.drawable.ic_broken_image)
                        .into(movieImage)


            }

            
        }

        init {
            binding.root.setOnClickListener {
                val itemPosition =  adapterPosition

                if(itemPosition != RecyclerView.NO_POSITION)
                {
                    val movie = getItem(itemPosition)

                    listener.onItemClick(movie)
                }

                }
            }

        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeAdapter.HomeViewHolder {
        val binding = MovieItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)


    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
          val currentItem = getItem(position)
          holder.bindPost(currentItem)
    }



    companion object DiffCallback : DiffUtil.ItemCallback<Movie>() {

        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }
    }

    interface onItemClickListenner {
        fun onItemClick(movie: Movie)
    }


}