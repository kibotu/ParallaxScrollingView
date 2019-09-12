package net.kibotu.parallaxscrollingview

import android.app.Activity
import android.graphics.drawable.GradientDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.vectordrawable.graphics.drawable.ArgbEvaluator
import androidx.viewpager2.widget.ViewPager2

class OffsetOnPageScrollListener(private val activity: Activity, private val view: View, private val colors: List<BackgroundGradientProperty>, private val changeStatusBar: Boolean = false) : ViewPager2.OnPageChangeCallback() {

    private val argbEvaluator by lazy { ArgbEvaluator() }

    private val backgroundGradientDrawable: GradientDrawable = GradientDrawable()

    private val buffer = intArrayOf(colors[0].topGradient.resColor, colors[0].bottomGradient.resColor)

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)

        val left = colors[position.coerceIn(0, colors.lastIndex)]
        val right = colors[position.plus(1).coerceIn(0, colors.lastIndex)]

        buffer[0] = argbEvaluator.evaluate(positionOffset, left.topGradient.resColor, right.topGradient.resColor) as Int
        buffer[1] = argbEvaluator.evaluate(positionOffset, left.bottomGradient.resColor, right.bottomGradient.resColor) as Int

        backgroundGradientDrawable.colors = buffer
        if (changeStatusBar)
            activity.changeStatusBarColor(buffer[0])

        ViewCompat.setBackground(view, backgroundGradientDrawable)
    }

    private val Int.resColor: Int
        get() = ContextCompat.getColor(activity, this)
}