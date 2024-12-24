package expo.modules.blurrecents

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import expo.modules.core.interfaces.ReactActivityLifecycleListener

class ActivityLifecycleListener : ReactActivityLifecycleListener {
  
  private fun handleBlurringChange(activity: Activity?, isBlurred: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      activity?.runOnUiThread {
        activity.setRecentsScreenshotEnabled(!isBlurred)
      }
    }
  }

  override fun onCreate(activity: Activity?, savedInstanceState: Bundle?) {
    super.onCreate(activity, savedInstanceState)
    onBlurringEnabledChanged = { enabled ->
      handleBlurringChange(activity, enabled)
    }
  }

  override fun onResume(activity: Activity?) {
    super.onResume(activity)
    if (isBlurringEnabled) {
      activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
  }

  override fun onPause(activity: Activity?) {
    super.onPause(activity)
    if (isBlurringEnabled) {
      activity?.window?.setFlags(
        WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE
      )
    }
  }

  override fun onDestroy(activity: Activity?) {
    super.onDestroy(activity)
    onBlurringEnabledChanged = null
  }

  companion object {
    private var _isBlurringEnabled: Boolean = false
    var isBlurringEnabled: Boolean
      get() = _isBlurringEnabled
      set(value) {
        _isBlurringEnabled = value
        onBlurringEnabledChanged?.invoke(value)
      }

    private var onBlurringEnabledChanged: ((Boolean) -> Unit)? = null
  }
}