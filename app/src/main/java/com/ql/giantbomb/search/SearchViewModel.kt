package com.ql.giantbomb.search

import android.util.Log
import androidx.lifecycle.*
import com.metaplex.lib.modules.nfts.models.NFT
import com.ql.giantbomb.base.utils.wrapEspressoIdlingResource
import com.ql.giantbomb.search.ui.SearchRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class SearchViewModel : ViewModel() {

    private val TAG = "SearchViewModel"
    val searchRepository = SearchRepository()
    private val _itemsNFT = MutableLiveData<List<NFT>>().apply { value = emptyList() }
    val itemsNFTs: LiveData<List<NFT>> = _itemsNFT

    val empty: LiveData<Boolean> = Transformations.map(_itemsNFT) { it.isEmpty() }

    val emptyMessage =
        MutableLiveData<String>().apply { "Sorry! No NFTs to show. Try a Different Key" }

    var jobGetGames: Job? = null

    fun getNfts(key: String) {
        wrapEspressoIdlingResource {

            if (jobGetGames != null && jobGetGames!!.isActive) {
                jobGetGames?.cancel()
            }

            if (key.contains(" ")) {
                emptyMessage.postValue("Invalid Key!")
                _itemsNFT.postValue(emptyList())
                return
            }

            emptyMessage.postValue("Loading NFTs...")

            jobGetGames = viewModelScope.launch {
                val result = searchRepository.getNFTs(key)

                result.onSuccess { nfts ->
                    val nftList = nfts.filterNotNull()
                    if (nftList.isEmpty()) {
                        emptyMessage.postValue("No NFTs found for the key!")
                    }
                    Log.d(TAG, "getNfts: " + nftList.size)
                    _itemsNFT.postValue(nftList)
                }
                result.onFailure { error ->
                    Timber.e(error)
                    emptyMessage.postValue("Sorry there was error loading NFTs for this key!")
                    _itemsNFT.postValue(emptyList())
                }
            }
        }
    }

}