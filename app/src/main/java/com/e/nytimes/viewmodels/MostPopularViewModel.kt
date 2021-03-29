package com.e.nytimes.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.nytimes.data.model.NyTimesModel
import com.e.nytimes.data.repository.MostPopularRepository
import com.e.nytimes.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class MostPopularViewModel @ViewModelInject constructor(
    private val repository: MostPopularRepository
) : ViewModel() {

    private val _mostPopularState = MutableStateFlow<Resource<NyTimesModel>>(Resource.Empty)
    val mostPopularState: StateFlow<Resource<NyTimesModel>> = _mostPopularState

    init {
        getMostPopular()
    }

    fun getMostPopular() {

        viewModelScope.launch {

            repository.getMostPopular()
                .onStart {
                    _mostPopularState.value = Resource.Loading(true)
                }
                .catch {
                    it.message?.let { message ->
                        _mostPopularState.value = Resource.Error(message)
                    }
                }
                .collect { news ->
                    _mostPopularState.value = Resource.Success(news)
                }


        }

    }

}