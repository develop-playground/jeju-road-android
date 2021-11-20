package github.dev_playground.jeju_road.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.widget.ViewPager2
import github.dev_playground.jeju_road.R
import github.dev_playground.jeju_road.databinding.ItemRestaurantContentImageBinding
import github.dev_playground.jeju_road.databinding.ViewRestaurantContentBinding
import github.dev_playground.jeju_road.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.util.showShort

class RestaurantContentView
@JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseCustomView<ViewRestaurantContentBinding>(context, attr, defStyleAttr) {

    override fun getLayoutId() = R.layout.view_restaurant_content

    fun setTitle(title: String) {
        binding.textViewRestaurantContentTitle.text = title
    }

    fun setIntroduction(introduction: String) {
        binding.textViewRestaurantContentIntroduction.text = introduction
    }

    fun setContentImageList(images: List<String>) {
        binding.viewPager2RestaurantContent.apply {
            adapter = ContentImageListAdapter().apply {
                submitList(images)
            }
            binding.textViewRestaurantContentImageCount.text = "0/${images.size}"

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.textViewRestaurantContentImageCount.text = "$position/${images.size}"
                }
            })
        }
    }

    inner class ContentImageListAdapter : BaseListAdapter<String>(DIFF_CALLBACK) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            return ImagePagerViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_restaurant_content_image,
                    parent,
                    false
                )
            )
        }

        inner class ImagePagerViewHolder(val binding: ItemRestaurantContentImageBinding) : BaseViewHolder(binding.root) {

            init {
                binding.imageViewItemRestaurantContent.setOnClickListener {
                    binding.root.context.showShort("url :: ${getItem(bindingAdapterPosition)}")
                }
            }

            override fun bind(data: String) {
                binding.url = data
            }

        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
    }

}