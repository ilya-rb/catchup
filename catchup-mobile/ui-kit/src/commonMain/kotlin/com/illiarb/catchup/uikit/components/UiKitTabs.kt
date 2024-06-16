package com.illiarb.catchup.uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class UiKitTab(
  val id: String,
  val text: String,
  val url: String,
)

@Composable
fun UiKitTabs(
  modifier: Modifier = Modifier,
  items: List<UiKitTab>,
  itemContent: @Composable LazyItemScope.(Int, UiKitTab) -> Unit,
) {
  LazyRow(
    modifier = modifier.padding(start = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp),
  ) {
    itemsIndexed(
      items = items,
      key = { _, model -> model.id },
      itemContent = itemContent,
    )
  }
}
