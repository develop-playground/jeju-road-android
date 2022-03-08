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
import github.dev_playground.jeju_road.databinding.ItemRestaurantContentMenuBinding
import github.dev_playground.jeju_road.databinding.ViewRestaurantContentBinding
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.Menu
import github.dev_playground.jeju_road.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.ui.image.FullSizeImageActivity
import github.dev_playground.jeju_road.ui.image.FullSizeImageActivity.Companion.KEY_URL
import github.dev_playground.jeju_road.util.RoundRectOutlineProvider
import github.dev_playground.jeju_road.util.startActivity

class RestaurantContentView
@JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseCustomView<ViewRestaurantContentBinding>(context, attr, defStyleAttr) {

    override fun getLayoutId() = R.layout.view_restaurant_content

    init {
        binding.apply {
            viewPager2RestaurantContent.outlineProvider = RoundRectOutlineProvider()
            frameLayoutRestaurantContentImageCount.outlineProvider =
                RoundRectOutlineProvider(R.dimen.dp_16)
        }
    }

    fun setContentInformation(information: DetailInformation) {
        setTitle(information.name)
        setIntroduction(information.introduction)
        setContentImageList(information.images)
        setMenuList(information.menus)
    }

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
            binding.textViewRestaurantContentImageCount.text =
                resources.getString(R.string.text_restaurant_content_image_count, images.size)

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    binding.textViewRestaurantContentImageCount.text =
                        resources.getString(
                            R.string.text_restaurant_content_image_current_count,
                            position + 1,
                            images.size
                        )
                }
            })
        }
    }

    private fun setMenuList(menus: List<Menu>) {
        binding.recyclerViewRestaurantContentMenu.adapter = ContentMenuListAdapter().apply {
            submitList(menus)
        }
    }

    private inner class ContentImageListAdapter : BaseListAdapter<String>(IMAGE_DIFF_CALLBACK) {

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

        private inner class ImagePagerViewHolder(val binding: ItemRestaurantContentImageBinding) :
            BaseViewHolder(binding.root) {

            init {
                binding.imageViewItemRestaurantContent.setOnClickListener {
                    it.context.startActivity<FullSizeImageActivity> {
                        putExtra(KEY_URL, getItem(bindingAdapterPosition))
                    }
                }
            }

            override fun bind(data: String) {
                binding.apply {
                    url = data
                    executePendingBindings()
                }
            }

        }
    }

    private inner class ContentMenuListAdapter : BaseListAdapter<Menu>(MENU_DIFF_CALLBACK) {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            return ContentMenuViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_restaurant_content_menu,
                    parent,
                    false
                )
            )
        }

        private inner class ContentMenuViewHolder(
            val binding: ItemRestaurantContentMenuBinding
        ) : BaseViewHolder(binding.root) {
            init {
                binding.imageViewItemRestaurantContentMenu.setOnClickListener {
                    it.context.startActivity<FullSizeImageActivity> {
                        putExtra(KEY_URL, getItem(bindingAdapterPosition).image)
                    }
                }
            }

            override fun bind(data: Menu) {
                binding.apply {
                    imageViewItemRestaurantContentMenu.outlineProvider = RoundRectOutlineProvider()
                    menu = data
                    executePendingBindings()
                }
            }

        }

    }

    companion object {
        private val IMAGE_DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem
            override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
        }
        private val MENU_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Menu>() {
            override fun areItemsTheSame(oldItem: Menu, newItem: Menu) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Menu, newItem: Menu) = oldItem == newItem
        }
    }

}