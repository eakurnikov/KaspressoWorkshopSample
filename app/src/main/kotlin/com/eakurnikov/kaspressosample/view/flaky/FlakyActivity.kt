package com.eakurnikov.kaspressosample.view.flaky

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.simple.SecondActivity
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_flaky.*
import java.util.concurrent.TimeUnit

/**
 * Created by eakurnikov on 2019-12-15
 */
class FlakyActivity : DaggerAppCompatActivity() {

    companion object {
        private val VISIBILITY_DELAY = TimeUnit.SECONDS.toMillis(1)
        private val TEXT_DELAY = TimeUnit.SECONDS.toMillis(3)

        fun start(context: Context): Unit =
            context.startActivity(Intent(context, FlakyActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flaky)
        AndroidInjection.inject(this)
        initViews()
    }

    private fun initViews() {
        btn_flaky_6.setOnClickListener {
            SecondActivity.start(this, getString(R.string.success))
        }

        Handler(mainLooper)
            .apply {
                postDelayed(
                    { tv_flaky_2.visibility = View.VISIBLE },
                    VISIBILITY_DELAY
                )
            }
            .apply {
                postDelayed(
                    { tv_flaky_2.text = getString(R.string.flaky_textview_text_end) },
                    VISIBILITY_DELAY + TEXT_DELAY
                )
            }
            .apply {
                postDelayed(
                    { btn_flaky_6.visibility = View.VISIBLE },
                    VISIBILITY_DELAY * 2 + TEXT_DELAY
                )
            }
            .apply {
                postDelayed(
                    { btn_flaky_6.text = getString(R.string.flaky_btn_text_end) },
                    VISIBILITY_DELAY * 2 + TEXT_DELAY * 2
                )
            }
    }
}