package github.dev_playground.jeju_road.data.model

import java.text.DecimalFormat

data class Menu(
    val id: Long,
    val name: String,
    val image: String,
    val price: Long
) {

    fun formatPrice(): String {
        return DecimalFormat("#,###").format(price)
    }

}