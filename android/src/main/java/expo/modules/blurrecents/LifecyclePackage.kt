package expo.modules.blurrecents

import android.content.Context
import expo.modules.core.interfaces.Package
import expo.modules.core.interfaces.ReactActivityLifecycleListener

class LifecyclePackage : Package {
  override fun createReactActivityLifecycleListeners(activityContext: Context): List<ReactActivityLifecycleListener> {
    return listOf(ActivityLifecycleListener())
  }
}