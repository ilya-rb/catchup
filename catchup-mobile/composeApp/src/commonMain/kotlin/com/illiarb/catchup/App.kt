package com.illiarb.catchup

import androidx.compose.runtime.Composable
import com.illiarb.catchup.features.home.HomePresenter
import com.illiarb.catchup.features.home.HomeScreen
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import com.illiarb.catchup.uikit.theme.UiKitTheme

@Composable
fun App() {
  val circuit = Circuit.Builder()
    .addPresenterFactory(HomePresenter.Factory())
    .addUi<HomeScreen, HomeScreen.State> { state, modifier -> HomeScreen(state, modifier) }
    .build()

  val backStack = rememberSaveableBackStack(root = HomeScreen)
  val navigator = rememberCircuitNavigator(backStack) {}

  CircuitCompositionLocals(circuit) {
    UiKitTheme(useDynamicColors = false) {
      NavigableCircuitContent(
        navigator = navigator,
        backStack = backStack,
      )
    }
  }
}
