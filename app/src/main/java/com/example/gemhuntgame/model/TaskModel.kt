package com.example.gemhuntgame.model

data class TaskRecord(
    val taskType: String,
    val trialCount: Int,
    val correctCount: Int,
    val finishTime: Long
)