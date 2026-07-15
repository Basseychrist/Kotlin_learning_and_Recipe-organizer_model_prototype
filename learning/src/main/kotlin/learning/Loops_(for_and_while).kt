package learning

// Demonstrates looping with a range-based for loop and a while loop.
fun main() {
    // 1. For loop using a range (1 to 5 inclusive)
    println("Counting up:")
    for (i in 1..5) {
        println("Number: $i")
    }

    // 2. While loop
    var energy = 3
    println("\nWorking:")
    while (energy > 0) {
        println("Coding... Energy level: $energy")
        energy--
    }
    println("Out of energy!")
}