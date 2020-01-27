package com.eakurnikov.kaspressosample.view.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.eakurnikov.kaspressosample.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by eakurnikov on 2019-12-17
 */
class SecondActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TITLE_KEY: String = "TITLE_KEY"

        fun start(context: Context, title: String): Unit = context.startActivity(
            Intent(context, SecondActivity::class.java).putExtra(TITLE_KEY, title)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        AndroidInjection.inject(this)
        initViews()
    }

    private fun initViews() {
        intent?.extras?.get(TITLE_KEY)?.toString().let { title: String? ->
            tv_second_title.text = title
        }
    }
}