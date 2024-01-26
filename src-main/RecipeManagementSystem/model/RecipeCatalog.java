package RecipeManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeCatalog implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private List<Recipe> recipes; // Add this field to store recipes
    private String state;
    public RecipeCatalog() {
        this.recipes = new ArrayList<>();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("RecipeCatalogUpdated"); // Notify observers about the update
        }
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
        notifyObservers();
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
        notifyObservers();
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

    public List<Recipe> getRecipes() {
        return new ArrayList<>(recipes); // Return a copy to prevent external modification
    }

    public void setState(String newState) {
        this.state = newState;
        notifyObservers();
    }

    public String getState() {
        return state;
    }
}