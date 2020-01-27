package com.eakurnikov.kaspressosample.view.docloc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.eakurnikov.kaspressosample.R
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

/**
 * Created by eakurnikov on 2019-12-15
 */
class DocLocActivity : DaggerAppCompatActivity() {

    companion object {
        fun start(context: Context): Unit =
            context.startActivity(Intent(context, DocLocActivity::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_loc)
        AndroidInjection.inject(this)
    }
}