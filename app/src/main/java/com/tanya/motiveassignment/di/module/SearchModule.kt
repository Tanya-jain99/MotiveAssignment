package com.tanya.motiveassignment.di.module

import androidx.lifecycle.ViewModelProvider
import com.tanya.motiveassignment.data.repository.Repository
import com.tanya.motiveassignment.di.FragmentContext
import com.tanya.motiveassignment.di.FragmentScope
import com.tanya.motiveassignment.ui.DispatcherProviderImpl
import com.tanya.motiveassignment.ui.base.ViewModelProviderFactory
import com.tanya.motiveassignment.ui.search.SearchFragment
import com.tanya.motiveassignment.ui.search.SearchAdapter
import com.tanya.motiveassignment.ui.search.SearchViewModel
import dagger.Module
import dagger.Provides

@Module
class SearchModule(private val fragment: SearchFragment) {

    @FragmentContext
    @Provides
    fun providesContext()  = fragment.requireContext()

    @Provides
    fun providesSearchViewModel(repository: Repository,
                                  dispatcherProviderImpl: DispatcherProviderImpl
    ) : SearchViewModel {
        return ViewModelProvider(fragment, ViewModelProviderFactory(SearchViewModel::class){
            SearchViewModel(repository, dispatcherProviderImpl)
        })[SearchViewModel::class.java]
    }

    @FragmentScope
    @Provides
    fun providesSearchAdapter() : SearchAdapter{
        return SearchAdapter()
    }
}