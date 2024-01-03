package com.example.galleryviewer


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.galleryviewer.screens.DisplayPhotoScreen
import com.example.galleryviewer.screens.MainScreen


const val MAIN_SCREEN_ROUTE = "main_screen"
const val PHOTO_ROUTE = "photo_screen"

@Composable
fun Navigation(myViewModel: PhotosViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN_ROUTE
    ) {
        composable(MAIN_SCREEN_ROUTE) {
            MainScreen(navController = navController, viewModel = myViewModel)
        }

        composable(
            route = PHOTO_ROUTE,
        ) {
            DisplayPhotoScreen(viewModel = myViewModel)
        }
    }
}