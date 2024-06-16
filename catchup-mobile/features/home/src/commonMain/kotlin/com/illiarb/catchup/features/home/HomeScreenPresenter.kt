package com.illiarb.catchup.features.home

import androidx.compose.runtime.Composable
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import com.illiarb.catchup.service.domain.Article
import com.illiarb.catchup.service.domain.NewsSource
import com.illiarb.catchup.uikit.components.UiKitTab

class HomePresenter(
  private val navigator: Navigator,
) : Presenter<HomeScreen.State> {

  private val stubArticles = (0 until 5).map { index ->
    Article(
      id = "id_$index",
      title = "Article $index",
      description = "Article $index",
      link = "https://google.com",
      source = NewsSource.IrishTimes,
    )
  }

  private val stubTabs = listOf(
    UiKitTab(id = "id_1", text = "Item 1", url = "https://picsum.photos/seed/picsum/200/300"),
    UiKitTab(id = "id_2", text = "Item 2", url = "https://picsum.photos/seed/picsum/200/300"),
    UiKitTab(id = "id_3", text = "Item 3", url = "https://picsum.photos/seed/picsum/200/300"),
  )

  @Composable
  override fun present(): HomeScreen.State {
    return HomeScreen.State(stubArticles, stubTabs) { event ->
      when (event) {
        is HomeScreen.Event.ButtonClick -> Unit
        is HomeScreen.Event.TabClicked -> Unit
        is HomeScreen.Event.ArticleClicked -> openArticleInBrowser(event.item.link)
      }
    }
  }

  private fun openArticleInBrowser(url: String) {

  }

  class Factory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
      return when (screen) {
        is HomeScreen -> HomePresenter(navigator)
        else -> null
      }
    }
  }
}
