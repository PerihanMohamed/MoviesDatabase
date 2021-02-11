package com.example.filtermovies.ui


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filtermovies.data.remote.MovieRepo
import com.example.filtermovies.model.Movie
import com.example.filtermovies.model.TrailersResult
import com.example.filtermovies.ui.home.HomeViewState
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @ViewModelInject constructor(val repo: MovieRepo) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState>
        get() = _viewState

    private  val _navigatetoselectedmovie = MutableLiveData<Movie>()
     val navigatetoselectedmovie : LiveData<Movie>
       get() = _navigatetoselectedmovie


    init {
        makeApiCall(SortBy.Popular)
    }


    fun makeApiCall(filter: SortBy) {
        viewModelScope.launch {
            var resultDef = repo.getPopular(filter)
            try {
                _viewState.value = HomeViewState.Loading
                val result = resultDef.await()
                _viewState.value = HomeViewState.Presenting(result.results)
            } catch (e: Exception) {
                _viewState.value = HomeViewState.Error
            }

        }
    }

    fun navigateToSelectedMovie (movie: Movie){
        _navigatetoselectedmovie.value = movie
    }





        fun FilterMovie(filter: SortBy) {
            makeApiCall(filter)
        }


}


enum class SortBy(val value: String) {
    Popular("popularity.desc"),
    TopRated("vote_count.desc"),
    UpComing("release_date.desc")

}