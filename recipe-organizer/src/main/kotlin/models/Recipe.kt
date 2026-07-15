package recipeorganizer

// Recipe.kt - Represents a single recipe with ingredients and metadata
data class Recipe(
    val id: String,
    val name: String,
    val ingredients: List<Ingredient>,
    val cookingTime: Int, // in minutes
    val servings: Int,
    val difficulty: String, // Easy, Medium, Hard
    val tags: List<String> // breakfast, lunch, dinner, vegetarian, etc.
) {
    // Builds a readable multi-line summary of the recipe.
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
