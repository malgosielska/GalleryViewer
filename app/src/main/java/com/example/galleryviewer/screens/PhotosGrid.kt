package com.example.galleryviewer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.galleryviewer.PHOTO_ROUTE
import com.example.galleryviewer.Photo
import com.example.galleryviewer.PhotosViewModel


@Composable
fun PhotoItem(photo: Photo, viewModel: PhotosViewModel, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(
                ImageRequest.Builder(LocalContext.current).data(data = photo.url)
                    .apply(block = fun ImageRequest.Builder.() {
                        crossfade(true)
                    }).build()
            ),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .clickable(onClick = {
                    viewModel.changeSelectedPhoto(photo)
                    navController.navigate(PHOTO_ROUTE)
                }
                ),
            contentScale = ContentScale.Crop,
        )
    }
}

@Composable
fun PhotosScreen(viewModel: PhotosViewModel, navController: NavController) {
    val photos = viewModel.photosLiveData.value

    LaunchedEffect(Unit) {
        viewModel.loadPhotos()
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .height(500.dp)
    ) {
        items(photos) { photo ->
            PhotoItem(photo, viewModel, navController)
        }
    }
}

@Composable
fun MainScreen(viewModel: PhotosViewModel, navController: NavController) {
    Column {
        MyAppTopBar(title = "Gallery Viewer")
        Spacer(modifier = Modifier.padding(5.dp))
        PhotosScreen(viewModel = viewModel, navController = navController)
    }
}