package com.piriurna.common.composables.cards

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.piriurna.common.composables.images.ADAsyncImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ADCard(
    modifier : Modifier = Modifier,
    imageUrl : String,
    title : String,
    imageWidth : Dp,
    imageHeight : Dp,
    onClick : () -> Unit
) {
    Card(modifier = modifier, onClick = onClick) {
        Column(verticalArrangement = Arrangement.Center) {
            ADAsyncImage(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .height(imageHeight)
                    .width(imageWidth),
                imageUrl = imageUrl,
                contentDescription = "Breed Image"
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    modifier = Modifier.align(Alignment.CenterVertically),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun ADCardPreview() {
    val width = 300
    Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
        ADCard(
            modifier = Modifier,
            imageHeight = (width.toFloat() * 0.7498138495904691).dp ,
            imageWidth = (width.dp),
            imageUrl = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
            title = "Breed Test",
            onClick = {}
        )
    }

}