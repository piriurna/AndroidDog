package com.piriurna.common.composables.list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun ADListRow(
    modifier : Modifier = Modifier,
    imageUrl : String,
    title : String,
    imageWidth : Dp,
    imageHeight : Dp,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(25.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .width(imageWidth)
                .height(imageHeight),
            model = ImageRequest.Builder(LocalContext.current)
                .data(imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "Breed Image"
        )

        Text(text = title)

    }
    Divider()
}


@Preview(showBackground = true)
@Composable
fun ADListRowPreview() {
    val width = 300
    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        ADListRow(
            modifier = Modifier,
            imageHeight = (width.toFloat() * 0.7498138495904691).dp ,
            imageWidth = (width.dp),
            imageUrl = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            title = "Breed Test"
        )
    }

}