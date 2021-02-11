package com.example.filtermovies.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filtermovies.data.remote.MovieRepo
import com.example.filtermovies.model.Movie
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel @ViewModelInject constructor(val repo : MovieRepo): ViewModel() {

    private val _viewState = MutableLiveData<DetailViewSate>()
    val viewSate : LiveData<DetailViewSate>
      get() = _viewState



//    private val _selectedMovie = MutableLiveData<Movie>()
//    val selecMovie : LiveData<Movie>
//        get() = _selectedMovie


    fun getMovieDetail (id :Int) {
        viewModelScope.launch {
            try {
                _viewState.value = DetailViewSate.Loading
                var resultDeffered = repo.getMovieDetail(id).await()
                _viewState.value = DetailViewSate.Presenting(resultDeffered.results)
            }catch (e:Exception){
                _viewState.value = DetailViewSate.Error
            }




        }

    }


}