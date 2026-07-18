package recipeorganizer.services

import recipeorganizer.models.Recipe

/**
 * RecipeService.kt - Business logic for recipe filtering and scaling operations
 *
 * This service provides utility functions for:
 * - Filtering recipes by ingredient matches
 * - Scaling recipe servings with proportional ingredient adjustments
 * - Displaying scaled ingredient information
 */

/**
 * Filters recipes by ingredient matches.
 * Returns recipes that contain ANY of the specified ingredient names (case-insensitive).
 *
 * @param recipes List of recipes to filter
 * @param ingredientNames Ingredient names to search for (will be lowercased for comparison)
 * @return List of recipes containing at least one matching ingredient
 *
 * Example: filterRecipesByIngredients(recipes, listOf("tomato", "cheese"))
 *          returns recipes with tomato OR cheese
 */
fun filterRecipesByIngredients(recipes: List<Recipe>, ingredientNames: List<String>): List<Recipe> {
    if (ingredientNames.isEmpty()) return recipes
    
    val lowerNames = ingredientNames.map { it.trim().lowercase() }
    return recipes.filter { recipe ->
        recipe.ingredients.any { ingredient ->
            ingredient.name.lowercase() in lowerNames
        }
    }
}

/**
 * Scales a recipe to a target serving size.
 * Returns a new Recipe with quantities adjusted proportionally using the data class copy() method.
 *
 * @param recipe The recipe to scale
 * @param targetServings The desired number of servings (must be > 0)
 * @return A new Recipe with scaled ingredient quantities
 * @throws IllegalArgumentException if targetServings <= 0
 *
 * Example: scaleRecipe(pastaRecipe, 4) with original servings=2
 *          doubles all ingredient quantities
 */
fun scaleRecipe(recipe: Recipe, targetServings: Int): Recipe {
    require(targetServings > 0) { "Target servings must be greater than 0" }
    
    val scaleFactor = targetServings.toDouble() / recipe.servings
    
    // Use data class copy() to create a new Recipe with modified ingredients
    val scaledIngredients = recipe.ingredients.map { ingredient ->
        ingredient.copy(quantity = ingredient.quantity * scaleFactor)
    }
    
    return recipe.copy(
        ingredients = scaledIngredients,
        servings = targetServings
    )
}

/**
 * Formats scaled recipe ingredients as a readable string.
 * Useful for displaying ingredient adjustments in the UI.
 *
 * @param recipe The recipe to scale
 * @param targetServings The desired number of servings
 * @return Formatted string of scaled ingredients (one per line)
 */
fun getScaledIngredientsDisplay(recipe: Recipe, targetServings: Int): String {
    val scaled = scaleRecipe(recipe, targetServings)
    return scaled.ingredients.joinToString("\n") { "  • ${it.quantity} ${it.unit} ${it.name}" }
}
