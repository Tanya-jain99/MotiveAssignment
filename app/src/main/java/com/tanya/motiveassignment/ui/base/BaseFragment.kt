package com.tanya.motiveassignment.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class BaseFragment<VM: BaseViewModel, VB: ViewBinding>: Fragment() {

    @Inject
    lateinit var viewModel: VM

    lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        setupObserver()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = getViewBinding()
        setupUI()
        return binding.root
    }

    protected abstract fun injectDependencies()

    protected abstract fun getViewBinding(): VB

    protected abstract fun setupUI()

    protected abstract fun setupObserver()

}