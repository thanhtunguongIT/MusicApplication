package com.tung.musicapplication.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.viewpager.widget.ViewPager
import com.tung.musicapplication.R
import com.tung.musicapplication.databinding.FragmentPhotosBinding
import com.tung.musicapplication.domain.EnPhoto

class PhotosFragment(
    private val photos: List<EnPhoto>
) : DialogFragment() {

    private lateinit var binding: FragmentPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentTheme)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentPhotosBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {

            ivBack.setOnClickListener {
                dismiss()
            }

            val imageViewPagerAdapter = ImageViewPagerAdapter(photos)
            viewPager.adapter = imageViewPagerAdapter

            val onPageChangedListener = object : ViewPager.OnPageChangeListener{
                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    imageViewPagerAdapter.getPhotoItem(position)?.let { photo ->
                        tvTitle.text = photo.title
                        val desc = "${position + 1}/${photos.size}"
                        tvDescription.text = desc
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {

                }
            }
            viewPager.addOnPageChangeListener(onPageChangedListener)
            onPageChangedListener.onPageSelected(0)
        }
    }
}