package com.example.filtermovies.data.remote

import com.example.filtermovies.API_KEY
import com.example.filtermovies.model.MoviesResult
import com.example.filtermovies.model.TrailersResult
import com.example.filtermovies.ui.SortBy
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class MovieRepo @Inject constructor(private val apiService: ApiService) {


    fun getPopular (filter :SortBy) :Deferred<MoviesResult> {
      return  apiService.getPopular(API_KEY , "en-US",filter.value ,false ,false )
    }

    fun getMovieDetail (id :Int) : Deferred<TrailersResult>{
      return  apiService.getMovieDetail(id , API_KEY ,"en-US")
    }


}