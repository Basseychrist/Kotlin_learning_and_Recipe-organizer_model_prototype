// Main.kt - Prototype demonstrating variable scope and collection manipulation

fun main() {
    println("╔════════════════════════════════════════════════════════════╗")
    println("║  Recipe Organizer & Smart Meal Planner - Prototype        ║")
    println("║  Testing Variable Scope & Collection Manipulation          ║")
    println("╚════════════════════════════════════════════════════════════╝\n")

    // ==================== PROTOTYPE: COLLECTIONS & SCOPE ====================
    
    // Create sample recipes (COLLECTION: List<Recipe>)
    val recipes = createSampleRecipes()
    println("✓ Created ${recipes.size} sample recipes\n")
    
    // Display all recipes
    println("─── All Recipes ───")
    recipes.forEach { recipe ->
        println("  • ${recipe.name} (${recipe.cookingTime} mins, ${recipe.difficulty})")
    }
    println()

    // ==================== SCOPE TEST 1: Lambda & Local Variables ====================
    println("─── Scope Test 1: Filter Quick Recipes (< 30 mins) ───")
    
    val maxTime = 30 // Local variable in main()
    val quickRecipes = recipes.filter { recipe ->
        // 'maxTime' is accessible here (closure from outer scope)
        recipe.cookingTime <= maxTime
    }
    
    println("Found ${quickRecipes.size} recipes under $maxTime minutes:")
    quickRecipes.forEach { println("  • ${it.name} - ${it.cookingTime} mins") }
    println()

    // ==================== SCOPE TEST 2: Map & Transformation ====================
    println("─── Scope Test 2: Extract Recipe Names (Map) ───")
    
    val recipeNames = recipes.map { it.name } // Transform List<Recipe> -> List<String>
    println("Recipe names (${recipeNames.size} total):")
    println("  ${recipeNames.joinToString(", ")}\n")

    // ==================== SCOPE TEST 3: Filter by Tag ===== ==================
    println("─── Scope Test 3: Filter by Tag (Set operations) ───")
    
    val allTags: Set<String> = recipes
        .flatMap { it.tags } // Flatten List<List<String>> -> List<String>
        .toSet()             // Convert to Set (removes duplicates)
    
    println("Available tags: ${allTags.joinToString(", ")}\n")
    
    val vegetarianRecipes = recipes.filter { "vegetarian" in it.tags }
    println("Vegetarian recipes (${vegetarianRecipes.size}):")
    vegetarianRecipes.forEach { println("  • ${it.name}") }
    println()

    // ==================== SCOPE TEST 4: Nested Scope - Ingredient Filtering ====================
    println("─── Scope Test 4: Find Recipes by Ingredient ───")
    
    val searchIngredient = "tomato"
    val recipesWithTomato = recipes.filter { recipe ->
        // Inner lambda scope: can access both 'recipe' parameter and outer 'searchIngredient'
        recipe.ingredients.any { ingredient ->
            // Doubly nested scope: accessing 'searchIngredient' from main() scope
            ingredient.name.lowercase().contains(searchIngredient.lowercase())
        }
    }
    
    println("Recipes containing '$searchIngredient':")
    recipesWithTomato.forEach { println("  • ${it.name}") }
    println()

    // ==================== SCOPE TEST 5: Grouping with Map (Dictionary) ====================
    println("─── Scope Test 5: Group Recipes by Difficulty (Map) ───")
    
    val recipesByDifficulty: Map<String, List<Recipe>> = recipes.groupBy { it.difficulty }
    
    recipesByDifficulty.forEach { (difficulty, recipeList) ->
        // 'difficulty' and 'recipeList' are local to forEach lambda
        println("  $difficulty: ${recipeList.map { it.name }.joinToString(", ")}")
    }
    println()

    // ==================== SCOPE TEST 6: Complex Collection Operations ====================
    println("─── Scope Test 6: Aggregate Ingredients (Scope + Collections) ───")
    
    // Create a weekly meal plan (testing collections within data structures)
    val weeklyPlan = WeeklyMealPlan(
        startDate = "2024-11-11",
        dailyPlans = listOf(
            MealPlan("2024-11-11", breakfast = recipes[0], lunch = recipes[1], dinner = recipes[2]),
            MealPlan("2024-11-12", breakfast = recipes[1], lunch = recipes[0], dinner = recipes[1]),
            MealPlan("2024-11-13", breakfast = recipes[0], lunch = recipes[2], dinner = recipes[0]),
            MealPlan("2024-11-14", breakfast = recipes[2], lunch = recipes[1], dinner = recipes[2]),
            MealPlan("2024-11-15", breakfast = recipes[1], lunch = recipes[0], dinner = recipes[1]),
            MealPlan("2024-11-16", breakfast = recipes[0], lunch = recipes[2], dinner = recipes[0]),
            MealPlan("2024-11-17", breakfast = recipes[2], lunch = recipes[1], dinner = recipes[2])
        )
    )
    
    val aggregatedIngredients = weeklyPlan.getAggregatedIngredients()
    println("Grocery list for the week:")
    aggregatedIngredients.forEach { (name, quantity) ->
        println("  • $name: $quantity units")
    }
    println()

    // ==================== SCOPE TEST 7: User Preferences Matching ====================
    println("─── Scope Test 7: Filter by User Preferences ───")
    
    val userPrefs = UserPreferences(
        preferredTags = listOf("lunch", "vegetarian"),
        maxCookingTime = 45,
        restrictions = setOf() // No restrictions
    )
    
    val matchingRecipes = recipes.filter { userPrefs.matches(it) }
    println("Recipes matching user preferences (lunch, vegetarian, < 45 mins):")
    matchingRecipes.forEach { 
        println("  • ${it.name} - Tags: ${it.tags.joinToString(", ")}")
    }
    println()

    // ==================== SCOPE TEST 8: Variable Shadowing ====================
    println("─── Scope Test 8: Variable Shadowing Example ───")
    
    val recipeName = "Pasta" // Outer scope variable
    println("Outer scope: recipeName = '$recipeName'")
    
    recipes.take(1).forEach { recipe ->
        val recipeName = recipe.name // Inner scope variable shadows outer
        println("Inner scope: recipeName = '$recipeName' (shadowed)")
    }
    
    println("Back to outer scope: recipeName = '$recipeName'\n")

    println("╔════════════════════════════════════════════════════════════╗")
    println("║  All scope and collection tests completed successfully!   ║")
    println("╚════════════════════════════════════════════════════════════╝")
}

