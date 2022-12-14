package com.piriurna.common.composables.scaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RowScope.ADNavigationItem(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    selected : Boolean = false,
    text: String,
    selectedColor : Color,
    unselectedColor : Color = Color.LightGray,
    onClick: () -> Unit = {}
) {
    val iconColor = if(selected) selectedColor else unselectedColor
    val textColor = if(selected) Color.Black else Color.LightGray

    Box(
        modifier = modifier
            .weight(1f)
            .align(Alignment.Bottom)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
                .clickable { onClick.invoke() }
                .align(Alignment.Center),
        ) {
            Icon(
                modifier = Modifier.size(28.dp),
                imageVector = icon,
                contentDescription = "Item Icon",
                tint = iconColor
            )

            Text(
                text = text,
                color = textColor,
                fontWeight = FontWeight.Bold
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun ADNavigationItemPreview() {
    Row(Modifier.fillMaxSize()) {
        ADNavigationItem(
            icon = Icons.Default.Home,
            text = "Play Games",
            selectedColor = MaterialTheme.colors.primary
        )

        ADNavigationItem(
            icon = Icons.Default.Menu,
            text = "Profile",
            selected = true,
            selectedColor = MaterialTheme.colors.primary
        )
    }
}