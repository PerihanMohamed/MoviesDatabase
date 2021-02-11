package com.example.filtermovies.ui.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.filtermovies.R
import com.example.filtermovies.databinding.DetailFragmentBinding
import com.example.filtermovies.model.Movie
import com.example.filtermovies.model.Trailer
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*


@AndroidEntryPoint

class DetailFragmnent : Fragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()

    private lateinit var binding: DetailFragmentBinding

//    val args: Movie by navArgs()


    private val args by navArgs<DetailFragmnentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

          setUpObserver()

    }

    private fun setUpObserver() {
        val id = args.selectedMovies.id
//
//        viewModel.viewSate.observe(viewLifecycleOwner , Observer {
//
//            when (it) {
//                is DetailViewSate.Loading
//                -> {
//                    Log.d("TAG", "LOADING")
//                    status_image.visibility = View.VISIBLE
//                    status_image.setImageResource(R.drawable.loading_animation)
//                }
//                is DetailViewSate.Error -> {
//                    Log.d("TAG", "errir")
//                    status_image.visibility = View.VISIBLE
//                    status_image.setImageResource(R.drawable.loading_animation)
//
//                }
//                is DetailViewSate.Presenting -> {
//                    Log.d("TAG", "presenting")
//                    status_image.visibility = View.VISIBLE
//                      navigateToSelectedMovie()
//                }
//
//            }
//        })

        viewModel.getMovieDetail(id)



    }
//
//    private fun navigateToSelectedMovie() {
//       viewModel.selecMovie.observe(viewLifecycleOwner , Observer {
//           it?.let {
//               intializeData(it)
//           }
//
//
//       })
//    }

    private fun intializeData(movie: Movie) {
        binding.releaseDate.text = movie.release_date
        binding.movieTitle.text = movie.title
        binding.rating.text = movie.vote_average.toString()
        binding.synopsis.text = movie.overview

    }


}