package recipeorganizer.models

/**
 * MockData.kt - Provides mock Recipe and Ingredient data for development and testing
 *
 * Contains:
 * - sampleIngredients: Reusable ingredient objects for consistency
 * - sampleRecipesMock: Pre-built recipes using the sample ingredients
 *
 * This data is used in the interactive console application to demonstrate
 * recipe queries, filtering, and scaling operations.
 */

val sampleIngredients = listOf(
    Ingredient("pasta", 400.0, "grams"),
    Ingredient("tomato", 500.0, "grams"),
    Ingredient("chicken", 300.0, "grams"),
    Ingredient("rice", 200.0, "grams"),
    Ingredient("eggs", 3.0, "pieces"),
    Ingredient("cheese", 50.0, "grams")
)

val sampleRecipesMock = listOf(
    Recipe(
        id = "r1",
        name = "Tomato Pasta",
        ingredients = listOf(sampleIngredients[0], sampleIngredients[1]),
        cookingTime = 20,
        servings = 2,
        difficulty = "Easy",
        tags = listOf("lunch", "vegetarian")
    ),
    Recipe(
        id = "r2",
        name = "Chicken Stir Fry",
        ingredients = listOf(sampleIngredients[2], sampleIngredients[3]),
        cookingTime = 25,
        servings = 3,
        difficulty = "Medium",
        tags = listOf("dinner")
    ),
    Recipe(
        id = "r3",
        name = "Omelette",
        ingredients = listOf(sampleIngredients[4], sampleIngredients[5]),
        cookingTime = 10,
        servings = 1,
        difficulty = "Easy",
        tags = listOf("breakfast", "vegetarian")
    )
)
