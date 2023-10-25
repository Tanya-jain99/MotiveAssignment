package com.tanya.motiveassignment.ui.search


import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.tanya.motiveassignment.data.model.City
import com.tanya.motiveassignment.data.repository.Repository
import com.tanya.motiveassignment.ui.DispatcherProvider
import com.tanya.motiveassignment.ui.base.BaseViewModel
import com.tanya.motiveassignment.ui.base.UiState
import com.tanya.motiveassignment.utils.asModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: Repository,
    private val dispatcherProvider: DispatcherProvider) : BaseViewModel() {
    private val _searchList = MutableStateFlow<UiState<PagingData<City>>>(UiState.Loading)
    val searchList : StateFlow<UiState<PagingData<City>>> = _searchList

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun getSearchResult(queryFlow: StateFlow<String>)  {
        viewModelScope.launch(dispatcherProvider.main) {

            queryFlow.debounce(300)
                .distinctUntilChanged()
                .filter { query ->
                    if (query.isEmpty() || query.length < 3) {
                        _searchList.value = UiState.Success(PagingData.empty())
                        return@filter false
                    }else {
                        return@filter true
                    }
                }
                .flatMapLatest { query ->
                    return@flatMapLatest repository.getSearchResults(query)
                }.map {
                    it.map { city ->
                        city.asModel(city.toponymName, city.adminName1, city.countryName)
                    }
                }.flowOn(dispatcherProvider.io)
                    .collect {
                        _searchList.value = UiState.Success(it)
                    }
                }

        }

    }

