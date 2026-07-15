package recipeorganizer

import recipeorganizer.models.Ingredient
import recipeorganizer.models.Recipe

// Runs the recipe prototype and prints scope plus collection examples.
fun main() {
    println("Recipe Organizer Prototype")
    println("Scope + Collections demo\n")

    val recipes = sampleRecipes()
    val timeLimit = 20

    val quickRecipes = recipes.filter { it.cookingTime <= timeLimit }
    val recipeNames = recipes.map { it.name }
    val tags = recipes.flatMap { it.tags }.toSet()
    val byDifficulty = recipes.groupBy { it.difficulty }
    val ingredientTotals = recipes
        .flatMap { it.ingredients }
        .groupBy { it.name }
        .mapValues { (_, items) -> items.sumOf { it.quantity } }

    println("Outer scope limit: $timeLimit")
    quickRecipes.forEach { recipe ->
        val timeLimit = "shadowed by ${recipe.name}"
        println("Inner scope: $timeLimit")
    }

    println("\nAll recipes: ${recipeNames.joinToString()}")
    println("Unique tags: ${tags.joinToString()}")
    println("Quick recipes: ${quickRecipes.joinToString { it.name }}")
    println("By difficulty: ${byDifficulty.mapValues { (_, list) -> list.size }}")
    println("Ingredient totals: ${ingredientTotals}")
}

// Builds a small sample recipe set for the prototype demo.
private fun sampleRecipes(): List<Recipe> = listOf(
    Recipe(
        id = "r1",
        name = "Tomato Pasta",
        ingredients = listOf(
            Ingredient("pasta", 400.0, "grams"),
            Ingredient("tomato", 500.0, "grams")
        ),
        cookingTime = 20,
        servings = 2,
        difficulty = "Easy",
        tags = listOf("lunch", "vegetarian")
    ),
    Recipe(
        id = "r2",
        name = "Chicken Stir Fry",
        ingredients = listOf(
            Ingredient("chicken", 300.0, "grams"),
            Ingredient("rice", 200.0, "grams")
        ),
        cookingTime = 25,
        servings = 3,
        difficulty = "Medium",
        tags = listOf("dinner")
    ),
    Recipe(
        id = "r3",
        name = "Omelette",
        ingredients = listOf(
            Ingredient("eggs", 3.0, "pieces"),
            Ingredient("cheese", 50.0, "grams")
        ),
        cookingTime = 10,
        servings = 1,
        difficulty = "Easy",
        tags = listOf("breakfast", "vegetarian")
    )
)
