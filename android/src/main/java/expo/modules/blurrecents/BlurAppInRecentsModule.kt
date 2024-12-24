package expo.modules.blurrecents

import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

class BlurAppInRecentsModule : Module() {
  override fun definition() = ModuleDefinition {
    Name("BlurAppInRecents")

    Function("enable", fun() {
      ActivityLifecycleListener.isBlurringEnabled = true
    })

    Function("disable", fun() {
      ActivityLifecycleListener.isBlurringEnabled = false
    })

    Function("isBlurringEnabled") {
      return@Function ActivityLifecycleListener.isBlurringEnabled
    }
  }
}
