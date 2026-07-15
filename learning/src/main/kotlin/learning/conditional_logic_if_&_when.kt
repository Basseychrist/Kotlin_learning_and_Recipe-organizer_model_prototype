package learning

// Demonstrates if/else and when branching with a sample score.
fun main() {
    // Read score from user to make branching meaningful; falls back to 85 if input missing/invalid
    print("Enter a score (0-100) or press Enter to use sample: ")
        val score = readlnOrNull()?.toIntOrNull() ?: 85

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