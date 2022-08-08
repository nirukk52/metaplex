package com.ql.giantbomb.search

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ql.giantbomb.MyApplication
import com.ql.giantbomb.R
import com.ql.giantbomb.search.di.SearchComponent
import timber.log.Timber

class SearchMainActivity : AppCompatActivity() {

    // Stores an instance of RegistrationComponent so that its Fragments can access it
    lateinit var searchComponent: SearchComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of Registration component by grabbing the factory from the app graph
        searchComponent = (application as MyApplication).appComponent
            .searchComponent().create()

        // Injects this activity to the just created Registration component
        searchComponent.inject(this)

        Timber.d("GamesActivity" + searchComponent.hashCode())

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
    }

}