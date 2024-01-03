package com.example.galleryviewer.data

// The outermost wrapper for the api response
data class PhotosSearchResponse(
    val photos: PhotosMetaData
)

data class PhotosMetaData(
    val page: Int,
    val photo: List<PhotoResponse>
)

data class PhotoResponse(
    val id: String,
    val owner: String,
    val secret: String,
    val server: String,
    val farm: Int,
    val title: String,
    val datetaken: String?,     // Tutaj dodaj typ pola dla każdego parametru "extras"
    val ownername: String?,
    val tags: String?,
    val views: String?
)