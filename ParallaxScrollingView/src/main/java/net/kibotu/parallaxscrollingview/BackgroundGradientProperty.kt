package net.kibotu.parallaxscrollingview

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes

data class BackgroundGradientProperty(

    @ColorRes
    var topGradient: Int = 0,

    @ColorRes
    var bottomGradient: Int = 0,

    @DrawableRes
    var backgroundDrawable: Int = 0
) {

    fun setTopGradient(@ColorRes topGradient: Int): BackgroundGradientProperty {
        this.topGradient = topGradient
        return this
    }

    fun setBottomGradient(@ColorRes bottomGradient: Int): BackgroundGradientProperty {
        this.bottomGradient = bottomGradient
        return this
    }

    fun setBackgroundDrawable(@DrawableRes backgroundDrawable: Int): BackgroundGradientProperty {
        this.backgroundDrawable = backgroundDrawable
        return this
    }
}