package expo.modules.blurrecents

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.view.WindowManager
import expo.modules.kotlin.modules.Module
import expo.modules.kotlin.modules.ModuleDefinition

private const val OVERLAY_TAG = 71297

class BlurAppInRecentsModule : Module() {
  private var isBlurringEnabled = false
  private var windowFocusListener: ViewTreeObserver.OnWindowFocusChangeListener? = null

  override fun definition() = ModuleDefinition {
    Name("BlurAppInRecents")

    Function("enable") {
      isBlurringEnabled = true
      appContext.currentActivity?.let { activity ->
        activity.runOnUiThread {
          applyBlurState(activity, obscured = true)
          ensureOverlayView(activity)
          registerWindowFocusListener(activity)
        }
      }
    }

    Function("disable") {
      isBlurringEnabled = false
      appContext.currentActivity?.let { activity ->
        activity.runOnUiThread {
          applyBlurState(activity, obscured = false)
          removeOverlayView(activity)
        }
      }
    }

    Function("isBlurringEnabled") {
      return@Function isBlurringEnabled
    }

    OnDestroy {
      appContext.currentActivity?.let { activity ->
        windowFocusListener?.let {
          activity.window?.decorView?.viewTreeObserver?.removeOnWindowFocusChangeListener(it)
        }
        removeOverlayView(activity)
      }
      windowFocusListener = null
    }
  }

  private fun applyBlurState(activity: Activity, obscured: Boolean) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
      activity.setRecentsScreenshotEnabled(!obscured)
    }
    if (obscured) {
      activity.window?.setFlags(
        WindowManager.LayoutParams.FLAG_SECURE,
        WindowManager.LayoutParams.FLAG_SECURE
      )
    } else {
      activity.window?.clearFlags(WindowManager.LayoutParams.FLAG_SECURE)
    }
    setOverlayVisible(activity, obscured)
  }

  private fun ensureOverlayView(activity: Activity) {
    val decor = activity.window?.decorView as? ViewGroup ?: return
    if (decor.findViewWithTag<View>(OVERLAY_TAG) != null) return

    val overlay = View(activity).apply {
      tag = OVERLAY_TAG
      setBackgroundColor(Color.WHITE)
      visibility = View.GONE
      layoutParams = ViewGroup.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT
      )
    }
    decor.addView(overlay)
  }

  private fun setOverlayVisible(activity: Activity, visible: Boolean) {
    val decor = activity.window?.decorView as? ViewGroup ?: return
    decor.findViewWithTag<View>(OVERLAY_TAG)?.visibility =
      if (visible) View.VISIBLE else View.GONE
  }

  private fun removeOverlayView(activity: Activity) {
    val decor = activity.window?.decorView as? ViewGroup ?: return
    decor.findViewWithTag<View>(OVERLAY_TAG)?.let { decor.removeView(it) }
  }

  private fun registerWindowFocusListener(activity: Activity) {
    if (windowFocusListener != null) return

    windowFocusListener = ViewTreeObserver.OnWindowFocusChangeListener { hasFocus ->
      if (isBlurringEnabled) {
        applyBlurState(activity, obscured = !hasFocus)
      }
    }
    activity.window?.decorView?.viewTreeObserver?.addOnWindowFocusChangeListener(windowFocusListener)
  }
}
