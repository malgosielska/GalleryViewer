package com.example.galleryviewer.ui.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galleryviewer.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyAppTopBar(title: String) {
    val topBarFont = FontFamily(
        Font(R.font.pacifico_regular, FontWeight.Normal)
    )
    Surface {
        TopAppBar(
            modifier = Modifier
                .padding(5.dp),
            title = {
                Text(
                    text = title,
                    color = Color.Black,
                    style = TextStyle(
                        fontFamily = topBarFont,
                        fontWeight = FontWeight.Normal,
                        fontSize = 30.sp
                    )
                )
            },
        )
    }
}
