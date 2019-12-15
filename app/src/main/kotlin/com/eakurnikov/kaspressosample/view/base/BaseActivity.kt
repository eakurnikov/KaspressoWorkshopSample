package com.eakurnikov.kaspressosample.view.base

import androidx.lifecycle.ViewModelProvider
import com.eakurnikov.kaspressosample.viewmodel.base.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

/**
 * Created by eakurnikov on 2019-12-15
 */
abstract class BaseActivity<ViewModel : BaseViewModel> : DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    protected abstract var viewModel: ViewModel

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStop()
    }
}