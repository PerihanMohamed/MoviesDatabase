package com.example.filtermovies.data.remote

import com.example.filtermovies.API_KEY
import com.example.filtermovies.model.MoviesResult
import com.example.filtermovies.ui.SortBy
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiService: ApiService) {


    fun getPopular (filter :SortBy) {

        apiService.getPopular(API_KEY , "en-US",filter.value ,false ,false )

    }

    fun getMovieDetail (id :Int){
        apiService.getMovieDetail(id , API_KEY ,"en-US")
    }


}