package com.example.filtermovies.ui


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filtermovies.data.remote.MovieRepo
import com.example.filtermovies.ui.home.HomeViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeViewModel @ViewModelInject constructor(val repo: MovieRepo) : ViewModel() {

    private val _viewState = MutableLiveData<HomeViewState>()
    val viewState: LiveData<HomeViewState>
        get() = _viewState

    init {
        makeApiCall(SortBy.Popular)
    }
//
//    private var job = Job()
//
//    private val coroutineScope = CoroutineScope(job + Dispatchers.Main)


    fun makeApiCall(filter: SortBy) {
        viewModelScope.launch {
            var resultDef = repo.getPopular(filter)
            try {
                _viewState.value = HomeViewState.Loading
                val result = resultDef.await()
                _viewState.value = HomeViewState.Presenting(result.results)
            } catch (e: Exception) {
                _viewState.value = HomeViewState.Error
            }

        }
    }


//    override fun onCleared() {
//        super.onCleared()
//        job.cancel()
//    }


    fun FilterMovie(filter: SortBy) {
        makeApiCall(filter)
    }
}


enum class SortBy(val value: String) {
    Popular("popularity.desc"),
    TopRated("vote_count.desc"),
    UpComing("release_date.desc")

}