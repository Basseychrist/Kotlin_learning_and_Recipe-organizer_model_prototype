package recipeorganizer.models

/**
 * Ingredient.kt - Represents a single ingredient with quantity and measurement unit
 *
 * Data class provides:
 * - Auto-generated equals() for value comparison
 * - Auto-generated hashCode() for use in collections
 * - Auto-generated copy() for creating modified copies
 * - Readable toString() output
 */
data class Ingredient(
    val name: String,
    val quantity: Double,
    val unit: String // grams, cups, ml, pieces, etc.
) {
    /**
     * Formats the ingredient as a readable "quantity unit name" string.
     * Used when displaying ingredient lists to users.
     */
    override fun toString(): String = "$quantity $unit $name"
}
