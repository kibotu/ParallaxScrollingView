package net.kibotu.parallaxscrollingview.demo

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import kotlinx.android.synthetic.main.item_image.view.*
import net.kibotu.android.recyclerviewpresenter.Adapter
import net.kibotu.android.recyclerviewpresenter.Presenter
import net.kibotu.android.recyclerviewpresenter.PresenterModel
import net.kibotu.logger.Logger.logv

class ImagePresenter : Presenter<String>() {

    override val layout = R.layout.item_image

    override fun bindViewHolder(viewHolder: RecyclerView.ViewHolder, item: PresenterModel<String>, position: Int, payloads: MutableList<Any>?, adapter: Adapter) {

        logv { "bindViewHolder $position ${item.model}" }

        with(viewHolder.itemView) {

            GlideApp.with(context.applicationContext)
                .load(item.model)
                .override(image.width, image.height)
                .transition(DrawableTransitionOptions.withCrossFade())
                .centerInside()
                .into(image)
                .clearOnDetach()
                .waitForLayout()
        }
    }
}