package com.omooooori.composablelab.ui;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.image.ImageInfo;
import com.omooooori.composablelab.databinding.FragmentImageLoaderBinding;
import com.omooooori.composablelab.ui.viewmodel.ImageLoaderViewModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import androidx.annotation.OptIn;
import androidx.fragment.app.Fragment;
import coil.Coil;
import coil.ImageLoader;
import coil.annotation.ExperimentalCoilApi;
import coil.request.CachePolicy;
import coil.request.ErrorResult;
import coil.request.ImageRequest;
import coil.request.SuccessResult;
import timber.log.Timber;

public class ImageLoaderFragmentJava extends Fragment {

    private FragmentImageLoaderBinding _binding = null;
    private FragmentImageLoaderBinding getBinding() {
        return Objects.requireNonNull(_binding);
    }

    @Inject
    ImageLoaderViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        _binding = FragmentImageLoaderBinding.inflate(inflater, container, false);
        return getBinding().getRoot();
    }

    @OptIn(markerClass = ExperimentalCoilApi.class)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadGlideImage();
        loadFrescoImage();
        loadCoilImage();
        loadPicassoImage();

        getBinding().clearCacheButton.setOnClickListener(v -> clearCache());
    }

    private void loadGlideImage() {
        long startTime = System.currentTimeMillis();
        Glide.with(requireContext())
             .load(viewModel.getImageUrl())
             .listener(new RequestListener<Drawable>() {
                 @Override
                 public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                     return false;
                 }

                 @Override
                 public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                     long endTime = System.currentTimeMillis();
                     long duration = endTime - startTime;
                     getBinding().glidePerformanceLabel.setText("Glide: " + duration + "ms");
                     return false;
                 }
             })
             .into(getBinding().glideImageView);
    }

    private void loadFrescoImage() {
        long startTime = System.currentTimeMillis();
        BaseControllerListener<ImageInfo> listener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                getBinding().frescoPerformanceLabel.setText("Fresco: " + duration + "ms");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                Timber.e("Failed to load image using fresco. id: " + id + ", throwable: " + throwable.toString());
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setUri(Uri.parse(viewModel.getImageUrl()))
                                            .setControllerListener(listener)
                                            .build();
        getBinding().frescoImageView.setController(controller);
    }

    private void loadCoilImage() {
        long startTime = System.currentTimeMillis();

        ImageLoader imageLoader = new ImageLoader.Builder(requireContext())
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build();

        ImageRequest request = new ImageRequest.Builder(requireContext())
            .data(viewModel.getImageUrl())
            .target(getBinding().coilImageView)
            .listener(new ImageRequest.Listener() {
                @Override
                public void onStart(@NotNull ImageRequest request) {}
                @Override
                public void onCancel(@NotNull ImageRequest request) {}
                @Override
                public void onError(@androidx.annotation.NonNull ImageRequest request, @androidx.annotation.NonNull ErrorResult result) {
                    Timber.e(result.toString(), "Failed to load image using coil.");
                }
                @Override
                public void onSuccess(@androidx.annotation.NonNull ImageRequest request, @androidx.annotation.NonNull SuccessResult result) {
                    // 成功時の処理
                    long endTime = System.currentTimeMillis();
                    long duration = endTime - startTime;
                    getBinding().coilPerformanceLabel.setText("Coil: " + duration + "ms");
                }
            })
            .memoryCachePolicy(CachePolicy.ENABLED)
            .diskCachePolicy(CachePolicy.ENABLED)
            .build();

        imageLoader.enqueue(request);
    }

    private void loadPicassoImage() {
        long startTime = System.currentTimeMillis();
        Picasso.get()
               .load(viewModel.getImageUrl())
               .into(getBinding().picassoImageView, new Callback() {
                   @Override
                   public void onSuccess() {
                       long endTime = System.currentTimeMillis();
                       long duration = endTime - startTime;
                       getBinding().picassoPerformanceLabel.setText("Picasso: " + duration + "ms");
                   }

                   @Override
                   public void onError(Exception e) {
                       Timber.e("Failed to load image using picasso. e: " + e.toString());
                   }
               });
    }

    @ExperimentalCoilApi
    private void clearCache() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            if (getContext() != null) {
                Glide.get(getContext()).clearDiskCache();
            }
        });

        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();

        ImageLoader imageLoader = Coil.imageLoader(requireContext());
        if (imageLoader.getMemoryCache() != null) {
            imageLoader.getMemoryCache().clear();
        }
        if (imageLoader.getDiskCache() != null) {
            imageLoader.getDiskCache().clear();
        }

        // Picasso does not support cache clearing.
    }

    public static ImageLoaderFragment newInstance() {
        return new ImageLoaderFragment();
    }
}
