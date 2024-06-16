package com.illiarb.catchup.uikit.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

sealed class UiKitImageModel {

  data class Url(val url: String) : UiKitImageModel()

  internal fun unwrap(): Any = when (this) {
    is Url -> url
  }
}

@Composable
fun UiKitImage(model: UiKitImageModel, modifier: Modifier = Modifier) {
  AsyncImage(
    model = model.unwrap(),
    contentDescription = null,
    modifier = modifier,
    contentScale = ContentScale.Crop,
  )
}
