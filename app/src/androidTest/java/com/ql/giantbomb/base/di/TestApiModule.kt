package com.ql.giantbomb.base.di

import com.ql.giantbomb.base.api.GamesService
import dagger.Binds
import dagger.Module

@Module
abstract class TestApiModule {

    // Makes Dagger provide FakeStorage when a Storage type is requested
    @Binds
    abstract fun provideFakeGameService(gameService: FakeGameService): GamesService

}