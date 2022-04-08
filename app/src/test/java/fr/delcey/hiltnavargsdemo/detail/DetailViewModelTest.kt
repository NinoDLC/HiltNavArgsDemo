package fr.delcey.hiltnavargsdemo.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import fr.delcey.hiltnavargsdemo.NavArgProducer
import fr.delcey.hiltnavargsdemo.data.NumberDetails
import fr.delcey.hiltnavargsdemo.data.NumberRepository
import fr.delcey.hiltnavargsdemo.utils.TestCoroutineRule
import fr.delcey.hiltnavargsdemo.utils.getTestCoroutineDispatcherProvider
import fr.delcey.hiltnavargsdemo.utils.observeForTesting
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceTimeBy
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

        every { numberRepository.getNumberDetails(DEFAULT_NUMBER_INDEX) } returns flow {
            delay(100) // Simulate the computation takes time
            emit(getDefaultNumberDetails())
        }
        every { navArgProducer.getNavArgs(DetailFragmentArgs::class) } returns getDefaultDetailFragmentArgs()

        viewModel = DetailViewModel(
            coroutineDispatcherProvider = testCoroutineRule.getTestCoroutineDispatcherProvider(),
            numberRepository = numberRepository,
            navArgProducer = navArgProducer
        )
    }

    @Test
    fun nominal_case() = testCoroutineRule.runTest {
        // When
        viewModel.numberInfoLiveData.observeForTesting {
            advanceTimeBy(101) // could also use 'advanceTimeBy(100); runCurrent()' or even 'advanceTimeByAndRun(100)' extension

            // Then
            assertEquals(getDefaultNumberDetails(), it.value)
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