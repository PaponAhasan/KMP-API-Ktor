package org.kmp.kmp_ktor.networking.models.meals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealData(
    @SerialName("idMeal")
    val idMeal: String,
    @SerialName("strMeal")
    val strMeal: String,
    @SerialName("strMealThumb")
    val strMealThumb: String
)