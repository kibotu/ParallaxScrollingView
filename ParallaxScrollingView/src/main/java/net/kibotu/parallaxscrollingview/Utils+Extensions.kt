package net.kibotu.parallaxscrollingview

import android.content.res.Resources
import android.util.TypedValue

/**
 * Converts dp to pixel.
 */
internal val Float.dp: Float get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)
