package com.example.gemhuntgame.model

data class DoorModel(
    val id: Int,
    val taskType: String,
    var isOpened: Boolean,
    val closeRes: Int,
    val openRes: Int
)