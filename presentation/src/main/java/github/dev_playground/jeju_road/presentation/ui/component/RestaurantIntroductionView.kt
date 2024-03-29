package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import github.dev_playground.jeju_road.domain.model.Menu
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.*
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.presentation.ui.image.FullSizeImageActivity
import github.dev_playground.jeju_road.presentation.ui.image.FullSizeImageActivity.Companion.KEY_URL
import github.dev_playground.jeju_road.presentation.util.RoundRectOutlineProvider
import github.dev_playground.jeju_road.presentation.util.startActivity
import github.dev_playground.jeju_road.presentation.databinding.ItemRestaurantIntroductionImageBinding
import github.dev_playground.jeju_road.presentation.model.RestaurantIntroductionModel

class RestaurantIntroductionView
@JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : BaseCustomView<ViewRestaurantIntroductionBinding>(context, attr, defStyleAttr) {

    override fun getLayoutId() = R.layout.view_restaurant_introduction
    private val contentImageAdapter by lazy { ContentImageListAdapter() }

    init {
        with(binding) {
            viewPager2RestaurantContent.adapter = contentImageAdapter
            textViewRestaurantIntroductionImageCount.outlineProvider =
                RoundRectOutlineProvider(R.dimen.restaurant_introduction_image_count_radius)
        }
    }

    fun setIntroduction(introduction: RestaurantIntroductionModel) {
        setTitle(introduction.name)
        setIntroduction(introduction.introduction)
        setContentImageList(introduction.images)
        setMenuList(introduction.menus)
    }

    private fun setTitle(title: String) {
        binding.textViewRestaurantIntroductionTitle.text = title
    }

    private fun setIntroduction(introduction: String) {
        binding.textViewRestaurantIntroductionIntroduction.text = introduction
    }

    private fun setContentImageList(images: List<String>?) {
        contentImageAdapter.submitList(images ?: EMPTY_URL_LIST)

        val imageCount = images?.size ?: 0
        with(binding) {
            isMoreThanImageCountOne = imageCount > 1
            textViewRestaurantIntroductionImageCount.text =
                resources.getString(R.string.text_restaurant_introduction_image_count, imageCount)

            viewPager2RestaurantContent.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    textViewRestaurantIntroductionImageCount.text =
                        resources.getString(
                            R.string.text_restaurant_introduction_image_current_count,
                            position + 1,
                            imageCount
                        )
                }
            })
        }
    }

    private fun setMenuList(menus: List<Menu>) =
        with(binding.recyclerViewRestaurantIntroductionMenu) {
            binding.isEmptyMenu = menus.isEmpty()
            adapter = ContentMenuListAdapter().apply {
                submitList(menus)
            }
            addItemDecoration(IntroductionMenuItemDecoration())
        }

    private inner class ContentImageListAdapter : BaseListAdapter<String>(IMAGE_DIFF_CALLBACK) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
            return ImagePagerViewHolder(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.item_restaurant_introduction_image,
                    parent,
                    false
                )
            )
        }

        private inner class ImagePagerViewHolder(val binding: ItemRestaurantIntroductionImageBinding) :
            BaseViewHolder(binding.root) {

            init {
                with(binding.imageViewItemRestaurantIntroduction) {
                    setOnClickListener {
                        val imageUrl = getItem(bindingAdapterPosition)

                        if (imageUrl != EMPTY_URL) {
                            it.context.startActivity<FullSizeImageActivity> {
                                putExtra(KEY_URL, imageUrl)
                            }
                        }
                    }
                    outlineProvider = RoundRectOutlineProvider()
                }
            }

            override fun bind(data: String) {
                with(binding) {
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
                    R.layout.item_restaurant_introduction_menu,
                    parent,
                    false
                )
            )
        }

        private inner class ContentMenuViewHolder(
            val binding: ItemRestaurantIntroductionMenuBinding,
        ) : BaseViewHolder(binding.root) {
            init {
                with(binding.imageViewItemRestaurantIntroductionMenu) {
                    setOnClickListener {
                        it.context.startActivity<FullSizeImageActivity> {
                            putExtra(KEY_URL, getItem(bindingAdapterPosition).image)
                        }
                    }
                }
            }

            override fun bind(data: Menu) {
                with(binding) {
                    menu = data
                    executePendingBindings()
                }
            }
        }

    }

    private class IntroductionMenuItemDecoration : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State,
        ) {
            val count = parent.adapter?.itemCount ?: 0
            val position = parent.getChildAdapterPosition(view)
            val space =
                view.context.resources.getDimensionPixelSize(R.dimen.restaurant_introduction_menu_space)

            when (position) {
                0 -> outRect.right = space
                count -> outRect.left = space
                else -> {
                    outRect.left = space
                    outRect.right = space
                }
            }
            outRect.bottom = space
        }
    }

    companion object {
        private const val EMPTY_URL = "empty"
        private val EMPTY_URL_LIST = listOf(EMPTY_URL)
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