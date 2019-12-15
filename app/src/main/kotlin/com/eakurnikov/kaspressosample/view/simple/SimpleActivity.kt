package com.eakurnikov.kaspressosample.view.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.base.BaseActivity
import com.eakurnikov.kaspressosample.viewmodel.simple.SimpleViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_simple.*

/**
 * Created by eakurnikov on 2019-12-15
 */
class SimpleActivity : BaseActivity<SimpleViewModel>() {

    companion object {
        fun start(context: Context): Unit =
            context.startActivity(Intent(context, SimpleActivity::class.java))
    }

    override lateinit var viewModel: SimpleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SimpleViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        btn_delete.setOnClickListener { et_simple.text = null }
    }
}