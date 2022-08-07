package com.ql.giantbomb.base.di

import com.ql.giantbomb.base.api.GamesService
import com.ql.giantbomb.base.models.ResponseGameSearch
import com.ql.giantbomb.base.utils.wrapEspressoIdlingResource
import com.ql.giantbomb.utils.ResponseFileReader
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import javax.inject.Inject


class FakeGameService @Inject constructor() : GamesService {

    override suspend fun getGames(
        apiKey: String,
        filter: String,
        format: String
    ): ResponseGameSearch {
        if (filter == "name:Call Of Duty") {
            wrapEspressoIdlingResource {
                var result: ResponseGameSearch? = null
                val moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<ResponseGameSearch> =
                    moshi.adapter<ResponseGameSearch>(ResponseGameSearch::class.java)
                val jsonFile =
                    ResponseFileReader.readStringFromFile("response_CallOfDuty.json")
                result = jsonAdapter.fromJson(jsonFile)!!
                return result
            }
        }
        return ResponseGameSearch(number_of_page_results = 0, results = emptyList())
    }


}
