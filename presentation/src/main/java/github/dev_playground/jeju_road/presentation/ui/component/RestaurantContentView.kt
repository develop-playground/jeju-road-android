package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DimenRes
import androidx.core.widget.TextViewCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import github.dev_playground.jeju_road.domain.model.DetailInformation
import github.dev_playground.jeju_road.domain.model.Menu
import github.dev_playground.jeju_road.domain.model.OpenTime
import github.dev_playground.jeju_road.presentation.R
import github.dev_playground.jeju_road.presentation.databinding.*
import github.dev_playground.jeju_road.presentation.ui.base.BaseListAdapter
import github.dev_playground.jeju_road.presentation.ui.image.FullSizeImageActivity
import github.dev_playground.jeju_road.presentation.ui.image.FullSizeImageActivity.Companion.KEY_URL
import github.dev_playground.jeju_road.presentation.util.RoundRectOutlineProvider
import github.dev_playground.jeju_road.presentation.util.currentDayOfWeek
import github.dev_playground.jeju_road.presentation.util.startActivity
import java.text.SimpleDateFormat

class RestaurantContentView
@JvmOverloads
constructor(
    context: Context,
    attr: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BaseCustomView<ViewRestaurantContentBinding>(context, attr, defStyleAttr) {

    override fun getLayoutId() = R.layout.view_restaurant_content
    private val contentImageAdapter by lazy { ContentImageListAdapter() }

    init {
        with(binding) {
            viewPager2RestaurantContent.adapter = contentImageAdapter
            textViewRestaurantContentImageCount.outlineProvider =
                RoundRectOutlineProvider(R.dimen.dp_12)
        }
    }

    fun setContentInformation(information: DetailInformation) {
        setTitle(information.name)
        setIntroduction(information.introduction)
        setContentImageList(information.images)
        setMenuList(information.menus)
    }

    private fun setTitle(title: String) {
        binding.textViewRestaurantContentTitle.text = title
    }

    private fun setIntroduction(introduction: String) {
        binding.textViewRestaurantContentIntroduction.text = introduction
    }

    private fun setContentImageList(images: List<String>?) {
        contentImageAdapter.submitList(images ?: EMPTY_URL_LIST)

        val imageCount = images?.size ?: 0
        binding.run {
            isMoreThanImageCountOne = imageCount > 1
            textViewRestaurantContentImageCount.text =
                resources.getString(R.string.text_restaurant_content_image_count, imageCount)

            viewPager2RestaurantContent.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    textViewRestaurantContentImageCount.text =
                        resources.getString(
                            R.string.text_restaurant_content_image_current_count,
                            position + 1,
                            imageCount
                        )
                }
            })
        }
    }

    private fun setMenuList(menus: List<Menu>) {
        with(binding.recyclerViewRestaurantContentMenu) {
            adapter = ContentMenuListAdapter().apply {
                submitList(menus)
            }
            addItemDecoration(ContentMenuItemDecoration())
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
                binding.imageViewItemRestaurantContent.run {
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
                binding.imageViewItemRestaurantContentMenu.run {
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

    private class ContentMenuItemDecoration(
        @DimenRes
        private val spaceDimenResId: Int = R.dimen.dp_4
    ) : RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val count = parent.adapter?.itemCount ?: 0
            val position = parent.getChildAdapterPosition(view)
            val space = view.context.resources.getDimensionPixelSize(spaceDimenResId)

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