package com.omooooori.composablelab.ui

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.ImageLoader
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.controller.BaseControllerListener
import com.facebook.imagepipeline.image.ImageInfo
import com.omooooori.composablelab.databinding.FragmentImageLoaderBinding
import com.omooooori.composablelab.ui.viewmodel.ImageLoaderViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
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
        val startTime = System.currentTimeMillis()
        Glide.with(requireContext())
            .load(viewModel.imageUrl)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                    return false
                }

                override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                    val endTime = System.currentTimeMillis()
                    val duration = endTime - startTime
                    binding.glidePerformanceLabel.text = "Glide: ${duration}ms"
                    return false
                }
            })
            .into(binding.glideImageView)
    }

    private fun loadFrescoImage() {
        val startTime = System.currentTimeMillis()
        val listener = object : BaseControllerListener<ImageInfo>() {
            override fun onFinalImageSet(id: String?, imageInfo: ImageInfo?, animatable: Animatable?) {
                val endTime = System.currentTimeMillis()
                val duration = endTime - startTime
                binding.frescoPerformanceLabel.text = "Fresco: ${duration}ms"
            }

            override fun onFailure(id: String?, throwable: Throwable?) {
                Timber.e("Failed to load image using fresco. id: $id, throwable: ${throwable.toString()}")
            }
        }

        val controller = Fresco.newDraweeControllerBuilder()
            .setUri(Uri.parse(viewModel.imageUrl))
            .setControllerListener(listener)
            .build()
        binding.frescoImageView.controller = controller
    }

    private fun loadCoilImage() {
        val startTime = System.currentTimeMillis()
        val request = ImageRequest.Builder(requireContext())
            .data(viewModel.imageUrl)
            .target(binding.coilImageView)
            .listener(
                onSuccess = { _, _ ->
                    val endTime = System.currentTimeMillis()
                    val duration = endTime - startTime
                    binding.coilPerformanceLabel.text = "Coil: ${duration}ms"
                },
                onError = { _, _ ->
                    Timber.e("Failed to load image using coil.")
                }
            )
            .build()
        ImageLoader(requireContext()).enqueue(request)
    }

    private fun loadPicassoImage() {
        val startTime = System.currentTimeMillis()
        Picasso.get()
            .load(viewModel.imageUrl)
            .into(binding.picassoImageView, object : Callback {
                override fun onSuccess() {
                    val endTime = System.currentTimeMillis()
                    val duration = endTime - startTime
                    binding.picassoPerformanceLabel.text = "Picasso: ${duration}ms"
                }

                override fun onError(e: Exception?) {
                    Timber.e("Failed to load image using picasso. e: ${e.toString()}")
                }
            })
    }

    companion object {
        @JvmStatic
        fun newInstance() = ImageLoaderFragment()
    }
}
