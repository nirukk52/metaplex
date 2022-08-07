package com.ql.giantbomb.game.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ql.giantbomb.databinding.FragmentGameDetailsBinding
import com.ql.giantbomb.game.GamesActivity
import timber.log.Timber


class GameDetailsFragment : Fragment() {

    private lateinit var viewDataBinding: FragmentGameDetailsBinding

    private val args: GameDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentGameDetailsBinding.inflate(inflater, container, false).apply {
            game = args.game
        }

        (activity as AppCompatActivity?)!!.setSupportActionBar(viewDataBinding.toolbar)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setDisplayShowHomeEnabled(true)
        (activity as AppCompatActivity?)?.supportActionBar?.setHomeButtonEnabled(true)

        viewDataBinding.toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        Timber.d("GamesActivity" + (activity as GamesActivity).gamesComponent.hashCode())

        return viewDataBinding.root
    }
}

