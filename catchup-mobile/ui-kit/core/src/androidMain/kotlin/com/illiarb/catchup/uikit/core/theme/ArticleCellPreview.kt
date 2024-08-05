package com.illiarb.catchup.uikit.core.theme

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.illiarb.catchup.uikit.core.components.ArticleCell

@Composable
fun ArticleCellPreview(darkTheme: Boolean) {
  UiKitTheme(useDynamicColors = false, useDarkTheme = darkTheme) {
    LazyColumn {
      items(
        count = 3,
        key = { index -> index },
        itemContent = {
          ArticleCell(
            topText = "Title text Title text Title text Title text",
            bottomText = "Description description description description description description",
            tag = "Technology",
          )
          HorizontalDivider(
            thickness = 0.2.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.padding(vertical = 8.dp),
          )
        }
      )
    }
  }
}

@Composable
@Preview(
  showBackground = true,
  backgroundColor = 0xFFF9F9FF,
)
fun ArticlePreviewLight() {
  ArticleCellPreview(darkTheme = false)
}

@Composable
@Preview(
  showBackground = true,
  backgroundColor = 0xFF111318,
)
fun ArticlePreviewDark() {
  ArticleCellPreview(darkTheme = true)
}
