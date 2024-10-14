package org.kmp.kmp_ktor.networking.models.meals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealsList(
    @SerialName("meals")
    val meals: List<MealData>
)