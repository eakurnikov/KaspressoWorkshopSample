package com.eakurnikov.kaspressosample.view.simple

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.view.base.BaseActivity
import com.eakurnikov.kaspressosample.viewmodel.simple.SecondViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_second.*

/**
 * Created by eakurnikov on 2019-12-17
 */
class SecondActivity : BaseActivity<SecondViewModel>() {

    companion object {
        private const val TITLE_KEY: String = "TITLE_KEY"

        fun start(context: Context, title: String): Unit = context.startActivity(
            Intent(context, SecondActivity::class.java).putExtra(TITLE_KEY, title)
        )
    }

    override lateinit var viewModel: SecondViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(SecondViewModel::class.java)

        initViews()
    }

    private fun initViews() {
        intent?.extras?.get(TITLE_KEY)?.toString().let { title: String? ->
            tv_second_title.text =
                if (title.isNullOrEmpty()) getString(R.string.second_title_stub) else title
        }
    }
}