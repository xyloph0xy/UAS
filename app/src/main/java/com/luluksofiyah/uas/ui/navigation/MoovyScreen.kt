package com.luluksofiyah.uas.ui.navigation

sealed class MoovyScreen(val route: String) {
    data object HOME : MoovyScreen("Home")
    data object DETAIL : MoovyScreen("Detail")
    data object FAVORITE : MoovyScreen("Favorite")
}