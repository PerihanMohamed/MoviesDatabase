package com.example.filtermovies.ui.detail

import com.example.filtermovies.model.Trailer

sealed class DetailViewSate {

     object Error  : DetailViewSate()
     object Loading  : DetailViewSate()
     data class Presenting ( val results: List<Trailer>) :DetailViewSate()
}