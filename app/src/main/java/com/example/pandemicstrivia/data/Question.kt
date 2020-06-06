package com.example.pandemicstrivia.data

data class Question (
    val question: String,
    val categoryId: Int,
    val answers: List<String>,
    val correctAnswer: String
    //change later correctAnswer to an integer
)