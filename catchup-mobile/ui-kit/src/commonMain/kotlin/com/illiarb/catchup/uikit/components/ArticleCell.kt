package com.illiarb.catchup.uikit.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun ArticleCell(
  topText: String,
  bottomText: String,
  image: UiKitImageModel,
  modifier: Modifier = Modifier,
  onClick: () -> Unit = {},
) {
  Row(
    modifier = modifier.clickable { onClick.invoke() },
    verticalAlignment = Alignment.CenterVertically
  ) {
    Column(verticalArrangement = Arrangement.Center) {
      Text(
        text = topText,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(horizontal = 16.dp)
      )
      Text(
        text = bottomText,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp)
      )
    }
    Spacer(Modifier.weight(1f))
    UiKitImage(
      model = image,
      modifier = Modifier
        .padding(vertical = 16.dp)
        .padding(end = 16.dp)
        .size(60.dp)
        .clip(MaterialTheme.shapes.small)
    )
  }
}
