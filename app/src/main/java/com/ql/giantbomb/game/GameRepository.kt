package com.ql.giantbomb.game

import com.metaplex.lib.Metaplex
import com.metaplex.lib.drivers.indenty.ReadOnlyIdentityDriver
import com.metaplex.lib.drivers.storage.OkHttpSharedStorageDriver
import com.metaplex.lib.modules.nfts.models.NFT
import com.metaplex.lib.solana.SolanaConnectionDriver
import com.ql.giantbomb.base.models.ResponseGameSearch
import com.ql.giantbomb.base.utils.wrapEspressoIdlingResource
import com.solana.core.PublicKey
import com.solana.networking.RPCEndpoint
import javax.inject.Inject

class GameRepository @Inject constructor(
    private val gamesManager: GamesManager
) {

    suspend fun getGames(searchFilter: String): ResponseGameSearch {
        wrapEspressoIdlingResource {
            return gamesManager.getGames(searchFilter)
        }
    }

    private val ownerPublicKey = PublicKey("4oX431PqGrFBk2qrKQeUMZzabuwsP9AUWfpFbK3vxsLU")

    val solanaConnection = SolanaConnectionDriver(RPCEndpoint.mainnetBetaSolana)
    val solanaIdentityDriver = ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
    val storageDriver = OkHttpSharedStorageDriver()
    val metaplex = Metaplex(solanaConnection, solanaIdentityDriver, storageDriver)
//
//    suspend fun getNFTs(searchFilter: String): List<NFT> {
//        wrapEspressoIdlingResource {
//            metaplex.nft.findAllByOwner(ownerPublicKey) { result ->
//                result.onSuccess { nfts ->
//                    val nftList = nfts.filterNotNull()
//                    return@onSuccess
//                }
//            }
//        }
//    }


}