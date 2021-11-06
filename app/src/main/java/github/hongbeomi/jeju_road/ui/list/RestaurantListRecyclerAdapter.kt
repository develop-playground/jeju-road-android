package github.hongbeomi.jeju_road.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import github.hongbeomi.jeju_road.databinding.ItemRestaurantListBinding
import github.hongbeomi.jeju_road.domain.model.Information

class RestaurantListRecyclerAdapter :
    RecyclerView.Adapter<RestaurantListRecyclerAdapter.ViewHolder>() {

    private var itemList = listOf<Information>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRestaurantListBinding.inflate(LayoutInflater.from(parent.context))
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount() = itemList.size

    fun submitList(list: List<Information>) {
        itemList = list.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemRestaurantListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Information) {
            binding.information = data
            binding.executePendingBindings()
        }
    }

}