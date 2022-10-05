package com.piriurna.common.composables.list

import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.piriurna.common.R
import com.piriurna.common.models.ListSelectorItem

@Composable
fun ADGridListSelector(
    selectedColor : Color = Color.White,
    unselectedColor : Color = Color.LightGray,
    onOptionSelected : (ListSelectorItem) -> Unit,
    selectedOption : ListSelectorItem,
    itemSize : Int = 30,
) {

    var listOptionColor = unselectedColor
    var gridOptionColor = unselectedColor

    when(selectedOption) {
        ListSelectorItem.LIST -> {
            listOptionColor = selectedColor
            gridOptionColor = unselectedColor
        }
        ListSelectorItem.GRID -> {
            listOptionColor = unselectedColor
            gridOptionColor = selectedColor
        }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(15.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(
            modifier = Modifier
                .width(itemSize.dp)
                .height(itemSize.dp)
                .clickable {
                    onOptionSelected(ListSelectorItem.GRID)
                },
            painter = painterResource(id = R.drawable.ic_grid),
            contentDescription = "Grid Option",
            colorFilter = ColorFilter.tint(gridOptionColor)
        )

        Divider(
            modifier = Modifier
                .height(itemSize.dp)
                .width(1.dp)
        )

        Image(
            modifier = Modifier
                .width(itemSize.dp)
                .height(itemSize.dp)
                .clickable { onOptionSelected(ListSelectorItem.LIST) },
            painter = painterResource(id = R.drawable.ic_list),
            contentDescription = "List Option",
            colorFilter = ColorFilter.tint(listOptionColor)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ADGridListSelectorPreview() {
    var selectedOption by remember {
        mutableStateOf(ListSelectorItem.LIST)
    }
    ADGridListSelector(onOptionSelected = { selectedOption = ListSelectorItem.GRID }, selectedOption = selectedOption)
}