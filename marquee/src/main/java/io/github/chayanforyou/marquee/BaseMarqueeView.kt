package io.github.chayanforyou.marquee

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.HorizontalScrollView

class BaseMarqueeView(context: Context?, attrs: AttributeSet?) : HorizontalScrollView(context, attrs) {

    private lateinit var child: View
    private var onLayoutExecuted = false
    private var isLooping = false
    private var isRtl = false
    private var loops = 2
    private var startWaitTicks = 50
    private var endWaitTicks = 30
    private val handler = Handler(Looper.getMainLooper())

    init {
        isSmoothScrollingEnabled = true
        updateRtl()
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        if (!onLayoutExecuted) {
            fullScroll(if (isRtl) FOCUS_RIGHT else FOCUS_LEFT)
            child = getChildAt(0)
            if (!canScroll()) {
                val params = child.layoutParams as LayoutParams
                params.gravity = if (isRtl) Gravity.END else Gravity.START
                child.setLayoutParams(params)
            } else {
                handler.postDelayed(runnable, 500)
            }
            onLayoutExecuted = true
        }
    }

    private fun canScroll(): Boolean {
        val childWidth = child.width
        return width < childWidth + getPaddingRight() + getPaddingLeft()
    }

    fun isLooping(): Boolean {
        return isLooping
    }

    fun setLooping(looping: Boolean) {
        isLooping = looping
        onLayoutExecuted = false
    }

    fun getLoops(): Int {
        return loops
    }

    fun setLoops(loops: Int) {
        this.loops = loops
        onLayoutExecuted = false
    }

    fun getStartWaitTicks(): Int {
        return startWaitTicks
    }

    fun setStartWaitTicks(ticks: Int) {
        this.startWaitTicks = ticks
    }

    fun getEndWaitTicks(): Int {
        return endWaitTicks
    }

    fun setEndWaitTicks(ticks: Int) {
        this.endWaitTicks = ticks
    }

    fun updateRtl() {
        isRtl = Checks.isRtl(context)
    }

    private val littleScroll = Runnable { smoothScrollBy(if (isRtl) -2 else 2, 0) }

    private val fullScroll = Runnable { fullScroll(if (isRtl) FOCUS_RIGHT else FOCUS_LEFT) }

    private val runnable = object : Runnable {
        var wait = 0
        var repeat = 0
        var initialWait = 0

        override fun run() {
            if (repeat >= loops && !isLooping) {
                fullScroll(if (isRtl) FOCUS_RIGHT else FOCUS_LEFT)
                return
            }

            val scrollX = scrollX
            val maxScrollX = child.width - width
            val isEndReached = if (isRtl) scrollX <= 0 else scrollX >= maxScrollX

            if (!isEndReached) {
                if (initialWait > startWaitTicks) {
                    post(littleScroll)
                }
                initialWait++
            } else {
                wait++
                if (wait > endWaitTicks) {
                    post(fullScroll)
                    wait = 0
                    if (!isLooping) {
                        repeat++
                    }
                    initialWait = 0
                }
            }
            handler.postDelayed(this, 30)
        }
    }
}
