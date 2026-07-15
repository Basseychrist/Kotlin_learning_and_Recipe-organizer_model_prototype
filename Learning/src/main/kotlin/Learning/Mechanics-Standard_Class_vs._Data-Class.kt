// Holds project data without auto-generated equality or string helpers.
class StandardProject(val title: String, val tasks: Int)

// Uses a data class to get readable output, value equality, and copy support.
data class DataProject(val title: String, val tasks: Int)

// Compares a standard class with a data class.
fun main() {
    val std1 = StandardProject("Smart Budget", 5)
    val std2 = StandardProject("Smart Budget", 5)

    val data1 = DataProject("Smart Budget", 5)
    val data2 = DataProject("Smart Budget", 5)

    println("--- Mechanics Comparison ---")

    // 1. Behavior of toString()
    // Standard class prints the memory reference pointer. Data class prints actual readable properties.
    println("Standard class toString(): $std1")
    println("Data class toString():     $data1")

    // 2. Structural Equality (Using == calls the equals() method)
    // Standard class compares memory references (returns false even with identical values).
    // Data class compares the actual values inside the properties (returns true).
    println("\nAre standard classes equal? ${std1 == std2}") // false
    println("Are data classes equal?     ${data1 == data2}") // true

    // 3. The copy() Utility
    // Data classes let you easily clone an object while altering specific values.
    val updatedDataProject = data1.copy(tasks = 8)
    println("\nCopied project with new task count: $updatedDataProject")
}
