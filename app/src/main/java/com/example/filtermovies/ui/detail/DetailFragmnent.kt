package com.example.filtermovies.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.filtermovies.POSTER_BASE_URL
import com.example.filtermovies.R
import com.example.filtermovies.databinding.DetailFragmentBinding
import com.example.filtermovies.model.Movie
import com.example.filtermovies.model.MovieDetails
import com.example.filtermovies.model.Status
import com.example.filtermovies.model.Trailer
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.detail_fragment.*
import kotlinx.android.synthetic.main.detail_fragment.view.*
import kotlinx.android.synthetic.main.home_fragment.*
import java.lang.System.load
import java.util.*


@AndroidEntryPoint
class DetailFragmnent : Fragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: DetailFragmentBinding

    private val args by navArgs<DetailFragmnentArgs>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val id = args.selectedMovies.id

        viewModel.loadMovie(id).observe(viewLifecycleOwner , Observer {
            when (it.status) {
                Status.LOADING -> {
                    Log.d("msg" , "my loading" )
                }
                Status.SUCCESS -> {
                    val movie = it.data
                    movie?.let {
                        val movie = it.body()

                        if (movie != null) {
                           updateUI(movie)
                        }
                    }
                }
                Status.ERROR -> {
                    Log.e("msg" , "error")
                }
            }
        })


    }

    private fun updateUI(movie: MovieDetails) {

        binding.apply {
            picassoLoadImages(movie.posterPath, movie_poster)
            picassoLoadImages(movie.backdrop_path, movie_backdrop)
            movieTitle.text = movie.title
            synopsis.text = movie.overview
            release_date.text = movie.releaseDate
            rating.text = movie.rating.toString()

        }

    }

    private fun picassoLoadImages(img: String, imageView: ImageView) {
        val imgUrl = POSTER_BASE_URL + img
        imgUrl.let {
            val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
            Picasso.get()
                .load(imgUri)
                .error(R.drawable.ic_broken_image)
                .into(imageView)
        }

    }
}