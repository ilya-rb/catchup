package com.illiarb.catchup.uikit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ImageCell(
  text: String,
  imageUrl: String,
  modifier: Modifier = Modifier,
  selected: Boolean,
  onClick: () -> Unit,
) {
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = modifier.clickable { onClick() },
  ) {
    UiKitImage(
      model = UiKitImageModel.Url(imageUrl),
      modifier = Modifier
        .size(60.dp)
        .clip(MaterialTheme.shapes.small)
        .let {
          if (selected) {
            it.border(
              border = BorderStroke(width = 2.dp, color = Color.White),
              shape = CircleShape,
            )
          } else {
            it
          }
        }
    )
    Text(
      text = text,
      modifier = Modifier.padding(top = 16.dp),
      style = MaterialTheme.typography.bodyMedium,
    )
  }
}
