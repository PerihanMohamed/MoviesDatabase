package com.example.filtermovies.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.filtermovies.data.remote.MovieRepo

class HomeViewModel @ViewModelInject constructor(private val repo: MovieRepo) :ViewModel() {



}
enum class SortBy(val value :String ) {
    Popular ("popularity.desc"),
    TopRated ("vote_count.desc")
}