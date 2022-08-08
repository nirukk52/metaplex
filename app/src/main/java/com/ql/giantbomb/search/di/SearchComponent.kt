package com.ql.giantbomb.search.di

import com.ql.giantbomb.base.di.ActivityScope
import com.ql.giantbomb.search.SearchMainActivity
import com.ql.giantbomb.search.ui.SearchFragment
import dagger.Subcomponent


// Scope annotation that the GameComponent uses
// Classes annotated with @ActivityScope will have a unique instance in this Component
@ActivityScope
// Definition of a Dagger subcomponent
@Subcomponent
interface SearchComponent {

    // Factory to create instances of UserComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): SearchComponent
    }

    // Classes that can be injected by this Component
    fun inject(searchMainActivity: SearchMainActivity)
    fun inject(searchFragment: SearchFragment)
}