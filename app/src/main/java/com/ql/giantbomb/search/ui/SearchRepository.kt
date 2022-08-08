package com.ql.giantbomb.search.ui

import com.metaplex.lib.Metaplex
import com.metaplex.lib.drivers.indenty.ReadOnlyIdentityDriver
import com.metaplex.lib.drivers.storage.OkHttpSharedStorageDriver
import com.metaplex.lib.modules.nfts.models.NFT
import com.metaplex.lib.shared.OperationError
import com.metaplex.lib.shared.ResultWithCustomError
import com.metaplex.lib.solana.SolanaConnectionDriver
import com.ql.giantbomb.base.utils.wrapEspressoIdlingResource
import com.solana.core.PublicKey
import com.solana.networking.RPCEndpoint
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class SearchRepository {

    var ownerPublicKey = PublicKey("")
    val solanaConnection = SolanaConnectionDriver(RPCEndpoint.mainnetBetaSolana)
    var solanaIdentityDriver = ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
    val storageDriver = OkHttpSharedStorageDriver()
    val metaplex: Metaplex = Metaplex(solanaConnection, solanaIdentityDriver, storageDriver)

    suspend fun getNFTs(id: String): ResultWithCustomError<List<NFT?>, OperationError> =
        suspendCoroutine { continuation ->
            ownerPublicKey = PublicKey(id)
            solanaIdentityDriver =
                ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
            metaplex.setIdentity(solanaIdentityDriver)
            wrapEspressoIdlingResource {
                metaplex.nft.findAllByOwner(ownerPublicKey) { result ->
                    continuation.resume(result)
                }
            }
        }
}