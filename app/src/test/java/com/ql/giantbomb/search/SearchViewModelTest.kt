package com.ql.giantbomb.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.metaplex.lib.modules.nfts.models.NFT
import com.ql.giantbomb.search.ui.SearchRepository
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest {


    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var searchRepository: SearchRepository

    private var viewModel: SearchViewModel = SearchViewModel()

    @Mock
    private lateinit var apiItemsObserver: Observer<List<NFT>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun `test invalid key`() {
        viewModel.getNfts("ah ajh")

        assertTrue(viewModel.empty.value!!)
        assertEquals("Invalid Key!", viewModel.emptyMessage.value)
    }

}