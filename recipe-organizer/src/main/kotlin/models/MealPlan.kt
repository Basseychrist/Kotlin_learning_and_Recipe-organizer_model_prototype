package recipeorganizer.models

// MealPlan.kt - Represents meal plans
@Suppress("UNUSED_PARAMETER")
data class MealPlan(
    val date: String, // YYYY-MM-DD
    val breakfast: Recipe? = null,
    val lunch: Recipe? = null,
    val dinner: Recipe? = null,
    val snacks: List<Recipe> = emptyList()
) {
    // Collects every recipe scheduled in a single day.
    fun getAllMeals(): List<Recipe> {
        return listOfNotNull(breakfast, lunch, dinner) + snacks
    }

    // Adds up the cooking time for all recipes in the day.
    @Suppress("UNUSED")
    fun getTotalCookingTime(): Int {
        return getAllMeals().sumOf { it.cookingTime }
    }
}

@Suppress("UNUSED")
data class WeeklyMealPlan(
    val startDate: String, // YYYY-MM-DD (Monday)
    val dailyPlans: List<MealPlan> // 7 days
) {
    // Flattens all daily meal plans into one recipe list.
    fun getAllRecipes(): List<Recipe> {
        return dailyPlans.flatMap { it.getAllMeals() }
    }

    // Removes duplicates so only unique recipes remain.
    @Suppress("UNUSED")
    fun getUniqueRecipes(): Set<Recipe> {
        return getAllRecipes().toSet()
    }

    // Totals ingredient quantities across the whole weekly plan.
    @Suppress("UNUSED")
    fun getAggregatedIngredients(): Map<String, Double> {
        val ingredientMap = mutableMapOf<String, Double>()
        for (recipe in getAllRecipes()) {
            for (ingredient in recipe.ingredients) {
                ingredientMap[ingredient.name] =
                    (ingredientMap[ingredient.name] ?: 0.0) + ingredient.quantity
            }
        }
        return ingredientMap
    }
}
