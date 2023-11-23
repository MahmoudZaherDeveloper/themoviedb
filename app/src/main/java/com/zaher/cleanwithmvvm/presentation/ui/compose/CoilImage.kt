package com.zaher.cleanwithmvvm.presentation.ui.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.zaher.cleanwithmvvm.R

@Composable
fun CoilImage(imageUrl: String) {
    Image(
        painter = // Optional: Add image transformations
        rememberAsyncImagePainter(
            ImageRequest.Builder(LocalContext.current)
                .data(data = "https://image.tmdb.org/t/p/w600_and_h900_bestv2${imageUrl}")
                .apply(block = fun ImageRequest.Builder.() {
                    // Optional: Add image transformations
                    placeholder(R.drawable.ic_launcher_foreground)
                }).build()
        ),
        contentDescription = "Coil Image",
        modifier = Modifier
            .size(80.dp)
            .clip(RoundedCornerShape(4.dp))
    )
}