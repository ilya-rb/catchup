package com.illiarb.catchup.mobile

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.rememberCircuitNavigator
import uikit.theme.UiKitTheme

class MainActivity : ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    enableEdgeToEdge(SystemBarStyle.auto(Color.TRANSPARENT, Color.TRANSPARENT))

    super.onCreate(savedInstanceState)

    val circuit = Circuit.Builder()
      .addPresenterFactory(HomePresenter.Factory())
      .addPresenterFactory(SettingsPresenter.Factory())
      .addUi<HomeScreen, HomeScreen.State> { state, modifier -> HomeScreen(state, modifier) }
      .addUi<SettingsScreen, SettingsScreen.State> { state, modifier -> SettingsScreen(state, modifier) }
      .build()

    setContent {
      val backStack = rememberSaveableBackStack(root = HomeScreen)
      val navigator = rememberCircuitNavigator(backStack)

      UiKitTheme(useDynamicColors = false) {
        CircuitCompositionLocals(circuit) {
          NavigableCircuitContent(
            navigator = navigator,
            backStack = backStack,
          )
        }
      }
    }
  }
}