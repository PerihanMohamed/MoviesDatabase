package com.example.filtermovies.ui.home

import com.example.filtermovies.model.Movie

sealed class HomeViewState {
    data class Presenting ( val movie : List<Movie> ) : HomeViewState()

    object loading : HomeViewState()

    object Error : HomeViewState()
}
