package com.example.filtermovies.ui.home

import com.example.filtermovies.model.Movie

sealed class HomeViewState {

    data class Presenting(val results: List<Movie>) : HomeViewState()

    object Error : HomeViewState()

    object Loading : HomeViewState()
}