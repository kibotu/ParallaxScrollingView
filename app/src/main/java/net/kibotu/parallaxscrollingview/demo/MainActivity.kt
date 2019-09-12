package net.kibotu.parallaxscrollingview.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exozet.android.core.misc.createRandomImageUrl
import kotlinx.android.synthetic.main.activity_main.*
import net.kibotu.android.recyclerviewpresenter.PresenterAdapter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.parallaxscrollingview.OffsetOnPageScrollListener
import net.kibotu.parallaxscrollingview.ParallaxScrollingViewOnPageScrollListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PresenterAdapter()
        adapter.registerPresenter(ImagePresenter())
        viewPager.adapter = adapter

        val items = (0 until 10).map {
            PresenterModel(createRandomImageUrl(), R.layout.item_image, uuid = it.toString())
        }

        adapter.submitList(items)

        viewPager.registerOnPageChangeCallback(ParallaxScrollingViewOnPageScrollListener(listOf(wave1, wave2, wave3, wave4, wave5, wave6), 2f))

        viewPager.registerOnPageChangeCallback(OffsetOnPageScrollListener(this, root, items.indices.map { backgrounds[it] }, true))
    }
}