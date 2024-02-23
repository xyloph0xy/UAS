package com.luluksofiyah.uas.ui.screens.home

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.luluksofiyah.uas.data.Resource
import com.luluksofiyah.uas.data.model.Movie
import com.luluksofiyah.uas.ui.components.MovieListItem

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val popularMoviesState by homeViewModel.popularMoviesState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        item {
            Text(
                text = "Popular Movies",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 18.dp, bottom = 15.dp, top = 20.dp)
            )
        }

        handlePopularMoviesState(
            popularMoviesState = popularMoviesState,
            navController = navController
        )
    }
}

fun LazyListScope.handlePopularMoviesState(
    popularMoviesState: Resource<List<Movie>>,
    navController: NavController
) {
    when (popularMoviesState) {
        is Resource.Loading -> {}

        is Resource.Success -> {
            val movies = popularMoviesState.data

            items(movies.size) { index ->
                MovieListItem(movie = movies[index], navController = navController)
            }

        }

        is Resource.Error -> {}
    }
}