package org.kmp.kmp_ktor.networking.util

object Api {
    private const val API_KEY = "1"
    const val BASE_URL = "https://www.themealdb.com/api/json/v1/$API_KEY/"
    const val CATEGORIES_URL = "filter.php"
    const val MEAL_DETAILS_URL = "lookup.php"
}