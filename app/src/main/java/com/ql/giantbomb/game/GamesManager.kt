package com.ql.giantbomb.game

import android.util.Log
import com.metaplex.lib.Metaplex
import com.metaplex.lib.drivers.indenty.ReadOnlyIdentityDriver
import com.metaplex.lib.drivers.storage.OkHttpSharedStorageDriver
import com.metaplex.lib.modules.nfts.models.NFT
import com.metaplex.lib.shared.OperationError
import com.metaplex.lib.shared.ResultWithCustomError
import com.metaplex.lib.solana.SolanaConnectionDriver
import com.ql.giantbomb.base.api.GamesService
import com.ql.giantbomb.base.di.ActivityScope
import com.ql.giantbomb.base.models.ResponseGameSearch
import com.ql.giantbomb.base.utils.wrapEspressoIdlingResource
import com.solana.core.PublicKey
import com.solana.networking.RPCEndpoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.internal.wait
import javax.inject.Inject

@ActivityScope
class GamesManager @Inject constructor(
    private val gamesService: GamesService
) {

    suspend fun getGames(searchFilter: String): ResponseGameSearch {
        return withContext(Dispatchers.IO) {
            wrapEspressoIdlingResource {
                return@withContext gamesService.getGames(filter = searchFilter)
            }
        }
    }


    private val ownerPublicKey = PublicKey("4oX431PqGrFBk2qrKQeUMZzabuwsP9AUWfpFbK3vxsLU")

    val solanaConnection = SolanaConnectionDriver(RPCEndpoint.mainnetBetaSolana)
    val solanaIdentityDriver = ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
    val storageDriver = OkHttpSharedStorageDriver()
    val metaplex = Metaplex(solanaConnection, solanaIdentityDriver, storageDriver)

//    suspend fun getNFTs(searchFilter: String): ResultWithCustomError<List<NFT?>, OperationError> {
//        val res =  metaplex.nft.findAllByOwner(ownerPublicKey) {
//            resultWithCustomError ->  return@findAllByOwner
//        }
//        return  res
//    }

//    suspend fun getNFTs(searchFilter: String): ResultWithCustomError<List<NFT?>, OperationError> {
//        return metaplex.nft.findAllByOwner(ownerPublicKey) { result ->
//            result.onSuccess { nfts ->
//                val nftList = nfts.filterNotNull()
//                return@onSuccess
//            }
//        }
//    }


}

