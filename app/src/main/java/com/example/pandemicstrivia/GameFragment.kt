package com.example.pandemicstrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.example.pandemicstrivia.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    private val viewModel: GameViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_game, container, false)
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel.score.observe(viewLifecycleOwner, Observer{ newScore ->
            binding.scoreText.text = newScore.toString()

        })

        viewModel.question.observe(viewLifecycleOwner, Observer{ newQuestion ->
            binding.questionText.text = newQuestion.question
            val answerGroup = binding.radioGroup
            answerGroup.removeAllViews()

            for ((index, answer) in newQuestion.answers.withIndex()){
                val newRdb = createAnswerGroup(answer, index)
                answerGroup.addView((newRdb))

            }
        })


        binding.doneButton.setOnClickListener{ view : View ->
            val id = binding.radioGroup.checkedRadioButtonId
            viewModel.checkQuestion(id)
            Toast.makeText(context, "Current score: ${viewModel.score.value}", Toast.LENGTH_SHORT).show()
            //Toast.makeText(context, "Current question id: ${viewModel.currentQuestion.value}", Toast.LENGTH_SHORT).show()
            //Toast.makeText(context, "Current id: $id", Toast.LENGTH_SHORT).show()
                if (viewModel.currentQuestion.value!! < viewModel.totalQuestions.value!!-1){
                    viewModel.updateQuestion(viewModel.currentQuestion.value ?: 0)
                 } else {
                    view.findNavController().navigate(R.id.action_gameFragment_to_scoreFragment)
                }


         }

        return  binding.root
    }

    private fun createAnswerGroup(answer: String, id:Int): RadioButton{
        val rdb = RadioButton(context)
        rdb.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        rdb.text = answer
        rdb.id = id
        return rdb;
    }

}
