package com.ql.giantbomb.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ql.giantbomb.MyApplication
import com.ql.giantbomb.R
import com.ql.giantbomb.game.di.GamesComponent
import timber.log.Timber

class GamesActivity : AppCompatActivity() {

    // Stores an instance of RegistrationComponent so that its Fragments can access it
    lateinit var gamesComponent: GamesComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        // Creates an instance of Registration component by grabbing the factory from the app graph
        gamesComponent = (application as MyApplication).appComponent
            .gamesComponent().create()

        // Injects this activity to the just created Registration component
        gamesComponent.inject(this)

        Timber.d("GamesActivity" + gamesComponent.hashCode())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
    }


}