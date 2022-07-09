package github.dev_playground.jeju_road.presentation.model

import github.dev_playground.jeju_road.presentation.util.SimpleItemDiffCallback

sealed class RestaurantInformationModel: SimpleItemDiffCallback.DiffCallback {
    abstract val id: Long

    override fun areContentsTheSame(other: SimpleItemDiffCallback.DiffCallback): Boolean {
        return if (other is RestaurantInformationModel) {
            this.id == other.id
        } else {
            super.areContentsTheSame(other)
        }
    }

}