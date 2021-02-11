package com.example.filtermovies.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.filtermovies.R
import com.example.filtermovies.databinding.HomeFragmentBinding
import com.example.filtermovies.model.Movie
import com.example.filtermovies.ui.HomeViewModel
import com.example.filtermovies.ui.SortBy
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.home_fragment),HomeAdapter.onItemClickListenner {

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var binding: HomeFragmentBinding


    val pAdapter = HomeAdapter(this)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpRecyclerView()
        setUpObserver()
        setHasOptionsMenu(true)
    }

    private fun setUpObserver() {
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            when (it) {
                is HomeViewState.Loading -> {
                    Log.d("TAG", "LOADING")
                    status_image.visibility = View.VISIBLE
                    status_image.setImageResource(R.drawable.loading_animation)
                }
                is HomeViewState.Error -> {
                    Log.d("TAG", "errir")
                    status_image.visibility = View.VISIBLE
                    status_image.setImageResource(R.drawable.loading_animation)

                }
                is HomeViewState.Presenting -> {
                    Log.d("TAG", "presenting")
                    status_image.visibility = View.VISIBLE
                    showList(it.results)
                }

            }

        })
    }

    private fun showList(movies: List<Movie>) {
        pAdapter.submitList(movies)
    }


    private fun setUpRecyclerView() {
        Log.d("TAG", "setupRecyclerView")
        val manager = GridLayoutManager(activity, 3, GridLayoutManager.VERTICAL, false)
        binding.movieRecyclerView.setHasFixedSize(true)
        binding.movieRecyclerView.layoutManager = manager
        binding.movieRecyclerView.adapter = pAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onItemClick(movie: Movie) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragmnent2(movie)
        findNavController().navigate(action)
//        viewModel.navigateToSelectedMovie(movie)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.main_menu, menu)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.top_RATED -> {
                viewModel.FilterMovie(SortBy.TopRated)
            }
            R.id.UpComing -> {
                viewModel.FilterMovie(SortBy.UpComing)
            } else -> viewModel.FilterMovie(SortBy.Popular)

        }
        return true
    }

}

