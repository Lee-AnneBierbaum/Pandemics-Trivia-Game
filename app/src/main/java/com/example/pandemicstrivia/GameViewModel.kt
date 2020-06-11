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
        _totalQuestions.value = 5
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
        CategoryItem(2, "CHOLERA","The Cholera pandemic is one that has been recurring multiple times throughout history and is still present in many people's daily lives. It is part of the longest running Pandemic of all time. Let's explore what this pandemic entails."),
        CategoryItem(3, "SPANISH FLU", "The Spanish Flu - part of the H1N1 family - is known for having the biggest mortality rate in our recent history of pandemics. It happened in 1918. Let's test your knowledge on this historical flu pandemic.")
    )

    private val questions = listOf(
        Question("What is the most common symptoms?",1, listOf("Fever, Cough, Tiredness", "Headache & diarrhea", "Cough & Vomiting"), "Fever, Cough, Tiredness"),
        Question("How is the test done?",1, listOf("DNA Sample", "Urine Sample", "Mucus Sample"), "Mucus Sample"),
        Question("How does it spread?",1, listOf("Droplets through nose and mouth", "Drinking contaminated water", "Touching Blood"), "Droplets through nose and mouth"),
        Question("How long can it take to show symptoms?",1, listOf("up to 3 days", "up to 14 days", "up to 30 days"), "up to 14 days"),
        Question("What is a safe distance to keep from others?",1, listOf("at least 1 meter", "at least 1.5 meters", "at least 2 meters"), "at least 1 meter"),

        Question("What causes Cholera?",2, listOf("Droplets through nose and mouth", "Digesting contaminated water", "Touching an infected person"), "Digesting contaminated water"),
        Question("The main symptom of Cholera?",2, listOf("Diarrhea and dehydration", "Headache & diarrhea", "Cough & Vomiting"), "Diarrhea and dehydration"),
        Question("How long does it take to show symptoms after infected?",2, listOf("from 24 hours until 3 days", "from 5 hours until 12 hours", "from 12 hours until 5 days"), "from 12 hours until 5 days"),
        Question("How many cases are there each year(estimation)?",2, listOf("1.3 - 4 million", "2 - 4 million", "3 million"), "1.3 - 4 million"),
        Question("How can you prevent the Cholera disease?",2, listOf("Keep a distance from others", "Have clean water and sanitation", "Stay at home"), "Have clean water and sanitation"),

        Question("How many people is speculated to have had the virus?",3, listOf("half of the world's population", "a third of the world's population", "a fifth of the world's population"), "a third of the world's population"),
        Question("How many people died in the pandemic (estimation)?",3, listOf("60 million", "30 million", "50 million"), "50 million"),
        Question("What did they use for prevention and treatment?",3, listOf("Vaccine and Antibiotics", "Isolation and Quarantine", "Everyday Pharmaceutical medication"), "Isolation and Quarantine"),
        Question("Where was the pandemic discovered?",3, listOf("In the US Navy", "In a market in Spain", "In Nigeria"), "In the US Navy"),
        Question("What was a unique classification of this pandemic?",3, listOf("High mortality rate for people between 20 - 40 years", "High mortality rate for people under 10 years", "High mortality rate for people over 60 years"), "High mortality rate for people between 20 - 40 years")
    )



}