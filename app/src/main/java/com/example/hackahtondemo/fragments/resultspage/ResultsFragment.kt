package com.example.hackahtondemo.fragments.resultspage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackahtondemo.R
import com.example.hackahtondemo.databinding.FragmentFirstBinding
import com.example.hackahtondemo.databinding.FragmentSecondBinding
import com.example.hackahtondemo.fragments.entrypage.EntryViewModel
import com.example.hackahtondemo.util.adapter.CustomAdapter
import com.example.hackahtondemo.util.api.model.ApiResponse
import com.example.hackahtondemo.util.api.model.CollectionApiResponse
import com.example.hackahtondemo.util.callback.CustomCallback
import com.example.hackahtondemo.util.viewModel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_second.*

class ResultsFragment : Fragment(), CustomCallback {

    private val viewModel: ResultsViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProviders.of(this,
            ViewModelFactory(activity.application, this)
        )
            .get(ResultsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentSecondBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_second,
            container,
            false
        )

        binding.data = viewModel
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val api = ApiResponse("", "")
        collection_recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        collection_recycler.addItemDecoration(DividerItemDecoration(activity, DividerItemDecoration.VERTICAL))
        collection_recycler.adapter = CustomAdapter(listOf(api))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    override fun onDataCollected(response: CollectionApiResponse?) {
        response?: return
        collection_recycler.adapter = CustomAdapter(response.custom_collections)
    }
}