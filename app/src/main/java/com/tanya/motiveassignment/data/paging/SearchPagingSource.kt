package com.tanya.motiveassignment.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tanya.motiveassignment.data.api.NetworkService
import com.tanya.motiveassignment.data.api.model.CityNameResponse

class SearchPagingSource (private val query :String, private val searchApi: NetworkService) : PagingSource<Int, CityNameResponse>() {

        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CityNameResponse> {
            return try {
                val position = params.key ?: 1
                val queryMap = mapOf("name_startsWith" to query,
                    "maxRows" to "10",
                    "username" to "keep_truckin")
                val response = searchApi.search(queryMap)

                return LoadResult.Page(
                    data = response.cities,
                    prevKey = if (position == 1) null else position - 1,
                    nextKey = if (position == 20) null else position + 1
                )
            } catch (e: Exception) {
                LoadResult.Error(e)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, CityNameResponse>): Int? {
            return state.anchorPosition?.let { anchorPosition ->
                val anchorPage = state.closestPageToPosition(anchorPosition)
                anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
            }
        }
}