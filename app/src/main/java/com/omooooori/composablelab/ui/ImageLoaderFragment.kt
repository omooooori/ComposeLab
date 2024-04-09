package com.omooooori.composablelab.ui

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.omooooori.composablelab.R
import com.omooooori.composablelab.databinding.FragmentImageLoaderBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ImageLoaderFragment : Fragment() {

    private var _binding: FragmentImageLoaderBinding? = null
    private val binding: FragmentImageLoaderBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageLoaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImageLoaderFragment()
    }
}
