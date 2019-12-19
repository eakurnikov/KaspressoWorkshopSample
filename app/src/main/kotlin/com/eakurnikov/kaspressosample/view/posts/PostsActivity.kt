package com.eakurnikov.kaspressosample.view.posts

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.eakurnikov.kaspressosample.R
import com.eakurnikov.kaspressosample.data.model.Resource
import com.eakurnikov.kaspressosample.data.model.Post
import com.eakurnikov.kaspressosample.view.base.BaseActivity
import com.eakurnikov.kaspressosample.viewmodel.posts.PostsViewModel
import dagger.android.AndroidInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_posts.*

/**
 * Created by eakurnikov on 2019-12-15
 */
class PostsActivity : BaseActivity<PostsViewModel>() {

    companion object {
        fun start(context: Context): Unit =
            context.startActivity(Intent(context, PostsActivity::class.java))
    }

    override lateinit var viewModel: PostsViewModel

    private val adapter: PostsAdapter = PostsAdapter()

    private var viewModelDisposable: Disposable? = null

    private val onPosts = object : DisposableObserver<Resource<List<Post>>>() {
        override fun onComplete() {
        }

        override fun onNext(resource: Resource<List<Post>>) {
            when (resource) {
                is Resource.Success -> {
                    when (resource.data.size) {
                        0 -> showEmptyList()
                        else -> showPosts(resource.data)
                    }
                }
                is Resource.Loading -> showLoading(false)
                is Resource.Error -> showError(resource.message)
            }
        }

        override fun onError(error: Throwable) {
            showError(error.message ?: getString(R.string.error))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_posts)

        AndroidInjection.inject(this)

        viewModel = ViewModelProviders
            .of(this, viewModelFactory)
            .get(PostsViewModel::class.java)

        initViews()
    }

    override fun onStart() {
        super.onStart()
        viewModelDisposable = viewModel.postsSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(onPosts)
    }

    override fun onStop() {
        super.onStop()
        viewModelDisposable?.dispose()
        viewModelDisposable = null
    }

    private fun initViews() {
        showLoading(false)

        layout_refresh_posts.setOnRefreshListener {
            showLoading(true)
            viewModel.onRefresh()
        }

        list_posts.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@PostsActivity)
            adapter = this@PostsActivity.adapter
        }
    }

    private fun showLoading(isRefresh: Boolean) {
        if (isRefresh) {
            layout_refresh_posts.isRefreshing = true
            progress_bar_posts.visibility = View.GONE
        } else if (!layout_refresh_posts.isRefreshing) {
            progress_bar_posts.visibility = View.VISIBLE
        }
    }

    private fun showPosts(posts: List<Post>) {
        layout_refresh_posts.isRefreshing = false
        progress_bar_posts.visibility = View.GONE
        list_posts.visibility = View.VISIBLE
        tv_posts_error.visibility = View.GONE

        adapter.apply {
            data = posts
            notifyDataSetChanged()
        }
    }

    private fun showEmptyList() {
        layout_refresh_posts.isRefreshing = false
        progress_bar_posts.visibility = View.GONE
        list_posts.visibility = View.GONE
        tv_posts_error.visibility = View.VISIBLE
        tv_posts_error.text = getString(R.string.no_entries)
    }

    private fun showError(message: String) {
        layout_refresh_posts.isRefreshing = false
        progress_bar_posts.visibility = View.GONE
        list_posts.visibility = View.GONE
        tv_posts_error.visibility = View.VISIBLE
        tv_posts_error.text = message
    }
}