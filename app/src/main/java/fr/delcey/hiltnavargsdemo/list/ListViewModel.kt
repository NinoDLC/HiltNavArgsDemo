package fr.delcey.hiltnavargsdemo.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.delcey.hiltnavargsdemo.CoroutineDispatcherProvider
import fr.delcey.hiltnavargsdemo.data.IndexedNumber
import fr.delcey.hiltnavargsdemo.data.NumberRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    coroutineDispatcherProvider: CoroutineDispatcherProvider,
    private val numberRepository: NumberRepository,
) : ViewModel() {

    val numbersLiveData: LiveData<List<IndexedNumber>> = liveData(coroutineDispatcherProvider.io) {
        numberRepository.getNumbersFlow().collect {
            emit(it)
        }
    }
}