package com.example.pandemicstrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pandemicstrivia.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 */
class GameFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       //return inflater.inflate(R.layout.fragment_game, container, false)
        val viewModel : GameViewModel by viewModels()
        val binding: FragmentGameBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_game, container, false)

        viewModel.question.observe(viewLifecycleOwner, Observer{ newQuestion ->
            binding.questionText.text = newQuestion.question
            val answerOne = binding.answerOne
            val answerTwo = binding.answerTwo
            val answerThree = binding.answerThree

           // answerOne.removeAllViews()

            for ((index, answer) in newQuestion.answers.withIndex()){
                val newButton = createAnswerBtn(answer, index)
                //add view

            }
        })

        //binding.Button.setOnClickListener{
        // val id = binding.button.id
        // Toast.makeText(context, "Current id: ${id}", Toast.LENGTH_SHORT).show()
        // viewModel.updateQuestion(viewModel.currentQuestion.value ?: 0)

        // }

        return  binding.root
    }

    private fun createAnswerBtn(answer: String, id:Int): Button{
        val button = Button(context)
        button.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        button.text = answer
        button.id = id
        return button;
    }

}