// Helper function: Create sample recipes (demonstrates local scope)
fun createSampleRecipes(): List<Recipe> {
    return listOf(
        Recipe(
            id = "r1",
            name = "Tomato Pasta",
            ingredients = listOf(
                Ingredient("pasta", 400.0, "grams"),
                Ingredient("tomato", 500.0, "grams"),
                Ingredient("garlic", 3.0, "cloves"),
                Ingredient("olive oil", 50.0, "ml")
            ),
            cookingTime = 20,
            servings = 2,
            difficulty = "Easy",
            tags = listOf("lunch", "dinner", "vegetarian")
        ),
        Recipe(
            id = "r2",
            name = "Chicken Stir Fry",
            ingredients = listOf(
                Ingredient("chicken", 500.0, "grams"),
                Ingredient("bell pepper", 2.0, "pieces"),
                Ingredient("soy sauce", 100.0, "ml"),
                Ingredient("rice", 200.0, "grams")
            ),
            cookingTime = 25,
            servings = 3,
            difficulty = "Medium",
            tags = listOf("lunch", "dinner")
        ),
        Recipe(
            id = "r3",
            name = "Vegetable Soup",
            ingredients = listOf(
                Ingredient("carrot", 3.0, "pieces"),
                Ingredient("onion", 2.0, "pieces"),
                Ingredient("tomato", 400.0, "grams"),
                Ingredient("vegetable broth", 1000.0, "ml")
            ),
            cookingTime = 35,
            servings = 4,
            difficulty = "Easy",
            tags = listOf("lunch", "dinner", "vegetarian")
        ),
        Recipe(
            id = "r4",
            name = "Omelette",
            ingredients = listOf(
                Ingredient("eggs", 3.0, "pieces"),
                Ingredient("butter", 20.0, "grams"),
                Ingredient("cheese", 50.0, "grams")
            ),
            cookingTime = 10,
            servings = 1,
            difficulty = "Easy",
            tags = listOf("breakfast", "vegetarian")
        ),
        Recipe(
            id = "r5",
            name = "Grilled Fish",
            ingredients = listOf(
                Ingredient("fish fillet", 400.0, "grams"),
                Ingredient("lemon", 1.0, "piece"),
                Ingredient("olive oil", 30.0, "ml"),
                Ingredient("herbs", 10.0, "grams")
            ),
            cookingTime = 15,
            servings = 2,
            difficulty = "Medium",
            tags = listOf("lunch", "dinner")
        )
    )
}
