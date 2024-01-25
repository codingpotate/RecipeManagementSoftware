package RecipeManagementSystem.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import RecipeManagementSystem.model.Recipe;

public class RecipeManager {
    private List<Recipe> recipes;
    private List<String> shoppingLists;
    private static final String RECIPES_FILE_PATH = "recipes.txt";
    private static final String SHOPPING_LISTS_FILE_PATH = "shoppingLists.txt";
    private static final String MEAL_PLAN_FILE_PATH = "mealPlan.txt";

    private RecipeManager(List<Recipe> recipes, List<String> shoppingLists) {
        this.recipes = recipes;
        this.shoppingLists = shoppingLists;
        this.mealPlan = new MealPlan();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        saveRecipesToFile();
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
        saveRecipesToFile();
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<String> getShoppingLists() {
        return shoppingLists;
    }

    public void addShoppingList(String shoppingList) {
        shoppingLists.add(shoppingList);
        saveShoppingListsToFile();
    }

    public void saveRecipesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(RECIPES_FILE_PATH))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            System.out.println("Error saving recipes to file: " + e.getMessage());
        }
    }

    public void saveShoppingListsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SHOPPING_LISTS_FILE_PATH))) {
            oos.writeObject(shoppingLists);
        } catch (IOException e) {
            System.out.println("Error saving shopping lists to file: " + e.getMessage());
        }
    }

    public static RecipeManager createRecipeManager() {
        List<Recipe> recipes = loadRecipesFromFile();
        List<String> shoppingLists = loadShoppingListsFromFile();
        return new RecipeManager(recipes, shoppingLists);
    }

    @SuppressWarnings("unchecked")
    private static List<Recipe> loadRecipesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(RECIPES_FILE_PATH))) {
            Object object = ois.readObject();

            if (object instanceof List) {
                return (List<Recipe>) object;
            } else {
                throw new ClassCastException("Unexpected object type found in file for recipes.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Recipes file not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading recipes from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    private static List<String> loadShoppingListsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SHOPPING_LISTS_FILE_PATH))) {
            Object object = ois.readObject();

            if (object instanceof List) {
                return (List<String>) object;
            } else {
                throw new ClassCastException("Unexpected object type found in file for shopping lists.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Shopping lists file not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading shopping lists from file: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public Recipe getRecipeByTitle(String title) {
        for (Recipe recipe : recipes) {
            if (recipe.getTitle().equalsIgnoreCase(title)) {
                return recipe;
            }
        }
        return null;
    }

    public List<Recipe> searchRecipesByIngredients(List<String> ingredients) {
        List<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (containsAllIngredients(recipe.getIngredients(), ingredients)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }

    private boolean containsAllIngredients(List<String> recipeIngredients, List<String> searchIngredients) {
        for (String searchIngredient : searchIngredients) {
            if (!recipeIngredients.contains(searchIngredient)) {
                return false;
            }
        }
        return true;
    }

    private MealPlan mealPlan;

    public void planMeal(String day, Recipe recipe) {
        mealPlan.planMeal(day, recipe);
        saveMealPlanToFile();
    }

    public Recipe getPlannedMeal(String day) {
        return mealPlan.getPlannedMeal(day);
    }

    public void saveMealPlanToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEAL_PLAN_FILE_PATH))) {
            oos.writeObject(mealPlan);
        } catch (IOException e) {
            System.out.println("Error saving meal plan to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private static MealPlan loadMealPlanFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEAL_PLAN_FILE_PATH))) {
            Object object = ois.readObject();

            if (object instanceof MealPlan) {
                return (MealPlan) object;
            } else {
                throw new ClassCastException("Unexpected object type found in file for meal plan.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Meal plan file not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading meal plan from file: " + e.getMessage());
        }
        return new MealPlan();
    }

    public void clearMealPlan() {
        mealPlan.clearMealPlan();
        saveMealPlanToFile();
    }
}
