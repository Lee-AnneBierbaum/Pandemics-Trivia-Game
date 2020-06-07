package com.example.pandemicstrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.pandemicstrivia.databinding.FragmentDetailBinding

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_detail, container, false)
        val binding: FragmentDetailBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        viewModel.categoryHeading.observe(viewLifecycleOwner, Observer { catHeading ->
            binding.categoryHeading.text = catHeading
        })

        viewModel.categoryDescription.observe(viewLifecycleOwner, Observer { catDescription ->
            binding.categoryParagraph.text = catDescription
        })

        binding.startQuiz.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_detailFragment_to_gameFragment)
        )
        return binding.root
    }

}
