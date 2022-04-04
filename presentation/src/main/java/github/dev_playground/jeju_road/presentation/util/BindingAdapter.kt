package github.dev_playground.jeju_road.presentation.util

import android.widget.ImageView
import androidx.annotation.DimenRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import github.dev_playground.jeju_road.presentation.R

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_fail_load_36)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(imageView)
}

@BindingAdapter(value = ["roundImageUrl", "roundImageRadius"], requireAll = false)
fun loadImageRound(imageView: ImageView, imageUrl: String?, radius: Float?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .placeholder(R.drawable.ic_fail_load_36)
        .transform(
            CenterCrop(), RoundedCorners(radius?.toInt() ?:8)
        )
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(imageView)
}