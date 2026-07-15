package learning

// Returns a greeting string for the given user name.
fun greetUser(name: String): String {
    return "Hello, $name! Welcome back."
}

// Returns the square of the supplied number.
fun square(number: Int) = number * number

// Shows how to call both a normal function and a single-expression function.
fun main() {
    val greeting = greetUser("Developer")
    println(greeting)

    val result = square(5)
    println("5 squared is: $result")
}