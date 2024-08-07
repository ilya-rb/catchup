package com.illiarb.catchup.uikit.imageloader

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage

@Composable
fun UrlImage(
  modifier: Modifier = Modifier,
  url: String,
  contentScale: ContentScale = ContentScale.Crop,
) {
  AsyncImage(
    model = url,
    contentDescription = null,
    modifier = modifier,
    contentScale = contentScale,
  )
}
