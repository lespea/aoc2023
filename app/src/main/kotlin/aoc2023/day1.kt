package aoc2023

import kotlin.text.digitToInt

class Day1 : Day {

    override fun check1(): TestData {
        return TestData(
                """
                    1abc2
                    pqr3stu8vwx
                    a1b2c3d4e5f
                    treb7uchet
                """,
                "142",
        )
    }

    override fun check2(): TestData? {
        return TestData(
                """
                    two1nine
                    eightwothree
                    abcone2threexyz
                    xtwone3four
                    4nineeightseven2
                    zoneight234
                    7pqrstsixteen
                """,
                "281"
        )
    }

    override fun part1(input: Input): String {
        return input.lines.map(::lineToNum).sum().toString()
    }

    override fun part2(input: Input): String {
        return input.lines.map(::lineToNumRe).sum().toString()
    }

    fun lineToNum(line: String): Int {
        val n1 =
                line.find { it.isDigit() }?.digitToInt() ?: throw Exception("Invalid line: '$line'")

        val n2 =
                line.findLast { it.isDigit() }?.digitToInt()
                        ?: throw Exception("Invalid line: '$line'")

        return numToStr(n1, n2)
    }

    private val maps =
            mapOf<String, Int>(
                    "one" to 1,
                    "two" to 2,
                    "three" to 3,
                    "four" to 4,
                    "five" to 5,
                    "six" to 6,
                    "seven" to 7,
                    "eight" to 8,
                    "nine" to 9,
            )

    data class ReInfo(val line: Regex, val part: Regex)

    private val re: ReInfo =
            {
                val daystr = maps.keys.joinToString(separator = "|")
                val part = "(?:\\d|$daystr)"
                val r = "^.*?($part).*($part)(?!.*$part).*$".toRegex()
                ReInfo(r, "($part)".toRegex())
            }()

    private fun partToNum(part: String): Int {
        return maps.get(part) ?: part.toIntOrNull() ?: throw Exception("Invalid part $part")
    }

    fun lineToNumRe(line: String): Int {
        val (s1, s2) = re.line.find(line)?.destructured ?: return single(line)
        // val (s1, s2) = re.line.find(line)?.destructured ?: return 0

        return numToStr(partToNum(s1), partToNum(s2))
    }

    private fun single(line: String): Int {
        val (ns) = re.part.find(line)?.destructured ?: throw Exception("Invalid line: '$line'")
        val n = partToNum(ns)
        return numToStr(n, n)
    }

    private fun numToStr(n1: Int, n2: Int): Int {
        return n1 * 10 + n2
    }
}
