package com.ql.giantbomb.game

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ql.giantbomb.base.di.FakeGameService
import com.ql.giantbomb.base.models.Game
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GameViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()


    private lateinit var viewModel: GamesViewModel
    private lateinit var gameRepository: GameRepository
    private lateinit var mockWebServer: MockWebServer
    private lateinit var gameManager: GamesManager

    @Mock
    private lateinit var apiItemsObserver: Observer<List<Game>>

    @Before
    fun setup() {

        MockitoAnnotations.initMocks(this)


        mockWebServer = MockWebServer()
        mockWebServer.start()


        gameRepository = Mockito.mock(GameRepository::class.java)
        viewModel = GamesViewModel(gameRepository)

        viewModel.items.observeForever(apiItemsObserver)

        gameManager = GamesManager(FakeGameService())

    }

//    @Test
//    fun `read sample success json file`() {
//        val reader =
//            MockResponseFileReader("success_response.json")
//        assert(reader.content != null)
//    }

//    @Test
//    fun `fetch details and check response Code 200 returned`() {
//        // Assign
//        testCoroutineRule.runBlockingTest {
//            val response = MockResponse()
//                .setResponseCode(HttpURLConnection.HTTP_OK)
//                .setBody(MockResponseFileReader("success_response.json").content)
//            mockWebServer.enqueue(response)
//            // Act
//            val actualResponse = gameManager.getGames("")
//            // Assert
//            assertEquals(
//                actualResponse, null
//            )
//
//        }
//    }


//    @Test
//    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
//        testCoroutineRule.runBlockingTest {
//            Mockito.doReturn(ResponseGameSearch())
//                .`when`(gameRepository).getGames("")
//
//            viewModel.items.observeForever(apiItemsObserver)
//
//
//            viewModel.getGames("")
//
//            Mockito.verify(apiItemsObserver).onChanged(emptyList())
//
//            viewModel.items.removeObserver(apiItemsObserver)
//        }
//    }


//    @Test
//    fun GetGamesTest() {
//        testCoroutineRule.runBlockingTest {
//            Mockito.`when`(gameRepository.getGames("Call Of Duty")).thenReturn(
//                ResponseGameSearch(error = "OK", results = ArrayList<Game>(100))
//            )
//
//            viewModel.getGames("Call Of Duty")
//
//            assertEquals(viewModel.items.value?.size, 100)
//
//        }
//    }
}