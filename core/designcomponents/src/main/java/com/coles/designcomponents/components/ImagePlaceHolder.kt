package com.coles.designcomponents.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ImageNotSupported
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun ImagePlaceHolder(modifier: Modifier, imageUrl: String?) {
    val model = ImageRequest
        .Builder(LocalContext.current)
        .data(imageUrl)
        .size(Size.ORIGINAL)
        .build()
    val imageState = rememberAsyncImagePainter(model = model).state

    Box(modifier = Modifier.fillMaxWidth()) {
        if (imageState is AsyncImagePainter.State.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.Center)
//                color = MaterialTheme.color.secondaryBackground
            )
        }
        if (imageState is AsyncImagePainter.State.Success) {
            Image(
                painter = imageState.painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.scrim)
            )
        }
        if (imageState is AsyncImagePainter.State.Error) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(MaterialTheme.colorScheme.scrim)
                .align(Alignment.Center)) {
                Icon(
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.Center),
                    imageVector = Icons.Rounded.ImageNotSupported,
                    contentDescription = null,
                )
            }
        }
    }
}