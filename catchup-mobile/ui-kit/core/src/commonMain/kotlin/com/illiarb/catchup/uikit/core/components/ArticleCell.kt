package com.illiarb.catchup.uikit.core.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp

@Composable
fun ArticleCell(
  modifier: Modifier = Modifier,
  topText: String,
  bottomText: String,
  tag: String,
  onClick: () -> Unit = {},
) {
  Column(modifier = modifier.clickable { onClick.invoke() }) {
    Text(
      text = topText,
      style = MaterialTheme.typography.titleLarge,
      modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
      color = MaterialTheme.colorScheme.onSurface,
      fontWeight = FontWeight.Bold,
      overflow = TextOverflow.Ellipsis,
      maxLines = 3,
    )

    Text(
      text = bottomText,
      style = MaterialTheme.typography.bodyMedium,
      color = MaterialTheme.colorScheme.onSurface,
      modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(top = 8.dp),
      maxLines = 2,
      overflow = TextOverflow.Ellipsis,
    )

    Row(verticalAlignment = Alignment.CenterVertically) {
      FilterChip(
        selected = true,
        label = { Text(tag) },
        modifier = Modifier.padding(start = 16.dp, top = 16.dp).height(24.dp),
        onClick = {},
      )

      Spacer(Modifier.weight(1f))

      Icon(
        imageVector = Icons.AutoMirrored.Rounded.List,
        tint = MaterialTheme.colorScheme.onSurface,
        contentDescription = null,
        modifier = Modifier.padding(end = 16.dp).padding(top = 8.dp)
      )

      Icon(
        imageVector = Icons.Filled.Share,
        tint = MaterialTheme.colorScheme.onSurface,
        contentDescription = null,
        modifier = Modifier.padding(end = 16.dp).padding(top = 8.dp)
      )
    }
  }
}
