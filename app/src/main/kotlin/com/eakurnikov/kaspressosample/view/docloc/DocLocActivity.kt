package com.eakurnikov.kaspressosample.view.docloc

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.base.BaseActivity
import com.eakurnikov.kaspressosample.viewmodel.docloc.DocLocViewModel
import dagger.android.AndroidInjection

/**
 * Created by eakurnikov on 2019-12-15
 */
class DocLocActivity : BaseActivity<DocLocViewModel>() {

    companion object {
        fun start(context: Context): Unit =
            context.startActivity(Intent(context, DocLocActivity::class.java))
    }

    override lateinit var viewModel: DocLocViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_doc_loc)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(DocLocViewModel::class.java)

        initViews()
    }

    private fun initViews() {

    }
}