package io.github.chayanforyou.marquee

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class MarqueeTextView : FrameLayout {

    private lateinit var baseMarqueeView: BaseMarqueeView
    private lateinit var tvMain: TextView
    private lateinit var startFade: ImageView
    private lateinit var endFade: ImageView

    constructor(context: Context) : super(context) {
        initAttrs(null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttrs(attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttrs(attrs)
    }

    private fun initAttrs(attrs: AttributeSet?) {
        try {
            inflate(context, R.layout.view_marquee, this)
            baseMarqueeView = findViewById(R.id.mv_marquee)
            tvMain = findViewById(R.id.tv_main)
            startFade = findViewById(R.id.iv_start_fade)
            endFade = findViewById(R.id.iv_end_fade)

            if (attrs != null) {
                val a = context.obtainStyledAttributes(attrs, R.styleable.MarqueeTextView)
                tvMain.text = a.getString(R.styleable.MarqueeTextView_marqueeText)
                tvMain.setTextColor(a.getColor(R.styleable.MarqueeTextView_marqueeTextColor, ContextCompat.getColor(context, R.color.black)))
                tvMain.setTextSize(TypedValue.COMPLEX_UNIT_PX, a.getDimension(R.styleable.MarqueeTextView_marqueeTextSize, resources.getDimension(R.dimen.default_text_size)))
                baseMarqueeView.setLooping(a.getBoolean(R.styleable.MarqueeTextView_marqueeIsLooping, resources.getBoolean(R.bool.default_looping)))
                baseMarqueeView.setLoops(a.getInteger(R.styleable.MarqueeTextView_marqueeLoops, resources.getInteger(R.integer.default_loops)))
                baseMarqueeView.setStartWaitTicks(a.getInteger(R.styleable.MarqueeTextView_marqueeStartWaitTicks, resources.getInteger(R.integer.default_startWaitTicks)))
                baseMarqueeView.setEndWaitTicks(a.getInteger(R.styleable.MarqueeTextView_marqueeEndWaitTicks, resources.getInteger(R.integer.default_endWaitTicks)))

                val fadeColor = a.getColor(R.styleable.MarqueeTextView_marqueeFadeToColor, ContextCompat.getColor(context, R.color.white))
                startFade.setColorFilter(fadeColor)
                endFade.setColorFilter(fadeColor)
                startFade.tag = fadeColor

                val fontResId = a.getResourceId(R.styleable.MarqueeTextView_marqueeFontFamily, 0)
                if (fontResId != 0) {
                    tvMain.typeface = ResourcesCompat.getFont(context, fontResId)
                }
                a.recycle()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    var text: String?
        get() = tvMain.getText().toString()
        set(text) {
            tvMain.text = text
        }

    var textColor: Int
        get() = tvMain.currentTextColor
        set(color) {
            tvMain.setTextColor(color)
        }

    var textSize: Float
        get() = tvMain.textSize
        set(size) {
            tvMain.textSize = size
        }

    var fadeToColor: Int
        get() = startFade.tag as Int
        set(color) {
            startFade.setColorFilter(color)
            endFade.setColorFilter(color)
            startFade.tag = color
        }

    var looping: Boolean
        get() = baseMarqueeView.isLooping()
        set(looping) {
            baseMarqueeView.setLooping(looping)
        }

    var loops: Int
        get() = baseMarqueeView.getLoops()
        set(loops) {
            baseMarqueeView.setLoops(loops)
        }

    var startWaitTicks: Int
        get() = baseMarqueeView.getStartWaitTicks()
        set(ticks) {
            baseMarqueeView.setStartWaitTicks(ticks)
        }

    var endWaitTicks: Int
        get() = baseMarqueeView.getEndWaitTicks()
        set(ticks) {
            baseMarqueeView.setEndWaitTicks(ticks)
        }

    fun updateRtl() {
        baseMarqueeView.updateRtl()
    }
}
