# Recipe Organizer & Smart Meal Planner - Prototype

A console-based Kotlin learning project demonstrating core language concepts through an interactive recipe management system.

## Features

✅ **Current Implementation**
- 📚 **Recipe Display** - View all recipes with details (ID, name, cooking time, difficulty)
- 🔍 **Smart Filtering** - Filter recipes by:
  - Maximum cooking time
  - Ingredient name matches (case-insensitive)
  - All ingredients present
- 🍽️ **Serving Size Scaling** - Adjust recipe quantities for different serving sizes
- 📊 **Ingredient Totals** - Calculate total quantities across all recipes
- ➕ **Add Recipes** - Add new recipes to the collection at runtime
- 💾 **Mock Data** - Pre-built sample recipes for demonstration

## Project Structure

```
recipe-organizer/
├── src/main/kotlin/
│   ├── Main.kt                      # Interactive console loop (while/when)
│   ├── models/
│   │   ├── Recipe.kt               # Recipe data class with custom toString()
│   │   ├── Ingredient.kt           # Ingredient data class
│   │   ├── UserPreferences.kt      # User preferences & filtering logic
│   │   └── MockData.kt             # Sample recipe data
│   └── services/
│       └── RecipeService.kt        # Business logic (filtering, scaling)
└── README.md
```

## Getting Started

### Prerequisites
- Kotlin 1.8+
- Java 11+
- IntelliJ IDEA (recommended) or any Kotlin-compatible IDE

### Running the Application

**Using Gradle:**
```bash
gradle run
```

**Using IntelliJ:**
1. Open the project in IntelliJ IDEA
2. Right-click `Main.kt` → **Run 'MainKt'**

**Using kotlinc manually:**
```bash
kotlinc -d out src/main/kotlin/**/*.kt src/main/kotlin/*.kt
java -cp out MainKt
```

## Usage

Run the application and interact with the menu:

```
Menu:
1) List all recipes
2) View recipe details by ID
3) Show quick recipes (by max cooking time)
4) Show ingredient totals
5) Add a sample recipe (demo)
6) Filter recipes by ingredient
7) Scale recipe servings
q) Quit
```

### Example Interactions

**List all recipes:**
```
Enter choice: 1
Recipes:
- r1: Tomato Pasta (20 mins)
- r2: Chicken Stir Fry (25 mins)
- r3: Omelette (10 mins)
```

**Scale a recipe:**
```
Enter choice: 7
Enter recipe ID to scale: r1
Enter target serving size: 4

Original recipe serves 2:
  • 400.0 grams pasta
  • 500.0 grams tomato

Scaled to 4 servings:
  • 800.0 grams pasta
  • 1000.0 grams tomato
```

**Filter by ingredient:**
```
Enter choice: 6
Enter ingredient name to filter by: tomato
Recipes with 'tomato':
- r1: Tomato Pasta
```

## Architecture & Code Patterns

### Data Classes (Recipe, Ingredient)
- Auto-generated `equals()` for value comparison
- Auto-generated `copy()` for immutable scaling operations
- Custom `toString()` for readable output
- Demonstrates data class benefits over standard classes

### Collections & Functional Operations
- **filter()** - Find recipes by cooking time
- **flatMap()** - Flatten nested ingredient lists
- **groupBy()** - Organize recipes by difficulty or ingredient totals
- **sumOf()** - Calculate total ingredient quantities
- **find()** - Locate recipes by ID

### Variable Scope
- Demonstrates shadowing (local `timeLimit` variable in loops)
- Mutable vs. immutable collections
- Function parameter scope

### Kotlin Fundamentals Demonstrated

| Concept | Location | Example |
|---------|----------|---------|
| **Data Classes** | Recipe.kt, Ingredient.kt | `data class Recipe(...)` |
| **While Loops** | Main.kt:main() | `while (running) { ... }` |
| **When Conditionals** | Main.kt:main() | `when (readlnOrNull()?.trim()?.lowercase())` |
| **Collections** | RecipeService.kt | Lists, Sets, Maps operations |
| **Lambdas** | Main.kt, RecipeService.kt | `.filter { it.cookingTime <= max }` |
| **String Interpolation** | Recipe.kt | `"Recipe: $name (ID: $id)"` |
| **Function References** | RecipeService.kt | `.any { it in lowerNames }` |

## Code Review Summary

✅ **Strengths**
- Clean separation of concerns (models, services, UI)
- Comprehensive KDoc documentation on all public functions
- Proper null-safety with Elvis operator (?:)
- Immutable data models with data classes
- Functional programming patterns throughout

🔧 **Improvements Applied**
- Added require() validation for input constraints
- Trimmed user input for consistency
- Added detailed comments on complex collection operations
- Consistent formatting and naming conventions
- Error handling for user input

## Key Learning Outcomes

Through this project, you've demonstrated:
1. ✅ Kotlin syntax fundamentals (variables, functions, control flow)
2. ✅ Object-oriented design with data classes
3. ✅ Functional programming with lambdas and collections
4. ✅ Clean code organization with packages and services
5. ✅ User input handling and validation
6. ✅ Business logic implementation (filtering, scaling, aggregation)

## Future Development

- [ ] Complete CRUD operations (Update, Delete)
- [ ] Persistent storage (JSON/CSV file I/O)
- [ ] UserPreferences matching logic integration
- [ ] MealPlan service for daily meal generation
- [ ] Nutrition calculation engine
- [ ] Grocery list aggregation
- [ ] Unit tests for all business logic
- [ ] GUI using JavaFX

## Author

Created as a learning project for Kotlin fundamentals and console application development.

---

**Last Updated:** Sprint 1
**Status:** Core features implemented, ready for persistence layer
