package net.kibotu.parallaxscrollingview.demo

import com.ginsberg.cirkle.circular
import net.kibotu.parallaxscrollingview.BackgroundGradientProperty

val save = BackgroundGradientProperty()
    .setTopGradient(R.color.bg_save_top_gradient)
    .setBottomGradient(R.color.bg_save_bottom_gradient)
    .setBackgroundDrawable(R.drawable.bg_save_gradient)

val normal = BackgroundGradientProperty()
    .setTopGradient(R.color.bg_normal_top_gradient)
    .setBottomGradient(R.color.bg_normal_bottom_gradient)
    .setBackgroundDrawable(R.drawable.bg_normal_gradient)

val off = BackgroundGradientProperty()
    .setTopGradient(R.color.bg_off_top_gradient)
    .setBottomGradient(R.color.bg_off_bottom_gradient)
    .setBackgroundDrawable(R.drawable.bg_off_gradient)

val smart = BackgroundGradientProperty()
    .setTopGradient(R.color.bg_smart_top_gradient)
    .setBottomGradient(R.color.bg_smart_bottom_gradient)
    .setBackgroundDrawable(R.drawable.bg_smart_gradient)

val backgrounds = listOf(save, normal, off, smart).circular()