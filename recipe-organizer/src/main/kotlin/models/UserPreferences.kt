package recipeorganizer.models

// UserPreferences.kt - Represents user preferences for meal planning
@Suppress("UNUSED")
data class UserPreferences(
    val preferredTags: List<String> = emptyList(), // breakfast, lunch, vegetarian, etc.
    val maxCookingTime: Int = 60, // minutes
    val servingSize: Int = 2,
    val restrictions: Set<String> = emptySet() // allergies, dietary restrictions
) {
    // Checks whether a recipe fits the current preference rules.
    @Suppress("UNUSED")
    fun matches(recipe: Recipe): Boolean {
        val hasPreferredTag = preferredTags.isEmpty() || recipe.tags.any { it in preferredTags }
        val withinTimeLimit = recipe.cookingTime <= maxCookingTime
        val noRestrictions = restrictions.isEmpty() || !recipe.tags.any { it in restrictions }

        return hasPreferredTag && withinTimeLimit && noRestrictions
    }
}
