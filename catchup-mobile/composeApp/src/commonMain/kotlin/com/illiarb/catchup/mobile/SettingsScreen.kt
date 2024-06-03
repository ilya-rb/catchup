package com.illiarb.catchup.mobile

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
internal object SettingsScreen : Screen, CommonParcelable {

  data class State(val eventSink: (Event) -> Unit) : CircuitUiState

  sealed interface Event : CircuitUiEvent {
    data object BackClick : Event
  }
}

internal class SettingsPresenter(
  private val screen: SettingsScreen,
  private val navigator: Navigator,
) : Presenter<SettingsScreen.State> {

  @Composable
  override fun present(): SettingsScreen.State {
    return SettingsScreen.State { event ->
      when (event) {
        SettingsScreen.Event.BackClick -> navigator.pop()
      }
    }
  }

  class Factory : Presenter.Factory {
    override fun create(screen: Screen, navigator: Navigator, context: CircuitContext): Presenter<*>? {
      return when (screen) {
        is SettingsScreen -> SettingsPresenter(screen, navigator)
        else -> null
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SettingsScreen(state: SettingsScreen.State, modifier: Modifier = Modifier) {
  Scaffold(
    modifier = modifier,
    topBar = {
      TopAppBar(
        title = { Text(text = "Settings") },
        navigationIcon = {
          IconButton(onClick = { state.eventSink(SettingsScreen.Event.BackClick) }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
          }
        }
      )
    },
  ) { innerPadding ->
    Text(text = "Settings content", modifier = Modifier.padding(innerPadding))
  }
}