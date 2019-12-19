package com.eakurnikov.kaspressosample.posts.screen

import android.view.View
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.eakurnikov.kaspressosample.R
import org.hamcrest.Matcher

/**
 * Created by eakurnikov on 2019-12-18
 */
object PostsScreen : Screen<PostsScreen>() {

    val errorTextView = KTextView { withId(R.id.tv_posts_error) }

    val postsList = KRecyclerView(
        builder = { withId(R.id.list_posts) },
        itemTypeBuilder = { itemType(::PostItem) }
    )

    class PostItem(parent: Matcher<View>) : KRecyclerItem<PostItem>(parent) {
        val title = KTextView(parent) { withId(R.id.tv_post_title) }
        val body = KTextView(parent) { withId(R.id.tv_post_body) }
    }
}