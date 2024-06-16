package com.illiarb.catchup.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.illiarb.catchup.core.arch.CommonParcelable
import com.illiarb.catchup.core.arch.CommonParcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import com.illiarb.catchup.service.domain.Article
import com.illiarb.catchup.uikit.components.*

@CommonParcelize
object HomeScreen : Screen, CommonParcelable {

  data class State(
    val articles: List<Article>,
    val tabs: List<UiKitTab>,
    val eventSink: (Event) -> Unit,
  ) : CircuitUiState

  sealed interface Event : CircuitUiEvent {
    data object ButtonClick : Event
    data class TabClicked(val id: String) : Event
    data class ArticleClicked(val item: Article) : Event
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(state: HomeScreen.State, modifier: Modifier = Modifier) {
  val eventSink = state.eventSink

  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text(text = "Test") }) },
  ) { innerPadding ->
    Column(modifier = Modifier.padding(innerPadding)) {
      UiKitTabs(
        items = state.tabs,
        itemContent = { index, tab ->
          ImageCell(tab.text, tab.url, selected = index == 0) {
            eventSink.invoke(HomeScreen.Event.TabClicked(tab.id))
          }
        }
      )
      LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
        items(
          items = state.articles,
          key = { article -> article.id },
          itemContent = { article ->
            ArticleCell(
              topText = article.title,
              bottomText = article.description.orEmpty(),
              image = UiKitImageModel.Url(url = "https://picsum.photos/seed/picsum/200/300"),
            ) {
              eventSink.invoke(HomeScreen.Event.ArticleClicked(article))
            }
          }
        )
      }
    }
  }
}
