package org.kmp.kmp_ktor.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import kotlinktor.composeapp.generated.resources.Res
import kotlinktor.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.kmp.kmp_ktor.networking.models.meals.MealData

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel(),
    onMealClick: (MealData) -> Unit
) {
    val homeState by homeViewModel.homeState.collectAsState()

    Column(modifier) {
        if (homeState.isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }
        if (homeState.error != null) {
            Text(homeState.error.orEmpty())
            log(
                tag = "HomeScreen",
                message = homeState.error.orEmpty()
            )
        }

        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(homeState.meals) { meal ->
                FoodItem(meal = meal) {
                    onMealClick(meal)
                }
            }
        }
    }
}

@Composable
fun FoodItem(
    modifier: Modifier = Modifier,
    meal: MealData,
    onMealClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .clickable { onMealClick() }
    ) {
        AsyncImage(
            model = meal.strMealThumb,
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(Res.drawable.compose_multiplatform),
            onError = { state ->
                state.result.throwable.printStackTrace()
            }
        )
        Surface(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            color = MaterialTheme.colors.surface.copy(
                .5f
            )
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.padding(8.dp).fillMaxWidth()
            ) {
                Text(
                    text = meal.strMeal,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }
    }

}

expect fun log(tag: String, message: String)