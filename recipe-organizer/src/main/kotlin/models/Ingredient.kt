package recipeorganizer

// Ingredient.kt - Represents an ingredient with quantity and unit
data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String // grams, cups, ml, pieces, etc.
) {
    // Formats the ingredient as a readable quantity-name string.
    override fun toString(): String {
        return "$quantity $unit $name"
    }
}
