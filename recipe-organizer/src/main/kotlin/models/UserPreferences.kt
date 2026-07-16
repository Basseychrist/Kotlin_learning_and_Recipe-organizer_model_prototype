package recipeorganizer.models

/**
 * UserPreferences.kt - Stores user preferences for meal planning filters
 *
 * Data class that encapsulates user dietary preferences and restrictions.
 * The matches() method demonstrates functional programming with collection operations.
 */
@Suppress("UNUSED")
data class UserPreferences(
    val preferredTags: List<String> = emptyList(), // breakfast, lunch, vegetarian, etc.
    val maxCookingTime: Int = 60, // minutes
    val servingSize: Int = 2,
    val restrictions: Set<String> = emptySet() // allergies, dietary restrictions
) {
    /**
     * Checks whether a recipe matches the current preference rules.
     *
     * Returns true if:
     * - User has no preferred tags, OR recipe has at least one preferred tag
     * - AND recipe cooking time is within maxCookingTime limit
     * - AND recipe has no tags that match user restrictions
     *
     * @param recipe The recipe to check against preferences
     * @return True if recipe matches all preference criteria
     */
    @Suppress("UNUSED")
    fun matches(recipe: Recipe): Boolean {
        // Empty preferred tags = accept all recipes; otherwise check for at least one match
        val hasPreferredTag = preferredTags.isEmpty() || recipe.tags.any { it in preferredTags }
        
        // Recipe must be within the cooking time limit
        val withinTimeLimit = recipe.cookingTime <= maxCookingTime
        
        // Empty restrictions = no restrictions; otherwise reject if recipe has restriction tags
        val noRestrictions = restrictions.isEmpty() || !recipe.tags.any { it in restrictions }

        return hasPreferredTag && withinTimeLimit && noRestrictions
    }
}
