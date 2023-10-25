package com.tanya.motiveassignment.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tanya.motiveassignment.NewsApplication
import com.tanya.motiveassignment.data.model.City
import com.tanya.motiveassignment.databinding.FragmentSearchBinding
import com.tanya.motiveassignment.di.component.DaggerSearchComponent
import com.tanya.motiveassignment.di.module.SearchModule
import com.tanya.motiveassignment.ui.base.BaseFragment
import com.tanya.motiveassignment.ui.base.UiState
import com.tanya.motiveassignment.utils.getQueryTextChangeStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    @Inject
    lateinit var searchViewModel: SearchViewModel

    @Inject
    lateinit var searchAdapter: SearchAdapter

    companion object {
        const val TAG: String = "SearchFragment"
        fun newInstance() =
            SearchFragment().apply {
                arguments = Bundle()
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(LayoutInflater.from(requireContext()))
        binding.searchResult.adapter = searchAdapter
        binding.searchResult.layoutManager = LinearLayoutManager(requireContext())
        binding.searchView.clearFocus()
        searchViewModel.getSearchResult(binding.searchView.getQueryTextChangeStateFlow())
        return binding.root
    }
    override fun injectDependencies() {
        DaggerSearchComponent
            .builder()
            .searchModule(SearchModule(this))
            .applicationComponent((requireContext().applicationContext as NewsApplication)
                .applicationComponent)
            .build()
            .injectDependencies(this)
    }

    override fun getViewBinding(): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun setupUI() {
        val recyclerView: RecyclerView = binding.searchResult
        recyclerView.apply{
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.searchList
                    .collect {
                        when (it) {
                            is UiState.Success -> {
                                binding.progressBar.visibility = View.GONE
                                binding.searchResult.visibility = View.VISIBLE
                                searchAdapter.submitData(it.data)
                            }
                            is  UiState.Loading -> {
                                binding.progressBar.visibility = View.VISIBLE
                                binding.searchResult.visibility = View.GONE
                            }
                            is UiState.Error -> {
                            }
                        }
                    }
            }
        }

    }
//    private fun renderList(articleList: List<City>) {
//        searchAdapter.apply{
//            replaceData(articleList)
//            notifyDataSetChanged()
//        }
//    }
}