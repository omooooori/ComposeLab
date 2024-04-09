package com.omooooori.composablelab.ui

import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.bumptech.glide.Glide
import com.omooooori.composablelab.R
import com.omooooori.composablelab.databinding.FragmentImageLoaderBinding
import com.omooooori.composablelab.ui.viewmodel.ImageLoaderViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ImageLoaderFragment : Fragment() {

    private var _binding: FragmentImageLoaderBinding? = null
    private val binding: FragmentImageLoaderBinding
        get() = _binding!!

    @Inject lateinit var viewModel: ImageLoaderViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageLoaderBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadGlideImage()
        loadFrescoImage()
        loadCoilImage()
        loadPicassoImage()
    }

    private fun loadGlideImage() {
        Glide.with(requireContext())
            .load(viewModel.imageUrl)
            .into(binding.glideImageView)
    }

    private fun loadFrescoImage() {
        binding.frescoImageView.setImageURI(Uri.parse(viewModel.imageUrl))
    }

    private fun loadCoilImage() {
        binding.coilImageView.load(viewModel.imageUrl)
    }

    private fun loadPicassoImage() {
        Picasso.get()
            .load(viewModel.imageUrl)
            .into(binding.picassoImageView)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImageLoaderFragment()
    }
}
