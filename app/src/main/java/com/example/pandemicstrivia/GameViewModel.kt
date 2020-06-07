package com.example.pandemicstrivia

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pandemicstrivia.data.Question

class GameViewModel : ViewModel() {


    private val _question = MutableLiveData<Question>()
    private val _currentQuestion = MutableLiveData<Int>()
    private val _score = MutableLiveData<Int>()
    private val _totalQuestions = MutableLiveData<Int>()
    private val _categoryQuestions = MutableLiveData<List<Question>>()
    private val _categoryHeading = MutableLiveData<String>()
    private val _categoryDescription = MutableLiveData<String>()


    fun setupGame(categoryId: Int){
        val newQuestions = questions.filter{q -> q.categoryId == categoryId }
        _categoryQuestions.postValue(newQuestions)
        _question.value = newQuestions[0]
        _currentQuestion.value = 0
        _score.value = 0
        _totalQuestions.value = 3
        _categoryHeading.value = generateCategoryList[categoryId-1].name
        _categoryDescription.value = generateCategoryList[categoryId-1].description
    }

    val question: LiveData<Question> = _question
    val currentQuestion: LiveData<Int> = _currentQuestion
    val score: LiveData<Int> = _score
    val totalQuestions: LiveData<Int> = _totalQuestions
    val categoryHeading: LiveData<String> = _categoryHeading
    val categoryDescription: LiveData<String> = _categoryDescription

    fun updateQuestion(index: Int){
        _question.value = _categoryQuestions.value?.get(index.plus(1))
        _currentQuestion.value = index.plus(1)

    }

    fun checkQuestion(selectedAnswer: Int){
        val correctAnswer: String? = question.value?.correctAnswer
        val userAnswer: String? = question.value?.answers?.get(selectedAnswer)
        if (userAnswer == correctAnswer){
            _score.value = score.value?.plus(1)
        }
    }


    val generateCategoryList = listOf(
        CategoryItem(1, "COVID-19", "The pandemic disease COVID-19, is the latest in the Coronavirus family. It emerged in December 2019 in Wuhan, China. This quiz will test and improve your knowledge on the pandemic."),
        CategoryItem(2, "CHOLERA","need"),
        CategoryItem(3, "INFLUENZA", "need")
    )

    private val questions = listOf(
        Question("What is the most common symptoms?",1, listOf("Fever, Cough, Tiredness", "Headache & diarrhea", "Cough & Vomiting"), "Fever, Cough, Tiredness"),
        Question("How is the test done?",1, listOf("DNA Sample", "Urine Sample", "Mucus Sample"), "Mucus Sample"),
        Question("How does it spread?",1, listOf("Droplets through nose and mouth", "Drinking contaminated water", "Touching Blood"), "Droplets through nose and mouth"),
        Question("How long can it take to show symptoms?",1, listOf("up to 3 days", "up to 14 days", "up to 30 days"), "up to 14 days"),
        Question("What is a safe distance to keep from others?",1, listOf("at least 1 meter", "at least 1.5 meters", "at least 2 meters"), "at least 1 meter"),

        Question("blash ?",2, listOf("Fever, Cough, Tiredness", "Headache & diarrhea", "Cough & Vomiting"), "Fever, Cough, Tiredness"),
        Question("How is the test done?",2, listOf("DNA Sample", "Urine Sample", "Mucus Sample"), "Mucus Sample"),
        Question("How does it spread?",2, listOf("Droplets through nose and mouth", "Drinking contaminated water", "Touching Blood"), "Droplets through nose and mouth"),
        Question("How long can it take to show symptoms?",2, listOf("up to 3 days", "up to 14 days", "up to 30 days"), "up to 14 days"),
        Question("What is a safe distance to keep from others?",2, listOf("at least 1 meter", "at least 1.5 meters", "at least 2 meters"), "at least 1 meter"),

        Question("What is the most common symptoms?",3, listOf("Fever, Cough, Tiredness", "Headache & diarrhea", "Cough & Vomiting"), "Fever, Cough, Tiredness"),
        Question("How is the test done?",3, listOf("DNA Sample", "Urine Sample", "Mucus Sample"), "Mucus Sample"),
        Question("How does it spread?",3, listOf("Droplets through nose and mouth", "Drinking contaminated water", "Touching Blood"), "Droplets through nose and mouth"),
        Question("How long can it take to show symptoms?",3, listOf("up to 3 days", "up to 14 days", "up to 30 days"), "up to 14 days"),
        Question("What is a safe distance to keep from others?",3, listOf("at least 1 meter", "at least 1.5 meters", "at least 2 meters"), "at least 1 meter")
    )



}