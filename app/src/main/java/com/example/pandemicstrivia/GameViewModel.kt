package com.example.pandemicstrivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pandemicstrivia.data.Question

class GameViewModel : ViewModel() {

    private val questions = listOf<Question>(
        Question("This is our first Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree"),
        Question("This is our first Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree"),
        Question("This is our first Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree"),
        Question("This is our first Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree"),
        Question("This is our first Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree")
    )

    private val _question = MutableLiveData<Question>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()

    init {
        _score.value = 0
        _question.value = questions[0]
        _currentQuestion.value = 0
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion

    fun updateQuestion(index: Int){
        _question.value = questions[index.plus(1)]
        _currentQuestion.value = index.plus(1)
        // implement check of answer
        //parse in id of button clicked and check if it matches the correct answer
    }


}