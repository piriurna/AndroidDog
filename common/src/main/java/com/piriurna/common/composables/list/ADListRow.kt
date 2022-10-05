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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import coil.size.Size
import coil.size.SizeResolver

@Composable
fun ADListRow(
    modifier : Modifier = Modifier,
    fields : List<String> = emptyList(),
    fieldSize : Dp = 100.dp
) {
    Row(
        modifier = modifier
    ) {
        fields.forEach {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(modifier = Modifier.width(fieldSize), text = it, overflow = TextOverflow.Ellipsis, maxLines = 1, textAlign = TextAlign.Center)

                Divider(
                    modifier = Modifier
                        .height(32.dp)
                        .width(1.dp)
                )

            }
        }

    }
    Divider()
}


@Preview(showBackground = true)
@Composable
fun ADListRowPreview() {
    val width = 300
    Column() {
        ADListRow(
            modifier = Modifier,
            fields = listOf("Breed Test","Category", "Origin")
        )
    }

}