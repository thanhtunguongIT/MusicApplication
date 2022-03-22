package com.tung.musicapplication

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.tung.musicapplication.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (supportFragmentManager.findFragmentById(R.id.nav_host_main_activity) as NavHostFragment).apply {
            navController.setGraph(R.navigation.nav_music_graph)
        }
    }
}