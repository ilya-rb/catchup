package com.illiarb.catchup.mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.illiarb.catchup.mobile.parcel.CommonParcelable
import com.illiarb.catchup.mobile.parcel.CommonParcelize
import com.slack.circuit.runtime.CircuitContext
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import com.slack.circuit.runtime.screen.Screen

@CommonParcelize
internal object HomeScreen : Screen, CommonParcelable {

  data class State(val eventSink: (Event) -> Unit) : CircuitUiState

  sealed interface Event : CircuitUiEvent {
    data object ButtonClick : Event
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(state: HomeScreen.State, modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = { TopAppBar(title = { Text(text = "Test") }) },
  ) { innerPadding ->
    Button(
      onClick = { state.eventSink(HomeScreen.Event.ButtonClick) },
      modifier = Modifier.padding(innerPadding)
    ) {
      Text(text = "Settings")
    }
  }
}

internal class HomePresenter(
  private val navigator: Navigator,
) : Presenter<HomeScreen.State> {

  @Composable
  override fun present(): HomeScreen.State {
    return HomeScreen.State { event ->
      when (event) {
        HomeScreen.Event.ButtonClick -> navigator.goTo(SettingsScreen)
      }
    }
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