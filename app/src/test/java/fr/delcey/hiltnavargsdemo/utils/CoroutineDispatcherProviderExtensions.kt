package fr.delcey.hiltnavargsdemo.utils


import fr.delcey.hiltnavargsdemo.CoroutineDispatcherProvider
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
fun TestCoroutineRule.getTestCoroutineDispatcherProvider() = mockk<CoroutineDispatcherProvider> {
    every { main } returns testCoroutineDispatcher
    every { io } returns testCoroutineDispatcher
}