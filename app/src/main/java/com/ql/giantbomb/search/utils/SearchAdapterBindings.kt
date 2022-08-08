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
package com.ql.giantbomb.search.utils

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.metaplex.lib.Metaplex
import com.metaplex.lib.modules.nfts.models.NFT
import com.ql.giantbomb.R
import com.ql.giantbomb.search.ui.GamesAdapter

/**
 * [BindingAdapter]s for the [NFT]s list.
 */
@BindingAdapter("app:nft_items")
fun setNFTItems(listView: RecyclerView, items: List<NFT>) {
    (listView.adapter as GamesAdapter).submitList(items)
}

private val TAG = "GamesBindings"

@BindingAdapter("bind:nft", "bind:metaplex")
fun setImage(imageView: ImageView, nft: NFT, metaplex: Metaplex?) {
    if (metaplex == null) {
        return
    }
    nft.metadata(metaplex) { result ->
        result.onSuccess {
            Handler(Looper.getMainLooper()).post {
                Log.d(TAG, "setImage: " + it.image.toString())
                if (imageView.context is AppCompatActivity && !(imageView.context as AppCompatActivity).isDestroyed) {
                    Glide.with(imageView.context)
                        .load(it.image)
                        .placeholder(R.drawable.ic_placeholder)
                        .error(R.drawable.ic_placeholder)
                        .centerCrop()
                        .apply(RequestOptions().transform(RoundedCorners(16)).skipMemoryCache(true))
                        .into(imageView)
                }
            }
        }
    }
}
