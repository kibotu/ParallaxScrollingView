package net.kibotu.parallaxscrollingview

import android.annotation.TargetApi
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.graphics.Paint.Style
import android.graphics.Shader.TileMode
import android.graphics.drawable.shapes.RectShape
import android.os.Build.VERSION_CODES
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceHolder.Callback
import android.view.SurfaceView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import java.util.*

/**
 * Created by [Jan Rabe](https://about.me/janrabe).
 */

class ParallaxScrollingView : SurfaceView, Callback {

    constructor(context: Context?) : super(context) {
        init(null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs)
    }

    @TargetApi(VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs)
    }

    private val uuid by lazy { UUID.randomUUID().toString() }

    private val textureClipBounds by lazy { Rect() }

    private var maxBitmapHeight = 0

    private var shader: BitmapShader? = null

    private val paint by lazy {
        Paint().apply {
            isDither = true
            isAntiAlias = true
            isFilterBitmap = true
            style = Style.FILL
        }
    }

    private var shape = RectShape()

    private var translateX = 0f
    private var translateY = 0f

    private val textureMatrix by lazy { Matrix() }

    private var image: Bitmap? = null

    private var drawableId = 0

    private fun init(attrs: AttributeSet?) {
        if (isInEditMode) return
        log { "[init] $uuid" }
        setWillNotDraw(false)
        holder.addCallback(this)
        holder.setFormat(PixelFormat.TRANSLUCENT)
        setZOrderOnTop(true)
        setBackgroundResource(android.R.color.transparent)

        val ta: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.ParallaxScrollingView, 0, 0)
        try {
            speed = ta.getDimension(R.styleable.ParallaxScrollingView_speed, 0f)
            drawableId = ta.getResourceId(R.styleable.ParallaxScrollingView_src, 0)
            create()
            freeResources()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            ta.recycle()
        }
    }

    private fun create() {
        log { "[create] $uuid" }

        image = AppCompatResources.getDrawable(context, drawableId)!!.toBitmap()

        maxBitmapHeight = image!!.height.coerceAtLeast(maxBitmapHeight)
        shader = BitmapShader(image!!, TileMode.REPEAT, TileMode.REPEAT)
        shape.resize(width.toFloat(), image!!.height.toFloat())
        paint.shader = shader
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        log { "[surfaceCreated] $uuid" }
        if (image == null) create()
        start()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        log { "[surfaceChanged] $uuid" }
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        log { "[surfaceDestroyed] $uuid" }
        stop()
        freeResources()
    }

    private fun freeResources() {

//        if (SDK_INT >= HONEYCOMB && SDK_INT < JELLY_BEAN_MR2)
//            return;

        if (image != null && !image!!.isRecycled) {
            log { "[freeResources] $uuid recycle bitmap" }
            try {
                image!!.recycle()
            } catch (e: Exception) {
                log { "[freeResources] failed ${e.localizedMessage}" }
            } finally {
                image = null
            }
        }
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        if (isInEditMode) return
        canvas.getClipBounds(textureClipBounds)
        textureMatrix.reset()
        textureMatrix.preTranslate(translateX + dX, translateY)
        shader!!.setLocalMatrix(textureMatrix)
        shape.draw(canvas, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        setMeasuredDimension(width, maxBitmapHeight)
    }

    fun setOffset(dX: Float, dY: Float) {
        translateX = dX
        translateY = dY
        postInvalidate()
    }

    private var isRunning = false

    fun start() {
        if (isRunning) return
        log { "[start] $uuid" }
        isRunning = true
        if (offsetScrolling == null) offsetScrolling = createOffsetScrolling()
        removeCallbacks(offsetScrolling)
        post(offsetScrolling)
    }

    fun stop() {
        log { "[stop] $uuid" }
        removeCallbacks(offsetScrolling)
        isRunning = false
    }

    private var speed = 0f
    private var dX = 0f

    private var offsetScrolling: Runnable? = null

    private fun createOffsetScrolling(): Runnable {
        return Runnable {
            dX += speed
            post(offsetScrolling)
            postInvalidate()
        }
    }

    /**
     * @param speed Dp per frame.
     */
    fun setSpeed(speed: Float) {
        this.speed = speed.dp
    }
}
