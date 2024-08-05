package com.illiarb.catchup.uikit.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.illiarb.catchup.uikit.imageloader.UrlImage

@Composable
fun SelectableCircleAvatar(
  modifier: Modifier = Modifier,
  imageUrl: String,
  selected: Boolean,
  onClick: () -> Unit,
) {
  UrlImage(
    url = imageUrl,
    contentScale = ContentScale.Inside,
    modifier = modifier
      .clip(MaterialTheme.shapes.small)
      .let {
        if (selected) {
          it.border(
            border = BorderStroke(
              width = 1.dp,
              color = MaterialTheme.colorScheme.onSurface,
            ),
            shape = CircleShape,
          )
        } else {
          it
        }
      }
      .clickable { onClick() }
  )
}
