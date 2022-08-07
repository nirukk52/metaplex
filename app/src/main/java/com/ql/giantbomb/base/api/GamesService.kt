package com.ql.giantbomb.base.api

import com.ql.giantbomb.base.models.ResponseGameSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface GamesService {

    @GET("api/games")
    suspend fun getGames(
        @Query("api_key") apiKey: String = "9d45436f87d3848d2bdcce810bacb6df57dfd134",
        @Query("filter") filter: String,
        @Query("format") format: String = "json"
    ): ResponseGameSearch

}