package com.ql.giantbomb.game.ui.search

//import com.ql.giantbomb.game.ui.search.SearchFragmentDirections.Companion.actionSearchFragmentToDetailsFragment
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener
import androidx.annotation.CheckResult
import androidx.fragment.app.Fragment
import com.metaplex.lib.Metaplex
import com.metaplex.lib.drivers.indenty.ReadOnlyIdentityDriver
import com.metaplex.lib.drivers.storage.OkHttpSharedStorageDriver
import com.metaplex.lib.solana.SolanaConnectionDriver
import com.ql.giantbomb.base.models.Game
import com.ql.giantbomb.base.utils.EventObserver
import com.ql.giantbomb.databinding.FragmentSearchBinding
import com.ql.giantbomb.game.GamesActivity
import com.ql.giantbomb.game.GamesViewModel
import com.solana.core.PublicKey
import com.solana.networking.RPCEndpoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import timber.log.Timber
import javax.inject.Inject


class SearchFragment : Fragment() {

    private val TAG = "SearchFragment"
    private lateinit var viewDataBinding: FragmentSearchBinding

    @Inject
    lateinit var viewModel: GamesViewModel

    private lateinit var listAdapter: GamesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as GamesActivity).gamesComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }

        viewDataBinding.lifecycleOwner = this
//        setupNavigation()
        return viewDataBinding.root
    }

    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        viewDataBinding.etSearchGames.textChanges()
//            .distinctUntilChanged()
//            .debounce(300)
//            .onEach {
//                viewModel.getGames(it.toString())
//            }
//            .launchIn(CoroutineScope(Dispatchers.Main))
//
//        viewDataBinding.etSearchGames.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
//            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
//                viewModel.getGames(viewDataBinding.etSearchGames.text.toString())
//                return@OnEditorActionListener true
//            }
//            false
//        })

        viewModel.getNfts()

        setupListAdapter()
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
////        val solanaConnection = SolanaConnectionDriver(RPCEndpoint.mainnetBetaSolana)
////        val solanaIdentityDriver = ReadOnlyIdentityDriver(ownerPublicKey, solanaConnection.solanaRPC)
////        val storageDriver = OkHttpSharedStorageDriver()
////
////        metaplex = Metaplex(solanaConnection, solanaIdentityDriver, storageDriver)
////        metaplex.nft.findAllByOwner(ownerPublicKey) { result ->
////            result.onSuccess { nfts ->
////                val nftList = nfts.filterNotNull()
////                Log.d(TAG, "onViewCreated: " + nftList)
////            }
////        }
//    }

//
//    private fun setupNavigation() {
//        viewModel.openGameDetails.observe(viewLifecycleOwner,
//            EventObserver {
//                openTaskDetails(it)
//            })
//    }

    private fun openTaskDetails(game: Game) {
//        val action = actionSearchFragmentToDetailsFragment(game)
//        findNavController().navigate(action)
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = GamesAdapter(viewModel)
            viewDataBinding.rvGamesList.adapter = listAdapter
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }
//
//    @ExperimentalCoroutinesApi
//    @CheckResult
//    fun EditText.textChanges(): Flow<CharSequence?> {
//        return callbackFlow {
//            val listener = object : TextWatcher {
//                override fun afterTextChanged(s: Editable?) = Unit
//                override fun beforeTextChanged(
//                    s: CharSequence?,
//                    start: Int,
//                    count: Int,
//                    after: Int
//                ) = Unit
//
//                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                    this@callbackFlow.trySend(s).isSuccess
//                }
//            }
//            addTextChangedListener(listener)
//            awaitClose { removeTextChangedListener(listener) }
//        }.onStart { emit(text) }
//    }

}