package com.example.filtermovies.ui.detail

import com.example.filtermovies.model.Movie


sealed class DetailViewSate {

     object Error  : DetailViewSate()
     object Loading  : DetailViewSate()
     data class Presenting(val results: Movie) :DetailViewSate()
}