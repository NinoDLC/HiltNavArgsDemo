package fr.delcey.hiltnavargsdemo.detail

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import fr.delcey.hiltnavargsdemo.R

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.detail_fragment) {

    private val viewModel by viewModels<DetailViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val numberTextView = view.findViewById<TextView>(R.id.details_tv_number)
        val triviaTextView = view.findViewById<TextView>(R.id.details_tv_trivia)

        viewModel.numberInfoLiveData.observe(viewLifecycleOwner) {
            numberTextView.text = it.value
            triviaTextView.text = it.trivia
        }
    }
}