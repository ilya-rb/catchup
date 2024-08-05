package com.illiarb.catchup.features.home

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.min
import com.illiarb.catchup.uikit.core.components.ArticleCell
import com.illiarb.catchup.uikit.core.components.HorizontalList
import com.illiarb.catchup.uikit.core.components.SelectableCircleAvatar
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.screen.Screen
import com.slack.circuit.runtime.ui.Ui
import com.slack.circuit.runtime.ui.ui
import me.tatarka.inject.annotations.Inject

@Composable
internal fun HomeScreen(state: HomeScreenContract.State, modifier: Modifier = Modifier) {
  val density = LocalDensity.current
  val eventSink = state.eventSink

  val scrollState = rememberLazyListState()
  val scrolledToTop by remember {
    derivedStateOf {
      !scrollState.isScrollInProgress && scrollState.firstVisibleItemIndex == 0
    }
  }

  val topBarMaxSize = 92.dp
  val topBarMinSize = 60.dp
  val expandThreshold = 50.dp
  var topBarSize by remember { mutableStateOf(topBarMaxSize) }

  val insetsPadding = WindowInsets.systemBars
    .only(WindowInsetsSides.Top)
    .asPaddingValues()
    .calculateTopPadding()

  val nestedScrollConnection = remember {
    object : NestedScrollConnection {
      var total = 0.dp

      override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val densityAwareAvailable = with(density) { available.y.toDp() }

        total = (total - densityAwareAvailable).coerceAtLeast(minimumValue = 0.dp)

        when {
          available.y < 0 && topBarSize > topBarMinSize -> {
            topBarSize = max(topBarMinSize, topBarSize + densityAwareAvailable)
          }

          available.y > 0 && topBarSize < topBarMaxSize && total <= expandThreshold -> {
            topBarSize = min(topBarMaxSize, topBarSize + densityAwareAvailable)
          }
        }

        return Offset.Zero
      }
    }
  }

  Scaffold(
    modifier = modifier.nestedScroll(nestedScrollConnection),
    topBar = {
      Surface(
        shadowElevation = if (scrolledToTop) 0.dp else 2.dp,
        modifier = Modifier.height(topBarSize + insetsPadding).padding(top = insetsPadding),
      ) {
        HorizontalList(
          items = state.tabs,
          modifier = Modifier.fillMaxSize(),
          itemContent = { _, tab ->
            SelectableCircleAvatar(
              modifier = Modifier.size(60.dp),
              imageUrl = tab.imageUrl,
              selected = tab.source == state.selectedTab,
              onClick = { eventSink.invoke(HomeScreenContract.Event.TabClicked(tab.source)) }
            )
          },
        )
      }
    }
  ) { innerPadding ->
    LazyColumn(
      state = scrollState,
      modifier = Modifier.padding(innerPadding),
    ) {
      items(
        items = state.articles,
        key = { article -> article.id },
        itemContent = { article ->
          ArticleCell(
            topText = article.title,
            bottomText = article.description.orEmpty(),
            tag = article.tags.firstOrNull().orEmpty(),
            onClick = {
              eventSink.invoke(HomeScreenContract.Event.ArticleClicked(article))
            },
          )
          HorizontalDivider(
            thickness = 0.2.dp,
            color = MaterialTheme.colorScheme.outlineVariant,
            modifier = Modifier.padding(top = 16.dp),
          )
        }
      )
    }
  }
}

@Inject
class Factory : Ui.Factory {
  override fun create(screen: Screen, context: CircuitContext): Ui<*>? {
    return when (screen) {
      is HomeScreenContract.HomeScreen -> {
        ui<HomeScreenContract.State> { state, modifier ->
          HomeScreen(state, modifier)
        }
      }

      else -> null
    }
  }
}
