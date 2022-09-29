package com.piriurna.common.models

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.graphics.vector.ImageVector

open class BottomNavigationItem(
    val route : String,
    val iconId : Int,
    val title : String
) {
}
