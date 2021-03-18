package com.example.hackahtondemo.fragments.entrypage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.hackahtondemo.util.viewModel.ViewModelFactory
import com.example.hackahtondemo.R
import com.example.hackahtondemo.databinding.FragmentFirstBinding
import com.example.hackahtondemo.fragments.detailspage.DetailsFragmentMVC

class EntryFragment : Fragment() {

    private val viewModel: EntryViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProviders.of(this,
            ViewModelFactory(activity.application)
        )
            .get(EntryViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentFirstBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_first,
            container,
            false
        )

        binding.data = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            //showDetailsScreen()
        }
    }

    private fun showDetailsScreen() {
        val detailsFragment = DetailsFragmentMVC()
        val fragmentTransaction = fragmentManager?.beginTransaction() ?: return
        val viewGroup: ViewGroup? = view?.parent as ViewGroup?
        fragmentTransaction.replace(viewGroup?.id ?: 0, detailsFragment)
            .addToBackStack(null)
        fragmentTransaction.commit()
    }
}