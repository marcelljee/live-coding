package com.livecoding.android.app.ui.activity.moviepaginated

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livecoding.android.app.R
import com.livecoding.android.app.databinding.MoviePaginatedActivityBinding
import com.livecoding.android.app.ui.activity.moviepaginated.fragment.MoviePaginatedFragment

class MoviePaginatedActivity : AppCompatActivity() {
    private lateinit var binding: MoviePaginatedActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = MoviePaginatedActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviePaginatedFragment.newInstance())
                .commitNow()
        }
    }

    companion object {
        fun startActivity(activity: Activity?) {
            activity?.let {
                it.startActivity(createIntent(it))
            }
        }

        private fun createIntent(context: Context): Intent {
            return Intent(context, MoviePaginatedActivity::class.java)
        }
    }
}