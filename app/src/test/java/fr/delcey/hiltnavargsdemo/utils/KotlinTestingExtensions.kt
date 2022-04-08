package fr.delcey.hiltnavargsdemo.utils

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runCurrent

/**
 * Don't even ask about the runCurrent() part.
 * Forget everything you knew about coroutine testing before.
 * We were apes playing with mud it seems.
 * This change happened in coroutine testing 1.6.
 *
 * https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md#replace-advancetimebyn-with-advancetimebyn-runcurrent
 *
 * Why is it needed ? *shrugs*
 */
@ExperimentalCoroutinesApi
fun TestScope.advanceTimeByAndRun(delayTimeMillis: Long) {
    testScheduler.advanceTimeBy(delayTimeMillis)
    runCurrent()
}