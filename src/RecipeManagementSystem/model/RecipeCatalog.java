package RecipeManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeCatalog {
    private List<Recipe> recipes;

    public RecipeCatalog() {
        this.recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }

    public void deleteRecipe(Recipe recipe) {
        recipes.remove(recipe);
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
}
