package com.ql.giantbomb.game

import android.util.Log
import androidx.lifecycle.*
import com.metaplex.lib.Metaplex
import com.metaplex.lib.drivers.indenty.ReadOnlyIdentityDriver
import com.metaplex.lib.drivers.storage.OkHttpSharedStorageDriver
import com.metaplex.lib.modules.nfts.models.NFT
import com.metaplex.lib.solana.SolanaConnectionDriver
import com.ql.giantbomb.base.models.Game
import com.ql.giantbomb.base.utils.Event
import com.ql.giantbomb.base.utils.wrapEspressoIdlingResource
import com.solana.core.PublicKey
import com.solana.networking.RPCEndpoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class GamesViewModel @Inject constructor(
    private val gamesRepository: GameRepository
) : ViewModel() {

    private val _items = MutableLiveData<List<Game>>().apply { value = emptyList() }
    val items: LiveData<List<Game>> = _items

    private val _itemsNFT = MutableLiveData<List<NFT>>().apply { value = emptyList() }
    val itemsNFTs: LiveData<List<NFT>> = _itemsNFT

    private val _openGameDetails = MutableLiveData<Event<Game>>()
    val openGameDetails: LiveData<Event<Game>> = _openGameDetails

    // Displays Empty List View.
    val empty: LiveData<Boolean> = Transformations.map(_itemsNFT) {
        it.isEmpty()
    }
    var jobGetGames: Job? = null

    private val ownerPublicKey = PublicKey("4oX431PqGrFBk2qrKQeUMZzabuwsP9AUWfpFbK3vxsLU")

    val solanaConnection = SolanaConnectionDriver(RPCEndpoint.mainnetBetaSolana)
    val solanaIdentityDriver = ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
    val storageDriver = OkHttpSharedStorageDriver()
    private lateinit var metaplex: Metaplex


    /**
     * Get the Games from the Repository.
     */
    fun getGames(searchText: String) {
        wrapEspressoIdlingResource {

            if (jobGetGames != null && jobGetGames!!.isActive) {
                jobGetGames?.cancel()
            }
            if (searchText.isEmpty()) {
                _items.value = emptyList()
                return
            }
            jobGetGames = viewModelScope.launch {
                val searchFilter = "name:$searchText"
                val tasksResult = gamesRepository.getGames(searchFilter)

                _items.value = tasksResult.results
            }
        }
    }

    /**
     * Get the Games from the Repository.
     */
    fun getNfts() {
        wrapEspressoIdlingResource {

            if (jobGetGames != null && jobGetGames!!.isActive) {
                jobGetGames?.cancel()
            }
//            if (searchText.isEmpty()) {
//                _items.value = emptyList()
//                return
//            }


            metaplex = Metaplex(solanaConnection, solanaIdentityDriver, storageDriver)
            metaplex.nft.findAllByOwner(ownerPublicKey) { result ->
                result.onSuccess { nfts ->
                    val nftList = nfts.filterNotNull()
                    _itemsNFT.postValue(nftList)
                }
            }
        }
    }

    private val TAG = "GamesViewModel"

    /**
     * Called by Data Binding.
     */
    fun openGameDetails(game: Game) {
        _openGameDetails.value =
            Event(game)
    }

}