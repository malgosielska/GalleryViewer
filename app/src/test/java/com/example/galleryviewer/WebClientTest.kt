package com.example.galleryviewer

import ApiService
import com.example.galleryviewer.api_connection.PhotoResponse
import com.example.galleryviewer.api_connection.PhotosMetaData
import com.example.galleryviewer.api_connection.PhotosSearchResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class WebClientTest {

    private lateinit var apiService: ApiService

    private fun createSamplePhotosSearchResponse(): PhotosSearchResponse {
        return PhotosSearchResponse(
            photos = PhotosMetaData(
                page = 1,
                photo = listOf(
                    PhotoResponse(
                        id = "11111",
                        owner = "myowner1",
                        secret = "mysecret1",
                        server = "myserver1",
                        farm = 1,
                        title = "My First Photo",
                        datetaken = "2024-02-01",
                        ownername = "MyOwner1",
                        tags = "tag1,tag2",
                        views = "100"
                    ),
                    PhotoResponse(
                        id = "22222",
                        owner = "myowner2",
                        secret = "mysecret2",
                        server = "myserver2",
                        farm = 2,
                        title = "My Second Photo",
                        datetaken = "2024-02-02",
                        ownername = "MyOwner2",
                        tags = "tag3,tag4",
                        views = "200"
                    )
                )
            )
        )
    }

    @Before
    fun setUp() {
        apiService = mock(ApiService::class.java)
    }

    @Test
    fun fetchPhotosSearchResponse_returnsResponse() = runBlocking {
        `when`(apiService.fetchImages()).thenReturn(createSamplePhotosSearchResponse())
        val response = apiService.fetchImages()
        assertNotNull(response)
        assertTrue(
            "Response should be of PhotosSearchResponse type",
            response is PhotosSearchResponse
        )
        assertEquals("Page should match", 1, response.photos.page)
        assertEquals("Number of photos should match", 2, response.photos.photo.size)
        assertEquals("First photo owner should match", "myowner1", response.photos.photo[0].owner)
        assertEquals("Second photo farm should match", 2, response.photos.photo[1].farm)
    }
}