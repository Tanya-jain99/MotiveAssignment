package com.tanya.motiveassignment.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tanya.motiveassignment.data.api.NetworkService
import com.tanya.motiveassignment.data.api.model.CityNameResponse
import com.tanya.motiveassignment.data.paging.SearchPagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(private val networkService: NetworkService)
{
    /*fun getSearchResults(query : String) : Flow<List<CityNameResponse>> {
        return flow {
            val queryMap = mapOf("name_startsWith" to query,
            "maxRows" to "10",
                "username" to "keep_truckin")

            emit(networkService.search(queryMap))
        }.map {
            it.cities
        }
    }*/

    fun getSearchResults(query: String) = Pager(
        config = PagingConfig(pageSize = 10, maxSize = 50),
        pagingSourceFactory = { SearchPagingSource(query, networkService) }
    ).flow
}