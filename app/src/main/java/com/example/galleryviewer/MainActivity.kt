package com.example.galleryviewer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

class MainActivity : ComponentActivity() {
    private val photosViewModel: PhotosViewModel by lazy {
        ViewModelProvider(this)[PhotosViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation(myViewModel = photosViewModel)
        }
    }
}

