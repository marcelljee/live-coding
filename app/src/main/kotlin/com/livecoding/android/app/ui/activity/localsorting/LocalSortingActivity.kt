package com.livecoding.android.app.ui.activity.localsorting

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.livecoding.android.app.R
import com.livecoding.android.app.databinding.LocalSortingActivityBinding
import com.livecoding.android.app.ui.activity.localsorting.fragment.LocalSortingFragment

class LocalSortingActivity : AppCompatActivity() {

    private lateinit var binding: LocalSortingActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LocalSortingActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, LocalSortingFragment.newInstance())
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
            return Intent(context, LocalSortingActivity::class.java)
        }
    }
}