package com.livecoding.android.app.ui.activity.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livecoding.android.app.R
import com.livecoding.android.app.databinding.ActivityHomeBinding
import com.livecoding.android.app.ui.activity.home.fragment.HomeFragment

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)

        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, HomeFragment.newInstance())
                .commitNow()
        }
    }
}