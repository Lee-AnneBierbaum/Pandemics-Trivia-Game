package com.example.pandemicstrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pandemicstrivia.databinding.FragmentHomeBinding
import com.example.pandemicstrivia.databinding.FragmentScoreBinding


/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_score, container, false)
        val binding: FragmentScoreBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_score, container, false)
        binding.homeButton.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_scoreFragment_to_homeFragment)
        )
        val totalScore = viewModel.score.value
        val totalQuestions = viewModel.totalQuestions.value
        binding.score.text = " You have scored $totalScore out of $totalQuestions"
        return binding.root
    }

}