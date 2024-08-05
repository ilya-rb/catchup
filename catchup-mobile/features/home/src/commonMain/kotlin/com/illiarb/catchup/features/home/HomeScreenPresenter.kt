package com.illiarb.catchup.features.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.illiarb.catchup.core.arch.CoreNavigator
import com.illiarb.catchup.core.coroutines.AppDispatchers
import com.illiarb.catchup.service.domain.Article
import com.illiarb.catchup.service.domain.NewsSource
import com.illiarb.catchup.service.network.CatchupService
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen
import kotlinx.coroutines.flow.first
import me.tatarka.inject.annotations.Inject

@Inject
class HomeScreenPresenter(
  private val navigator: Navigator,
  private val catchupService: CatchupService,
  private val appDispatchers: AppDispatchers,
) : Presenter<HomeScreenContract.State> {

  @Composable
  override fun present(): HomeScreenContract.State {
    var selectedTab by remember {
      mutableStateOf<NewsSource?>(null)
    }

    var sources by remember {
      mutableStateOf<Set<NewsSource>>(emptySet())
    }

    if (selectedTab == null) {
      selectedTab = sources.firstOrNull()
    }

    val tabs = sources.map { source ->
      HomeScreenContract.Tab(
        id = source.key,
        source = source,
        imageUrl = when (source) {
          NewsSource.IrishTimes -> "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQEey2YHaKAWE4PJ14yz8Z8BmqauVdjMExM5A&s"
          NewsSource.HackerNews -> "https://static-00.iconduck.com/assets.00/hackernews-icon-2048x2048-h8uaqp6j.png"
          NewsSource.Dou -> "https://images.squarespace-cdn.com/content/v1/57b5b62a725e25b7ca9bd8da/1586188273506-R95E0GGT43UBSS28WM2U/6.png"
          else -> ""
        },
      )
    }

    var articles by remember {
      mutableStateOf<List<Article>>(emptyList())
    }

    fun eventSink(event: HomeScreenContract.Event) {
      when (event) {
        is HomeScreenContract.Event.ButtonClick -> Unit
        is HomeScreenContract.Event.TabClicked -> selectedTab = event.source
        is HomeScreenContract.Event.ArticleClicked -> openArticleInBrowser(event.item.link)
      }
    }

    LaunchedEffect(Unit) {
      val availableSources = catchupService.collectAvailableSources().first()
      sources = availableSources
      selectedTab = sources.first()
    }

    LaunchedEffect(selectedTab?.key) {
      selectedTab?.let {
        articles = catchupService.collectLatestNewsFrom(it).first()
      }
    }

    return HomeScreenContract.State(
      articles = articles,
      tabs = tabs,
      selectedTab = selectedTab,
      eventSink = ::eventSink,
    )
  }

  private fun openArticleInBrowser(url: String) {
    if (navigator is CoreNavigator) {
      navigator.openUrl(url)
    }
  }

  @Inject
  class Factory(
    private val catchupService: CatchupService,
    private val appDispatchers: AppDispatchers,
  ) : Presenter.Factory {
    override fun create(
      screen: Screen,
      navigator: Navigator,
      context: CircuitContext
    ): Presenter<*>? {
      return when (screen) {
        is HomeScreenContract.HomeScreen -> HomeScreenPresenter(
          navigator,
          catchupService,
          appDispatchers
        )

        else -> null
      }
    }
  }
}
