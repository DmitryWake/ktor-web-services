package com.example.model

import kotlinx.serialization.Serializable

@Serializable
data class WordsWithLetterModel(
    val letter: Char,
    val words: List<String>
)
