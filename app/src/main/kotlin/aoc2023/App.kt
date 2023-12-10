package aoc2023

val day: Int = 1

fun main() {
    val runner: Day =
            when (day) {
                1 -> Day1()
                else -> throw Exception("Bad day")
            }

    val c2 = runner.check2()

    runner.check1().validate(runner::part1, 1)
    c2?.validate(runner::part2, 2)

    val input = Utils.input(day)

    println("Part 1: ${runner.part1(input)}")
    if (c2 != null) {
        println("Part 2: ${runner.part2(input)}")
    }
}

data class TestData(val input: String, val want: String) {
    fun validate(fn: (Input) -> String, num: Int) {
        val got = fn(Input(this.input.trimIndent().split('\n')))
        require(got == this.want) { "Check failed for #$num :: $got != ${this.want}" }
    }
}

interface Day {
    fun check1(): TestData
    fun check2(): TestData?

    fun part1(input: Input): String
    fun part2(input: Input): String
}
