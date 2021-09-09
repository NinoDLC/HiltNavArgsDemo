package fr.delcey.hiltnavargsdemo.list

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import fr.delcey.hiltnavargsdemo.R

@AndroidEntryPoint
class ListFragment : Fragment(R.layout.list_fragment) {

    private val viewModel by viewModels<ListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.main_rv)
        val adapter = NumberAdapter { itemIndex ->
            Log.d("Nino", "listener called with $itemIndex")
            findNavController().navigate(ListFragmentDirections.navigateToDetail(itemIndex))
        }

        recyclerView.adapter = adapter

        viewModel.numbersLiveData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }
}