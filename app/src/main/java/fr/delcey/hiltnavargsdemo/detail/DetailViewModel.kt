package fr.delcey.hiltnavargsdemo.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.delcey.hiltnavargsdemo.NavArgProducer
import fr.delcey.hiltnavargsdemo.data.NumberDetails
import fr.delcey.hiltnavargsdemo.data.NumberRepository
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val numberRepository: NumberRepository,
    private val navArgProducer: NavArgProducer
) : ViewModel() {

    val numberInfoLiveData : LiveData<NumberDetails> = liveData {
        val args : DetailFragmentArgs = navArgProducer.getNavArgs(DetailFragmentArgs::class)

        numberRepository.getNumberDetails(args.numberId).collect {
            emit(it)
        }
    }
}
