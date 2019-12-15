package com.eakurnikov.kaspressosample.view.main

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.base.BaseActivity
import com.eakurnikov.kaspressosample.viewmodel.main.MainViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by eakurnikov on 2019-12-15
 */
class MainActivity : BaseActivity<MainViewModel>() {

    private val onBtnClickListener = { view: View? ->
        viewModel.onButtonClick(this@MainActivity, view)
    }

    override lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(MainViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        btn_simple_screen.setOnClickListener(onBtnClickListener)
        btn_doc_loc_screen.setOnClickListener(onBtnClickListener)
        btn_posts_screen.setOnClickListener(onBtnClickListener)
        btn_flaky_screen.setOnClickListener(onBtnClickListener)
        btn_webview_screen.setOnClickListener(onBtnClickListener)
    }
}