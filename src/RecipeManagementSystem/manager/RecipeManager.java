package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.Recipe;
import RecipeManagementSystem.model.RecipeCatalog;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class RecipeManager {
    private static RecipeManager instance;
    private RecipeCatalog recipeCatalog;
    private ShoppingListManager shoppingListManager;
    private MealPlanManager mealPlanManager;
    private FileHandler fileHandler;
    private InputHandler inputHandler;

    private RecipeManager() {
        this.recipeCatalog = new RecipeCatalog();
        this.shoppingListManager = new ShoppingListManager();
        this.mealPlanManager = new MealPlanManager();
        this.fileHandler = new FileHandler();
        this.inputHandler = new InputHandler();
        loadRecipes(); // Load recipes from file upon instantiation
        loadShoppingLists(); // Load shopping lists from file upon instantiation
        loadMealPlan(); // Load meal plan from file upon instantiation
    }

    public static RecipeManager createRecipeManager() {
        if (instance == null) {
            instance = new RecipeManager();
        }
        return instance;
    }

    public List<Recipe> getRecipes() {
        return recipeCatalog.getRecipes();
    }

    public List<String> getShoppingLists() {
        return shoppingListManager.getShoppingLists();
    }

    public void addRecipe(Recipe recipe) {
        recipeCatalog.addRecipe(recipe);
        saveRecipes();
    }

    public void deleteRecipe(Recipe recipe) {
        recipeCatalog.deleteRecipe(recipe);
        saveRecipes();
    }

    public Recipe getRecipeByTitle(String title) {
        return recipeCatalog.getRecipeByTitle(title);
    }

    public List<Recipe> searchRecipesByIngredients(List<String> ingredients) {
        return recipeCatalog.searchRecipesByIngredients(ingredients);
    }

    public void planMeal(String day, Recipe recipe) {
        mealPlanManager.planMeal(day, recipe);
        saveMealPlan();
    }

    public Recipe getPlannedMeal(String day) {
        return mealPlanManager.getPlannedMeal(day);
    }

    public void clearMealPlan() {
        mealPlanManager.clearMealPlan();
        saveMealPlan();
    }

    public void addShoppingList(String shoppingList) {
        shoppingListManager.addShoppingList(shoppingList);
        saveShoppingLists();
    }

    public void saveRecipes() {
        fileHandler.saveRecipesToFile(recipeCatalog.getRecipes(), "recipes.txt");
    }

    public void saveShoppingLists() {
        fileHandler.saveShoppingListsToFile(shoppingListManager.getShoppingLists(), "shoppingLists.txt");
    }

    public void saveMealPlan() {
        fileHandler.saveMealPlanToFile(mealPlanManager.getPlannedMeals(), "mealPlan.txt");
    }

    private void loadRecipes() {
        List<Recipe> recipes = fileHandler.loadRecipesFromFile("recipes.txt");
        if (recipes != null) {
            recipeCatalog = new RecipeCatalog();
            for (Recipe recipe : recipes) {
                recipeCatalog.addRecipe(recipe);
            }
        }
    }

    private void loadShoppingLists() {
        List<String> shoppingLists = fileHandler.loadShoppingListsFromFile("shoppingLists.txt");
        if (shoppingLists != null) {
            shoppingListManager = new ShoppingListManager();
            for (String shoppingList : shoppingLists) {
                shoppingListManager.addShoppingList(shoppingList);
            }
        }
    }

    private void loadMealPlan() {
        Map<String, Recipe> mealPlan = fileHandler.loadMealPlanFromFile("mealPlan.txt");
        if (mealPlan != null) {
            mealPlanManager = new MealPlanManager();
            for (Entry<String, Recipe> entry : mealPlan.entrySet()) {
                String day = entry.getKey();
                Recipe recipe = entry.getValue();
                mealPlanManager.planMeal(day, recipe);
            }
        }
    }
}
