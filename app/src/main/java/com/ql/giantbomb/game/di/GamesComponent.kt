package com.ql.giantbomb.game.di

import com.ql.giantbomb.base.di.ActivityScope
import com.ql.giantbomb.game.GamesActivity
import com.ql.giantbomb.game.ui.search.SearchFragment
import dagger.Subcomponent


// Scope annotation that the GameComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this Component
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface GamesComponent {

    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): GamesComponent
    }

    // Classes that can be injected by this Component
    fun inject(gamesActivity: GamesActivity)
    fun inject(searchFragment: SearchFragment)
}