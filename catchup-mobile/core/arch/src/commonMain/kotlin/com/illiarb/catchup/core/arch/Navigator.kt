package com.illiarb.catchup.core.arch

import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Navigator as CircuitNavigator

class CoreNavigator(
  private val openLink: (String) -> Unit,
  private val navigator: CircuitNavigator,
) : Navigator by navigator {

  fun openUrl(url: String) {
    openLink.invoke(url)
  }
}
