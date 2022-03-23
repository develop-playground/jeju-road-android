package github.dev_playground.jeju_road.presentation.util

import android.app.Activity
import com.google.android.material.transition.platform.MaterialContainerTransform
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback

/**
 * Must be used before super.onCreate(savedInstanceState)
 */
fun Activity.addEnterMaterialSharedElementCallback() {
    setEnterSharedElementCallback(MaterialContainerTransformSharedElementCallback())
}

/**
 * Must be used before super.onCreate(savedInstanceState)
 */
fun Activity.addExitMaterialSharedElementCallback() {
    setExitSharedElementCallback(
        MaterialContainerTransformSharedElementCallback()
    )
    window.sharedElementsUseOverlay = false
}

fun Activity.addMaterialSharedElementEnterTransition(
    action: MaterialContainerTransform.() -> Unit
) {
    window.sharedElementEnterTransition = MaterialContainerTransform().apply(action)
}

fun Activity.addMaterialSharedElementReEnterTransition(
    action: MaterialContainerTransform.() -> Unit
) {
    window.sharedElementReenterTransition = MaterialContainerTransform().apply(action)
}

fun Activity.addMaterialSharedElementReturnTransition(
    action: MaterialContainerTransform.() -> Unit
) {
    window.sharedElementReturnTransition = MaterialContainerTransform().apply(action)
}

fun Activity.addMaterialSharedElementExitTransition(
    action: MaterialContainerTransform.() -> Unit
) {
    window.sharedElementExitTransition = MaterialContainerTransform().apply(action)
}