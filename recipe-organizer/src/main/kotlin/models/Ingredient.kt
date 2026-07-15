package recipeorganizer.models

// Ingredient.kt - Represents an ingredient with quantity and unit
@Suppress("UNUSED_PARAMETER")
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
