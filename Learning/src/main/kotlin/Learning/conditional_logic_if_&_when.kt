package Learning

// Demonstrates if/else and when branching with a sample score.
fun main() {
    val score = 85

    // 1. Classic if-else
    if (score >= 50) {
        println("Passed!")
    } else {
        println("Try again!")
    }

    // 2. Powerful 'when' statement
    when (score) {
        100 -> println("Perfect score!")
        in 80..99 -> println("Great job, an A!")
        else -> println("Keep practicing!")
    }
}