package com.dido.weatherapp.arch.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB: ViewBinding, VM: BaseViewModel>(
    private val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB,
) : Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!

    protected abstract val viewModel: VM
    protected abstract fun initView(view: View, savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = bindingInflater(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}