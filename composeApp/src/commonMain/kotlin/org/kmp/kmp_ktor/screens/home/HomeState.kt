package org.kmp.kmp_ktor.screens.home

import org.kmp.kmp_ktor.networking.models.meals.MealData

data class HomeState(
    val meals: List<MealData> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)