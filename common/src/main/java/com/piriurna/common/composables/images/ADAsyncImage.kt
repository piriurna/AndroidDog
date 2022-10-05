package com.piriurna.common.composables.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.piriurna.common.R

@Composable
fun ADAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl : String,
    contentDescription : String
) {
    AsyncImage(
        modifier = modifier,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .fallback(R.drawable.ic_dog)
            .error(R.drawable.ic_dog)
            .crossfade(true)
            .build(),
        contentDescription = contentDescription
    )
}


@Preview
@Composable
fun ADAsyncImagePreview() {
    ADAsyncImage(modifier = Modifier, "", "")
}