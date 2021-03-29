package com.e.nytimes.data.repository

import com.e.nytimes.data.api.ApiService
import com.e.nytimes.data.model.NyTimesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class MostPopularRepository @Inject constructor(val api: ApiService) {

    fun getMostPopular(): Flow<NyTimesModel> {

        return flow {
            val searchResult = api.getMostPopular()
            emit(searchResult)
        }.flowOn(Dispatchers.IO)

    }

}