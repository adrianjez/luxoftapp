package com.luxoft.sample_app.presentation.search.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.luxoft.sample_app.BuildConfig
import com.luxoft.sample_app.R

@BindingAdapter("imagePath")
fun bindImagePath(view: ImageView, path: String?) {
    if (!path.isNullOrEmpty()) {
        Glide.with(view.context)
                .load(BuildConfig.MOVIE_DB_IMAGE_BASE_URL + path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(view)
    }
}

@BindingAdapter("favoriteState")
fun bindFavoriteState(view: ImageView, state: Boolean?) {
    state?.let {
        if(state) view.setImageResource(R.drawable.ic_selested_star)
        else view.setImageResource(R.drawable.ic_not_selected_star)
    }
}