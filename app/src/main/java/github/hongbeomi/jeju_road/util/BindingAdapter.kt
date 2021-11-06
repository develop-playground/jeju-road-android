package github.hongbeomi.jeju_road.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("bind:imageUrl")
fun loadImage(imageView: ImageView, imageUrl: String?) = imageUrl?.let {
    Glide.with(imageView.context)
        .load(imageUrl)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(imageView)
}