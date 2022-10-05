package com.piriurna.common.composables.list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.piriurna.common.composables.images.ADAsyncImage

@Composable
fun ADImageTitleListRow(
    modifier : Modifier = Modifier,
    imageUrl : String,
    title : String,
    imageWidth : Dp = 0.dp,
    imageHeight : Dp = 0.dp
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(25.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ADAsyncImage(
            modifier = Modifier
                .width(imageWidth)
                .height(imageHeight),
            imageUrl = imageUrl,
            contentDescription = "Breed Image"
        )

        Text(modifier = Modifier, text = title, overflow = TextOverflow.Ellipsis, maxLines = 1)

    }
    Divider()
}


@Preview(showBackground = true)
@Composable
fun ADImageTitleListRowPreview() {
    val width = 300
    Column() {
        ADImageTitleListRow(
            modifier = Modifier,
            imageUrl = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            title = "Breed Test",
        )
    }

}