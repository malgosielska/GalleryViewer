package com.example.galleryviewer

import WebClient
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PhotosViewModel : ViewModel() {
    private val _photosLiveData: MutableState<List<Photo>> = mutableStateOf(emptyList())
    val photosLiveData: MutableState<List<Photo>> = _photosLiveData

    val selectedPhoto = mutableStateOf<Photo?>(null)

    fun loadPhotos() {
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages()
            val photosList = searchResponse.photos.photo.map { photo ->
                val imageUrl =
                    "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg"

                Photo(
                    id = photo.id,
                    url = imageUrl,
                    title = photo.title,
                    dateTaken = photo.datetaken,
                    ownerName = photo.ownername,
                    tags = photo.tags,
                    views = photo.views
                )
            }
            _photosLiveData.value = photosList
        }
    }

    fun changeSelectedPhoto(photo: Photo) {
        selectedPhoto.value = photo
    }
}