package com.example.filtermovies.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.example.filtermovies.data.remote.ApiService
import com.example.filtermovies.data.remote.MovieRepo
import com.example.filtermovies.model.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class DetailViewModel @ViewModelInject constructor(val apiService: ApiService): ViewModel() {

    private val _viewState = MutableLiveData<DetailViewSate>()
    val viewSate : LiveData<DetailViewSate>
      get() = _viewState





    fun loadMovie(id: Int)= liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data=apiService.MovieDetail(id)))
        }catch (exception: Exception){
            emit(Resource.error(data=null,message = exception.message?:"Error occured"))
        }
    }


}