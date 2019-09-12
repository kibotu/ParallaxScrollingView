package net.kibotu.parallaxscrollingview

import androidx.viewpager2.widget.ViewPager2

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

class ParallaxOnPageScrollListener(val wave: ParallaxScrollingView, private val offsetSpeed: Float = 2f) : ViewPager2.OnPageChangeCallback() {

    private val offsets: HashMap<Int, Int> = HashMap()

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        offsets[position] = positionOffsetPixels
        val totalOffset = offsets.values.sumBy { it }
        wave.setOffset(-(totalOffset * offsetSpeed), 0f)
    }
}