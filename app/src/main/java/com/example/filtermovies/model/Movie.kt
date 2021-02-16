package com.example.filtermovies.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MoviesResult(
    val page: Int,
    val total_results: Int,
    val total_pages: Int,
    val results: List<Movie>
) : Parcelable

@Parcelize
data class Movie(
    val popularity : Double,
    val id : Int,
    val video : Boolean,
    val vote_count : Int,
    val vote_average : Double,
    val title : String,
    val release_date : String = "",
    val original_language : String,
    val original_title : String,
    val genre_ids : List<Int>,
    val backdrop_path : String,
    val adult : Boolean,
    val overview : String,
    val poster_path : String
) : Parcelable

@Parcelize
data class MovieDetails(
    val budget: Int,
    val id: Int,
    val overview: String,
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val revenue: Long,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    @SerializedName("vote_average")
    val rating: Double ,
    val backdrop_path : String,
) : Parcelable




