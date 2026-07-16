package recipeorganizer

import recipeorganizer.models.Ingredient
import recipeorganizer.models.Recipe
import recipeorganizer.models.sampleRecipesMock
import recipeorganizer.services.filterRecipesByIngredients
import recipeorganizer.services.scaleRecipe
import recipeorganizer.services.getScaledIngredientsDisplay

/**
 * Main.kt - Interactive Recipe Organizer Console Application
 *
 * This application demonstrates Kotlin fundamentals through a recipe management system:
 * - While loops and when conditional statements for menu-driven UI
 * - Data classes for modeling domain objects
 * - Collections (List, Set, Map) for data organization
 * - Functional operations (filter, map, groupBy)
 * - Mutable vs immutable collections
 */

fun main() {
    println("Recipe Organizer - Interactive Console")
    val recipes = sampleRecipesMock.toMutableList()

    var running = true
    while (running) {
        printMenu()
        print("Enter choice: ")
        when (readlnOrNull()?.trim()?.lowercase()) {
            "1" -> listRecipes(recipes)
            "2" -> viewRecipe(recipes)
            "3" -> showQuickRecipes(recipes)
            "4" -> showIngredientTotals(recipes)
            "5" -> addSampleRecipe(recipes)
            "6" -> addCustomRecipe(recipes)
            "7" -> filterByIngredient(recipes)
            "8" -> scaleRecipeServings(recipes)
            "q", "quit", "exit" -> {
                println("Exiting. Goodbye!")
                running = false
            }
            else -> println("Invalid choice. Enter 1-8 or 'q' to quit.")
        }
        println()
    }
}

/**
 * Displays the main menu options.
 */
private fun printMenu() {
    println("\nMenu:")
    println("1) List all recipes")
    println("2) View recipe details by ID")
    println("3) Show quick recipes (by max cooking time)")
    println("4) Show ingredient totals")
    println("5) Add a sample recipe (demo)")
    println("6) Add your own recipe")
    println("7) Filter recipes by ingredient")
    println("8) Scale recipe servings")
    println("q) Quit")
}

/**
 * Lists all recipes with ID and cooking time.
 */
private fun listRecipes(recipes: List<Recipe>) {
    println("\nRecipes:")
    recipes.forEach { println("- ${it.id}: ${it.name} (${it.cookingTime} mins)") }
}

/**
 * Displays detailed information for a recipe by ID.
 */
private fun viewRecipe(recipes: List<Recipe>) {
    print("Enter recipe ID: ")
    val id = readlnOrNull()?.trim() ?: ""
    val found = recipes.find { it.id == id }
    if (found != null) {
        println("\n$found")
    } else {
        println("Recipe with ID '$id' not found.")
    }
}

/**
 * Filters and displays recipes with cooking time under a specified limit.
 * Demonstrates functional operations (filter) and user input handling.
 */
private fun showQuickRecipes(recipes: List<Recipe>) {
    print("Enter max cooking time in minutes (default 20): ")
    val input = readlnOrNull()?.trim()
    val max = input?.toIntOrNull() ?: 20
    val quick = recipes.filter { it.cookingTime <= max }
    if (quick.isEmpty()) {
        println("No recipes under $max minutes.")
    } else {
        println("\nQuick recipes (<= $max mins):")
        quick.forEach { println("- ${it.id}: ${it.name} (${it.cookingTime} mins)") }
    }
}

/**
 * Calculates and displays the total quantity of each ingredient across all recipes.
 * Demonstrates flatMap and groupBy operations on collections.
 */
private fun showIngredientTotals(recipes: List<Recipe>) {
    val totals = recipes
        .flatMap { it.ingredients }              // Flatten nested ingredient lists
        .groupBy { it.name }                      // Group by ingredient name
        .mapValues { (_, items) -> items.sumOf { it.quantity } }  // Sum quantities per group

    println("\nIngredient totals across all recipes:")
    totals.forEach { (name, qty) -> println("- $name: $qty") }
}

/**
 * Adds a sample recipe to the mutable list.
 * Demonstrates mutable collections and demonstrates adding elements at runtime.
 */
private fun addSampleRecipe(recipes: MutableList<Recipe>) {
    val new = Recipe(
        id = "r${recipes.size + 1}",
        name = "Quick Salad",
        ingredients = listOf(
            Ingredient("lettuce", 100.0, "grams"),
            Ingredient("tomato", 50.0, "grams")
        ),
        cookingTime = 5,
        servings = 1,
        difficulty = "Easy",
        tags = listOf("lunch", "vegetarian")
    )
    recipes.add(new)
    println("Added sample recipe: ${new.id} - ${new.name}")
}

