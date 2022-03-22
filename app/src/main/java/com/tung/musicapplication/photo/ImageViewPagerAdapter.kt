package com.tung.musicapplication.photo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.viewpager.widget.PagerAdapter
import com.tung.musicapplication.R
import com.tung.musicapplication.domain.EnPhoto
import com.tung.musicapplication.extension.loadUrl
import java.util.*

class ImageViewPagerAdapter(
    private val photos: List<EnPhoto>
) : PagerAdapter() {

    override fun getCount(): Int {
        return photos.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView: View =
            LayoutInflater.from(container.context).inflate(R.layout.item_photos, container, false)

        val imageView: ImageView = itemView.findViewById<View>(R.id.iv_image) as ImageView

        imageView.loadUrl(photos[position].url)

        Objects.requireNonNull(container).addView(itemView)

        return itemView
    }

    fun getPhotoItem(position: Int): EnPhoto? {
        return if (photos.isEmpty().not() && position < photos.size) {
            photos[position]
        } else {
            null
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}