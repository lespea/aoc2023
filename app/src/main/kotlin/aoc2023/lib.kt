package aoc2023

import kotlin.io.*
import kotlin.streams.toList

data class Input(val lines: List<String>)

object Utils {
    fun input(day: Int): Input {
        return this.javaClass.getResourceAsStream("/day$day.txt")?.bufferedReader()?.use {
            Input(it.lines().toList())
        }
                ?: throw RuntimeException("Bad day $day")
    }
}
