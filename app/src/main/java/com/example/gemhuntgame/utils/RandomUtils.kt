package com.example.gemhuntgame.utils

import kotlin.random.Random

object RandomUtils {
    fun getRandomBoolean(): Boolean {
        return Random.nextBoolean()
    }

    fun <T> shuffleList(list: List<T>): List<T> {
        return list.shuffled(Random)
    }

    fun getRandomInt(min: Int, max: Int): Int {
        return Random.nextInt(min, max + 1)
    }
}