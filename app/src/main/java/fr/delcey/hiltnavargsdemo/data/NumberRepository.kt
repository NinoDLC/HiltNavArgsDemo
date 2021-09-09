package fr.delcey.hiltnavargsdemo.data

import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class NumberRepository @Inject constructor() {

    private val numberList = List(100) {
        IndexedNumber(numberId = it, value = Random.nextInt(101).toString())
    }

    /**
     * Generates 10 random numbers, from 0 to 100 (inclusive), and emits them as a List in a Flow
     */
    fun getNumbersFlow() = flow {
        emit(numberList)
    }

    fun getNumberDetails(index: Int) = flow {
        val numberInfo = getNumberDetails(numberList[index])

        emit(numberInfo)
    }

    private fun getNumberDetails(indexedNumber: IndexedNumber): NumberDetails {
        val valueAsInt = indexedNumber.value.toInt()

        val triviaStringBuilder = StringBuilder()

        triviaStringBuilder.append(
            if (valueAsInt % 2 == 0) {
                "This number is pair\n"
            } else {
                "This number is odd\n"
            }
        )
        if (isIntPrime(valueAsInt)) {
            triviaStringBuilder.append("This number is prime\n")
        }
        if (valueAsInt == 2) {
            triviaStringBuilder.append("This is the only pair prime number\n")
        }
        if (isIntTriangular(valueAsInt)) {
            triviaStringBuilder.append("This number is triangular\n")
        }
        triviaStringBuilder.append("$valueAsInt squared is ${valueAsInt * valueAsInt}")

        return NumberDetails(value = indexedNumber.value, trivia = triviaStringBuilder.toString())
    }

    private fun isIntPrime(number: Int): Boolean {
        if (number < 2) {
            return false
        }
        for (i in 2..(number / 2)) {
            if (number % i == 0) {
                return false
            }
        }
        return true
    }

    private fun isIntTriangular(number: Int): Boolean {
        var accumulated = 0

        for (i in 1..number) {
            accumulated += i
            if (number == accumulated) {
                return true
            } else if (number < accumulated) {
                return false
            }
        }

        return false
    }
}