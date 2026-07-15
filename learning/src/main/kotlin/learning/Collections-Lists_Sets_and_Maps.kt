@file:Suppress("SpellCheckingInspection", "UNUSED_VARIABLE")
package learning

// Demonstrates list, set, and map operations in Kotlin.
@Suppress("UNUSED_VARIABLE")
fun main() {
    // ---- 1. LISTS (Ordered, allows duplicates) ----
    val immutableList = listOf("Kotlin", "Java", "Kotlin") // Cannot add/remove elements
    val mutableList = mutableListOf("Next.js", "Supabase")
    mutableList.add("Cloudflare Pages") // Modifiable

    // Use immutableList to avoid "never used" warnings and show duplicate preservation
    println("Immutable list (with duplicates preserved): ${immutableList.joinToString()}")

    println("--- List Practice ---")
    for (tech in mutableList) {
        println("Tech stack item: $tech")
    }

    // ---- 2. SETS (Unique elements only, unordered) ----
    val uniqueSet = setOf("Data Science", "Machine Learning", "Data Science")
    println("\n--- Set Practice (Duplicates removed automatically) ---")
    println("Set items: $uniqueSet") // Output: [Data Science, Machine Learning]

    // ---- 3. MAPS (Key-Value pairs) ----
    val userRoles = mutableMapOf(
        "admin" to "Full Access",
        "developer" to "Write Access",
        "guest" to "Read Only"
    )

    println("\n--- Map Practice ---")
    userRoles["lead"] = "All Access" // Adding a new pair
    userRoles.forEach { (role, permission) ->
        println("Role: $role -> Permission: $permission")
    }
}
