package recipeorganizer.models

/**
 * Recipe.kt - Represents a complete recipe with metadata and ingredients
 *
 * Data class provides immutable recipe objects with:
 * - Auto-generated equals() for value-based comparison
 * - Auto-generated copy() for creating modified recipes (used in scaling)
 * - Custom toString() for readable, multi-line display
 */
@Suppress("UNUSED_PARAMETER")
data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>,
    val cookingTime: Int, // in minutes
    val servings: Int,
    val difficulty: String, // Easy, Medium, Hard
    val tags: List<String> // breakfast, lunch, dinner, vegetarian, etc.
) {
    /**
     * Builds a readable multi-line summary of the recipe with formatted output.
     * Displays recipe metadata and ingredient list in a tree-like structure.
     */
    override fun toString(): String {
        val ingredientList = ingredients.joinToString("\n    • ") { it.toString() }
        return """
            |Recipe: $name (ID: $id)
            |├─ Cooking Time: $cookingTime mins | Servings: $servings | Difficulty: $difficulty
            |├─ Tags: ${tags.joinToString(", ")}
            |└─ Ingredients:
            |    • $ingredientList
        """.trimMargin()
    }
}
