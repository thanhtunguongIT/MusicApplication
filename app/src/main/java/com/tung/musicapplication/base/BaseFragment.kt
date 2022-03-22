package com.tung.musicapplication.base

import android.content.Context
import androidx.fragment.app.Fragment
import com.tung.musicapplication.MusicApplication
import com.tung.musicapplication.di.AppComponent
import com.tung.musicapplication.di.viewmodel.ViewModelFactory
import javax.inject.Inject

open class BaseFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    open fun doInjection(appComponent: AppComponent) {

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        doInjection((requireActivity().application as MusicApplication).appComponent)
    }
}