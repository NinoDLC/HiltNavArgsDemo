package fr.delcey.hiltnavargsdemo.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.delcey.hiltnavargsdemo.NavArgProducer
import fr.delcey.hiltnavargsdemo.data.NumberDetails
import fr.delcey.hiltnavargsdemo.data.NumberRepository
import fr.delcey.hiltnavargsdemo.utils.TestCoroutineRule
import fr.delcey.hiltnavargsdemo.utils.assertNonNullLiveDataValue
import fr.delcey.hiltnavargsdemo.utils.observeForTesting
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    companion object {
        // IN
        private const val DEFAULT_NUMBER_INDEX = 42

        // OUT
        private const val DEFAULT_NUMBER_VALUE = "666"
        private const val DEFAULT_NUMBER_TRIVIA = "DEFAULT_NUMBER_TRIVIA"
    }

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var numberRepository: NumberRepository

    @MockK
    private lateinit var navArgProducer: NavArgProducer

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)

        every { numberRepository.getNumberDetails(DEFAULT_NUMBER_INDEX) } returns flowOf(getDefaultNumberDetails())
        every { navArgProducer.getNavArgs(DetailFragmentArgs::class) } returns getDefaultDetailFragmentArgs()

        viewModel = DetailViewModel(numberRepository, navArgProducer)
    }

    @Test
    fun nominal_case() = testCoroutineRule.runBlockingTest {
        // When
        viewModel.numberInfoLiveData.observeForTesting {
            // Then
            val value = assertNonNullLiveDataValue<NumberDetails>(it)
            assertEquals(getDefaultNumberDetails(), value)
        }
    }

    // region IN
    private fun getDefaultDetailFragmentArgs() = DetailFragmentArgs(
        numberId = DEFAULT_NUMBER_INDEX
    )
    // endregion IN

    // region OUT
    private fun getDefaultNumberDetails() = NumberDetails(
        value = DEFAULT_NUMBER_VALUE,
        trivia = DEFAULT_NUMBER_TRIVIA
    )
    // endregion OUT
}