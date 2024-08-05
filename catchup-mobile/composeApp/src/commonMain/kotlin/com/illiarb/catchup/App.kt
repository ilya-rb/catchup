package com.illiarb.catchup

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import com.illiarb.catchup.di.UiComponent
import com.illiarb.catchup.features.home.HomeScreenContract
import com.illiarb.catchup.uikit.core.theme.UiKitTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.Navigator

@Composable
fun App(
  uiComponent: UiComponent,
  imageLoader: ImageLoader,
  openLink: (String) -> Unit,
) {
  val backStack = rememberSaveableBackStack(root = HomeScreenContract.HomeScreen)

  val navigator = remember {
    com.illiarb.catchup.core.arch.CoreNavigator(
      navigator = Navigator(backStack) {},
      openLink = { url ->
        openLink.invoke(url)
      }
    )
  }

  setSingletonImageLoaderFactory { imageLoader }

  CircuitCompositionLocals(uiComponent.circuit) {
    UiKitTheme(useDynamicColors = false) {
      NavigableCircuitContent(
        navigator = navigator,
        backStack = backStack,
      )
    }
  }
}
