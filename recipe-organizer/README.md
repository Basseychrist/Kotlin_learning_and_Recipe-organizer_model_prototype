# Recipe Organizer & Smart Meal Planner

A console-based Kotlin application for managing recipes, filtering by ingredients/cooking time, and generating structured meal plans.

## Features

- 📚 **Recipe Management**: Add, view, and organize recipes with ingredients and metadata
- 🔍 **Smart Filtering**: Filter recipes by ingredients, cooking time, and tags
- 📅 **Meal Planning**: Auto-generate daily and weekly meal plans
- 🛒 **Grocery Lists**: Generate ingredient lists from meal plans
- 🎯 **Preferences**: Customize meal plans based on dietary restrictions and preferences

## Project Structure

```
recipe-organizer/
├── src/main/kotlin/
│   ├── Main.kt                 # Entry point
│   ├── models/
│   │   ├── Recipe.kt           # Recipe data class
│   │   ├── Ingredient.kt       # Ingredient data class
│   │   ├── MealPlan.kt         # Meal plan classes
│   │   └── UserPreferences.kt  # User preferences
│   ├── services/
│   │   ├── RecipeManager.kt    # Recipe CRUD & filtering
│   │   └── MealPlanner.kt      # Meal plan generation
│   └── ui/
│       └── Console.kt          # Console UI & menu
└── README.md
```

## Getting Started

### Prerequisites
- Kotlin 1.8+
- Java 11+

### Installation

```bash
git clone https://github.com/Basseychrist/Kotlin_learning_and_Recipe-organizer_model_prototype.git

cd recipe-organizer
```

### Running the Application

```bash
kotlinc -d out src/main/kotlin/*.kt src/main/kotlin/**/*.kt
java -cp out MainKt
```

## Usage

Once running, the console menu provides options to:

1. **Manage Recipes** - Add, view, and delete recipes
2. **Filter Recipes** - Find recipes by ingredients, time, or tags
3. **Generate Meal Plans** - Create daily or weekly meal plans
4. **View Grocery Lists** - See all ingredients needed for a meal plan
5. **Exit** - Close the application

## Example: Adding a Recipe

```
Enter recipe name: Pasta Carbonara
Enter ingredients (name,quantity,unit): eggs,4,
                                      bacon,200,grams
                                      pasta,500,grams
Enter cooking time (minutes): 20
Enter difficulty (Easy/Medium/Hard): Easy
Enter tags (breakfast,lunch,etc): lunch,dinner
```

## Architecture

### Core Classes

- **Recipe**: Immutable recipe with name, ingredients, cooking time, difficulty, tags
- **Ingredient**: Represents quantity and unit of an ingredient
- **MealPlan**: Single day plan with breakfast, lunch, dinner, snacks
- **RecipeManager**: CRUD operations and filtering logic
- **MealPlanner**: Generates meal plans based on preferences

### Key Concepts Demonstrated

✓ **Variable Scope**: Local vs. class-level variables in Kotlin  
✓ **Collections**: Lists, Sets, Maps for recipe storage and filtering  
✓ **Lambdas**: Functional filtering and searching  
✓ **Data Classes**: Immutable model objects  
✓ **Extension Functions**: Custom operations on collections

## Development Roadmap

- [x] Project setup & design
- [x] Core data models
- [ ] Recipe management service
- [ ] Filtering logic
- [ ] Meal planning engine
- [ ] Console UI
- [ ] Persistent storage (JSON)
- [ ] Nutrition tracking
- [ ] Advanced planning features

## Contributing

Feel free to fork this project and submit pull requests for any improvements!

## License

MIT License - see LICENSE file for details

## Author
Eno-Obong Etim Bassey

Created as a learning project for Kotlin console applications and collection manipulation.
