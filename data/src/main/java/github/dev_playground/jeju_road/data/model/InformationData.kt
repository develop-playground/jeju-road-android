package github.dev_playground.jeju_road.data.model

import github.dev_playground.jeju_road.domain.model.Information

data class InformationData(
    val id: Long,
    val name: String,
    val category: List<String>,
    val address: String,
    val image: String,
    val introduction: String
)

fun InformationData.toDomain(): Information {
    return Information(
        id = this.id,
        name = this.name,
        category = this.category,
        address = this.address,
        image = this.image,
        introduction = this.introduction
    )
}