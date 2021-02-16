package com.example.filtermovies.data.remote

import com.example.filtermovies.model.Movie
import com.example.filtermovies.model.MovieDetails
import com.example.filtermovies.model.MoviesResult
import com.example.filtermovies.model.TrailersResult
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    fun getPopular(
        @Query("api_key") Api_key: String,
        @Query("language") Language: String,
        @Query("sort_by") SortBy: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int = 1,
    ): Deferred<MoviesResult>

//
//    @GET("movie/{movie_id}/videos")
//    fun getMovieDetail (
//        @Path("id")id : Int ,
//        @Query("api_key") Api_key: String,
//        @Query("language") Language: String,
//    ) :Deferred<MovieDetails>


@GET("movie/{movie_id}")
     suspend fun MovieDetail(
        @Path ("movie_id") id : Int
    ) : Response<MovieDetails>

}