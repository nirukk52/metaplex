package com.ql.giantbomb.search.ui

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ql.giantbomb.R
import com.ql.giantbomb.databinding.FragmentSearchBinding
import com.ql.giantbomb.search.SearchMainActivity
import com.ql.giantbomb.search.SearchViewModel
import timber.log.Timber
import javax.inject.Inject

class SearchFragment : Fragment() {

    private val TAG = "SearchFragment"
    private lateinit var viewDataBinding: FragmentSearchBinding

    val model: SearchViewModel by activityViewModels()

    private lateinit var listAdapter: GamesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity as SearchMainActivity).searchComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewmodel = model
        }

        viewDataBinding.lifecycleOwner = this
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListAdapter()
        if (model.itemsNFTs.value?.isEmpty() == true) {
            model.getNfts(requireActivity().getString(R.string.default_key))
        }

        viewDataBinding.btGo.setOnClickListener {
            if (viewDataBinding.etSearchGames.text.isNullOrEmpty()) {
                model.getNfts(requireActivity().getString(R.string.default_key))
            } else {
                model.getNfts(viewDataBinding.etSearchGames.text.toString())
            }
        }
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = GamesAdapter(viewModel)
            viewDataBinding.rvGamesList.adapter = listAdapter
            val orientation = resources.configuration.orientation
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                viewDataBinding.rvGamesList.layoutManager = GridLayoutManager(context, 4)
            } else {
                viewDataBinding.rvGamesList.layoutManager = GridLayoutManager(context, 2)
            }
        } else {
            Timber.w("ViewModel not initialized when attempting to set up adapter.")
        }
    }
}