package com.luluksofiyah.uas.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.luluksofiyah.uas.ui.navigation.MoovyNavHost
import com.luluksofiyah.uas.ui.navigation.MoovyScreen
import com.luluksofiyah.uas.ui.theme.UASTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private var isSplashScreenClosed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().setKeepOnScreenCondition {
            !isSplashScreenClosed
        }
        super.onCreate(savedInstanceState)
        setContent {
            UASTheme {
                LaunchedEffect(key1 = Unit) {
                    delay(2000)
                    isSplashScreenClosed = true
                }
                MoovyApp()
            }
        }
    }
}

@Composable
fun MoovyApp(
    navController: NavHostController = rememberNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route?.let { route ->
        when (route) {
            MoovyScreen.HOME.route -> MoovyScreen.HOME
            "${MoovyScreen.DETAIL.route}/{movieId}" -> MoovyScreen.DETAIL
            MoovyScreen.FAVORITE.route -> MoovyScreen.FAVORITE
            else -> null
        }
    } ?: MoovyScreen.HOME

    Scaffold(
        topBar = {
            MoovyTopAppBar(
                currentScreen = currentScreen,
                canNavigateBack = navController.previousBackStackEntry != null,
                navigateUp = {
                    navController.navigateUp()
                },
                onFavoriteClicked = {
                    navController.navigate(MoovyScreen.FAVORITE.route)
                }
            )
        },
        content = { innerPadding ->
            MoovyNavHost(navController = navController, innerPadding = innerPadding)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoovyTopAppBar(
    currentScreen: MoovyScreen,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    onFavoriteClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                currentScreen.route,
                style = MaterialTheme.typography.titleLarge
            )
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            if (!canNavigateBack) {
                IconButton(onClick = onFavoriteClicked) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red
                    )
                }
            }
        },
    )
}