/**
 * Prompts user to create and add a custom recipe.
 * Allows users to input recipe details and ingredients interactively.
 */
private fun addCustomRecipe(recipes: MutableList<Recipe>) {
    println("\n--- Add Your Own Recipe ---")
    
    // Get recipe name
    print("Recipe name: ")
    val name = readlnOrNull()?.trim() ?: ""
    if (name.isEmpty()) {
        println("Recipe name cannot be empty.")
        return
    }
    
    // Get ingredients
    val ingredients = mutableListOf<Ingredient>()
    println("Add ingredients (type 'done' when finished):")
    while (true) {
        print("  Ingredient name (or 'done'): ")
        val ingName = readlnOrNull()?.trim() ?: ""
        if (ingName.lowercase() == "done") break
        if (ingName.isEmpty()) {
            println("  Ingredient name cannot be empty.")
            continue
        }
        
        print("  Quantity: ")
        val quantity = readlnOrNull()?.trim()?.toDoubleOrNull()
        if (quantity == null || quantity <= 0) {
            println("  Invalid quantity. Please enter a positive number.")
            continue
        }
        
        print("  Unit (grams, cups, ml, pieces, etc.): ")
        val unit = readlnOrNull()?.trim() ?: ""
        if (unit.isEmpty()) {
            println("  Unit cannot be empty.")
            continue
        }
        
        ingredients.add(Ingredient(ingName, quantity, unit))
        println("  ✓ Added $quantity $unit of $ingName")
    }
    
    if (ingredients.isEmpty()) {
        println("Recipe must have at least one ingredient.")
        return
    }
    
    // Get cooking time
    print("Cooking time (minutes): ")
    val cookingTime = readlnOrNull()?.trim()?.toIntOrNull()
    if (cookingTime == null || cookingTime <= 0) {
        println("Invalid cooking time. Please enter a positive number.")
        return
    }
    
    // Get servings
    print("Number of servings: ")
    val servings = readlnOrNull()?.trim()?.toIntOrNull()
    if (servings == null || servings <= 0) {
        println("Invalid servings. Please enter a positive number.")
        return
    }
    
    // Get difficulty
    print("Difficulty (Easy/Medium/Hard): ")
    val difficulty = readlnOrNull()?.trim() ?: ""
    if (difficulty.isEmpty()) {
        println("Difficulty cannot be empty.")
        return
    }
    
    // Get tags
    print("Tags (comma-separated, e.g., breakfast,lunch,vegetarian): ")
    val tags = readlnOrNull()?.trim()?.split(",")?.map { it.trim() } ?: emptyList()
    
    // Create and add recipe
    val newRecipe = Recipe(
        id = "r${recipes.size + 1}",
        name = name,
        ingredients = ingredients,
        cookingTime = cookingTime,
        servings = servings,
        difficulty = difficulty,
        tags = tags
    )
    
    recipes.add(newRecipe)
    println("\n✓ Recipe added successfully!")
    println(newRecipe)
}

/**
 * Filters recipes by ingredient using the RecipeService.
 * Demonstrates how business logic is separated into service functions.
 */
private fun filterByIngredient(recipes: List<Recipe>) {
    print("Enter ingredient name to filter by: ")
    val ingredient = readlnOrNull()?.trim() ?: ""
    if (ingredient.isEmpty()) {
        println("No ingredient provided.")
        return
    }
    
    val filtered = filterRecipesByIngredients(recipes, listOf(ingredient))
    if (filtered.isEmpty()) {
        println("No recipes found with ingredient '$ingredient'.")
    } else {
        println("\nRecipes with '$ingredient':")
        filtered.forEach { println("- ${it.id}: ${it.name}") }
    }
}

/**
 * Scales a recipe's ingredients to a target serving size.
 * Demonstrates the scaleRecipe function and data class copy() method.
 */
private fun scaleRecipeServings(recipes: List<Recipe>) {
    print("Enter recipe ID to scale: ")
    val id = readlnOrNull()?.trim() ?: ""
    val recipe = recipes.find { it.id == id }
    if (recipe == null) {
        println("Recipe with ID '$id' not found.")
        return
    }
    
    print("Enter target serving size: ")
    val targetServings = readlnOrNull()?.trim()?.toIntOrNull()
    if (targetServings == null || targetServings <= 0) {
        println("Invalid serving size.")
        return
    }
    
    println("\nOriginal recipe serves ${recipe.servings}:")
    recipe.ingredients.forEach { println("  • ${it.quantity} ${it.unit} ${it.name}") }
    
    println("\nScaled to $targetServings servings:")
    println(getScaledIngredientsDisplay(recipe, targetServings))
}
