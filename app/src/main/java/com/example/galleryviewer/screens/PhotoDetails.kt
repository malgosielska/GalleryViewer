package com.example.galleryviewer.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galleryviewer.Photo
import com.example.galleryviewer.PhotosViewModel
import com.skydoves.landscapist.glide.GlideImage


@Composable
fun DisplayPhotoScreen(viewModel: PhotosViewModel) {
    val selectedPhoto = viewModel.selectedPhoto.value

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        if (selectedPhoto != null) {
            MyAppTopBar("Photo details")
            PrintPhoto(selectedPhoto = selectedPhoto)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Data",
                style = TextStyle(
                    fontSize = 15.sp,
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp),
                color = Color.Black
            )
            PrintDescription(selectedPhotoItem = selectedPhoto)
        }
    }
}

@Composable
fun PrintPhoto(selectedPhoto: Photo) {
    GlideImage(
        imageModel = selectedPhoto.url,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .padding(vertical = 5.dp)
            .fillMaxWidth()
            .heightIn(max = 400.dp)
    )
}

@Composable
fun PrintDescription(selectedPhotoItem: Photo) {
    val descriptionItems = listOf(
        Pair("Title", selectedPhotoItem.title),
        Pair("Owner name", selectedPhotoItem.ownerName),
        Pair("Date taken", selectedPhotoItem.dateTaken),
        Pair("Tags", selectedPhotoItem.tags),
        Pair("Views", selectedPhotoItem.views)
    )
    LazyColumn(
        contentPadding = PaddingValues(bottom = 70.dp),
        modifier = Modifier.height(350.dp)
    ) {

        items(descriptionItems) { (label, text) ->
            DescriptionItem(label, text)
        }
    }
}

@Composable
fun DescriptionItem(label: String, text: String?) {
    if (text?.isNotBlank() == true) {
        Text(
            text = "${label}: $text", modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 3.dp),
            textAlign = TextAlign.Justify,
            style = TextStyle(
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        )
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = Color.Gray
        )
    }
}