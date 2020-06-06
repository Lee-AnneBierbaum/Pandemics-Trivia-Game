package com.example.pandemicstrivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pandemicstrivia.data.Question

class GameViewModel : ViewModel() {

    private val questions = listOf<Question>(
        Question("This is our first Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree"),
        Question("This is our second Question?", listOf("2answerOne", "2answerTwo", "answerThree"), "answerThree"),
        Question("This is our third Question?", listOf("3answerOne", "3answerTwo", "answerThree"), "answerThree"),
        Question("This is our fourth Question?", listOf("4answerOne", "4answerTwo", "answerThree"), "answerThree"),
        Question("This is our fifth Question?", listOf("answerOne", "answerTwo", "answerThree"), "answerThree")
    )

    private val _question = MutableLiveData<Question>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private val _totalQuestions = MutableLiveData<Int>()

    init {
        _score.value = 0
        _question.value = questions[0]
        _currentQuestion.value = 0
        _totalQuestions.value = 3
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    val totalQuestions: LiveData<Int> = _totalQuestions

    fun updateQuestion(index: Int){
        _question.value = questions[index.plus(1)]
        _currentQuestion.value = index.plus(1)

    }

    fun checkQuestion(selectedAnswer: Int){
        val correctAnswer: String? = question.value?.correctAnswer
        val userAnswer: String? = question.value?.answers?.get(selectedAnswer)
        if (userAnswer == correctAnswer){
            _score.value = score.value?.plus(1)
        }
    }

}