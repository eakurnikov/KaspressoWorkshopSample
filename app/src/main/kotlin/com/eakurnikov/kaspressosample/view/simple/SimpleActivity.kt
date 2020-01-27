package com.eakurnikov.kaspressosample.view.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.eakurnikov.kaspressosample.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_simple.*

/**
 * Created by eakurnikov on 2019-12-15
 */
class SimpleActivity : DaggerAppCompatActivity() {

    companion object {
        fun start(context: Context): Unit =
            context.startActivity(Intent(context, SimpleActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)
        AndroidInjection.inject(this)
        initViews()
    }

    private fun initViews() {
        btn_simple_delete.setOnClickListener { et_simple.text = null }

        btn_simple_next.setOnClickListener {
            SecondActivity.start(this, et_simple.text.toString())
        }
    }
}