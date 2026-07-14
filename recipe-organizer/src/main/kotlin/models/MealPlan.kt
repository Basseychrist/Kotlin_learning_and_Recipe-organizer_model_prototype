// MealPlan.kt - Represents meal plans
data class MealPlan(
    val date: String, // YYYY-MM-DD
    val breakfast: Recipe? = null,
    val lunch: Recipe? = null,
    val dinner: Recipe? = null,
    val snacks: List<Recipe> = emptyList()
) {
    fun getAllMeals(): List<Recipe> {
        return listOfNotNull(breakfast, lunch, dinner) + snacks
    }

    fun getTotalCookingTime(): Int {
        return getAllMeals().sumOf { it.cookingTime }
    }
}

data class WeeklyMealPlan(
    val startDate: String, // YYYY-MM-DD (Monday)
    val dailyPlans: List<MealPlan> // 7 days
) {
    fun getAllRecipes(): List<Recipe> {
        return dailyPlans.flatMap { it.getAllMeals() }
    }

    fun getUniqueRecipes(): Set<Recipe> {
        return getAllRecipes().toSet()
    }

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
