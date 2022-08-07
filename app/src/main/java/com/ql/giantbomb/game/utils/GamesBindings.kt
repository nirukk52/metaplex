/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ql.giantbomb.game.utils

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.metaplex.lib.modules.nfts.models.NFT
import com.ql.giantbomb.R
import com.ql.giantbomb.base.models.Game
import com.ql.giantbomb.game.ui.search.GamesAdapter

/**
 * [BindingAdapter]s for the [Game]s list.
 */
@BindingAdapter("app:game_items")
fun setGameItems(listView: RecyclerView, items: List<NFT>) {
    (listView.adapter as GamesAdapter).submitList(items)
}

@BindingAdapter("app:image")
fun setImage(imageView: ImageView, nft: NFT) {

//    nft.metadata(metaplex) { result ->
//        result.onSuccess {
//            if(viewHolder.nftImageView.tag == position) {
//                // Don't Use this change of thread hack. This is a over simplify example.
//                Handler(Looper.getMainLooper()).post(Runnable {
//                    val factory = DrawableCrossFadeFactory.Builder().setCrossFadeEnabled(true).build()
//                    Glide
//                        .with(context)
//                        .load(it.image)
//                        .transition(withCrossFade(factory))
//                        .centerCrop()
//                        .apply(RequestOptions().transform(RoundedCorners(16)).skipMemoryCache(true))
//                        .into(viewHolder.nftImageView)
//                })
//            }
//        }
//    }
//    Glide.with(imageView.context).load(uri).into(imageView)
}

@BindingAdapter("app:html_text")
fun setHtmlText(textView: TextView, htmlText: String?) {
    if (htmlText.isNullOrEmpty()) {
        textView.text = textView.context.getString(R.string.no_description)
    } else {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(htmlText, Html.FROM_HTML_MODE_COMPACT)
        } else {
            textView.text = Html.fromHtml(htmlText)
        }
    }
}
