package org.kmp.kmp_ktor.networking.repository

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException
import org.kmp.kmp_ktor.networking.models.meal.MealItem
import org.kmp.kmp_ktor.networking.models.meals.MealsList
import org.kmp.kmp_ktor.networking.util.Api
import org.kmp.kmp_ktor.networking.util.Response

class Repository(
    private val client: HttpClient
) {
    fun fetchMeals(location: String = "British"): Flow<Response<MealsList>> = flow {
        emit(Response.Loading())
        try {
            val url = "${Api.BASE_URL}${Api.CATEGORIES_URL}?a=$location"
            println("Requesting URL: $url")
            // Make the request
            val mealDto: MealsList = client.get(url).body() // Safely retrieve response body
            emit(Response.Success(mealDto)) // Emit success response
        } catch (e: IOException) {
            emit(Response.Error(e)) // Network error or I/O error
        } catch (e: Exception) {
            emit(Response.Error(e)) // General exception handling
        }
    }

    fun fetchMealById(mealId: String): Flow<Response<MealItem>> = flow {
        emit(Response.Loading())
        try {
            val mealDto: MealItem = client.get {
                url("${Api.BASE_URL}${Api.MEAL_DETAILS_URL}") {
                    parameters.append("i", mealId) // Query parameter for meal ID
                }
            }.body() // Safely retrieve response body
            emit(Response.Success(mealDto))
        } catch (e: IOException) {
            emit(Response.Error(e)) // Network error or I/O error
        } catch (e: Exception) {
            emit(Response.Error(e)) // General exception handling
        }
    }
}