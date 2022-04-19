package github.dev_playground.jeju_road.presentation.ui.component

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.NestedScrollingChild3
import androidx.core.view.NestedScrollingChildHelper

class CoordinatorCustomView
@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout(context, attrs), NestedScrollingChild3 {
    private val helper = NestedScrollingChildHelper(this)

    init {
        isNestedScrollingEnabled = true
    }

    override fun isNestedScrollingEnabled(): Boolean = helper.isNestedScrollingEnabled

    override fun setNestedScrollingEnabled(enabled: Boolean) {
        helper.isNestedScrollingEnabled = enabled
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        println("onInterceptTouchEvent: ${ev.toString()} ")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray) {
        super.onNestedPreScroll(target, dx, dy, consumed)
        println("onNestedPreScroll")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        super.onNestedPreScroll(target, dx, dy, consumed, type)
        println("onNestedPreScroll: Coordinator")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int,
        consumed: IntArray
    ) {
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null, type)
        super.onNestedScroll(
            target,
            dxConsumed,
            dyConsumed,
            dxUnconsumed,
            dyUnconsumed,
            type,
            consumed
        )
        println("onNestedScroll1")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type)
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null, type)
        println("onNestedScroll2")
    }

    override fun onNestedScroll(
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int
    ) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed)
        dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, null)
        println("onNestedScroll3")
    }

    override fun onStartNestedScroll(child: View, target: View, nestedScrollAxes: Int): Boolean {
        val superResult = super.onStartNestedScroll(child, target, nestedScrollAxes)
        return startNestedScroll(nestedScrollAxes) || superResult
    }

    override fun onStartNestedScroll(child: View, target: View, axes: Int, type: Int): Boolean {
        val superResult = super.onStartNestedScroll(child, target, axes, type)
        return startNestedScroll(axes, type) || superResult
    }

    override fun startNestedScroll(axes: Int): Boolean = helper.startNestedScroll(axes)

    override fun startNestedScroll(axes: Int, type: Int): Boolean = helper.startNestedScroll(axes, type)

    override fun stopNestedScroll(type: Int) {
        println("stopNestedScroll")
        TODO("Not yet implemented")
    }

    override fun hasNestedScrollingParent(type: Int): Boolean = helper.hasNestedScrollingParent(type)

    override fun hasNestedScrollingParent(): Boolean = helper.hasNestedScrollingParent()

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int,
        consumed: IntArray
    ) {
        println("dispatchNestedScroll")
        TODO("Not yet implemented")
    }

    override fun dispatchNestedScroll(
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        println("dispatchNestedScroll1: $dxConsumed $dxUnconsumed $dyConsumed $dyUnconsumed")
        TODO("Not yet implemented")
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?,
        type: Int
    ): Boolean {
        println("dispatchNestedPreScroll")
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type)
    }

    override fun dispatchNestedPreScroll(
        dx: Int,
        dy: Int,
        consumed: IntArray?,
        offsetInWindow: IntArray?
    ): Boolean {
        println("dispatchNestedPreScroll")
        return helper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow)
    }
}