package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.Recipe;
import RecipeManagementSystem.model.RecipeCatalog;
import RecipeManagementSystem.model.User;
import java.util.List;

public class RecipeManagerTest {

    public static void main(String[] args) {
        testAddRecipe();
        testDeleteRecipe();
        testSearchRecipesByIngredients();
    }

    private static void testAddRecipe() {
        RecipeManager recipeManager = new ConcreteRecipeManagerFactory().createRecipeManager();
        User user = new User("testUser", "testPassword");
        UserAuthenticationManager.getInstance().authenticateUser(user.getUsername(), user.getPassword());

        // Create a sample recipe
        Recipe recipe = new Recipe("Test Recipe", "Test Description", List.of("Ingredient1", "Ingredient2"));

        // Add the recipe to the manager
        recipeManager.addRecipe(recipe);

        // Check if the recipe was added successfully
        List<Recipe> recipes = recipeManager.getRecipes();
        assert recipes.size() == 1 : "Failed to add recipe to RecipeManager.";

        System.out.println("Test: Add Recipe - Passed");
    }

    private static void testDeleteRecipe() {
        RecipeManager recipeManager = new ConcreteRecipeManagerFactory().createRecipeManager();
        User user = new User("testUser", "testPassword");
        UserAuthenticationManager.getInstance().authenticateUser(user.getUsername(), user.getPassword());

        // Create and add a sample recipe
        Recipe recipe = new Recipe("Test Recipe", "Test Description", List.of("Ingredient1", "Ingredient2"));
        recipeManager.addRecipe(recipe);

        // Delete the recipe from the manager
        recipeManager.deleteRecipe(recipe);

        // Check if the recipe was deleted successfully
        List<Recipe> recipes = recipeManager.getRecipes();
        assert recipes.isEmpty() : "Failed to delete recipe from RecipeManager.";

        System.out.println("Test: Delete Recipe - Passed");
    }

    private static void testSearchRecipesByIngredients() {
        RecipeManager recipeManager = new ConcreteRecipeManagerFactory().createRecipeManager();
        User user = new User("testUser", "testPassword");
        UserAuthenticationManager.getInstance().authenticateUser(user.getUsername(), user.getPassword());

        // Create sample recipes with different ingredients
        Recipe recipe1 = new Recipe("Recipe1", "Description1", List.of("Ingredient1", "Ingredient2"));
        Recipe recipe2 = new Recipe("Recipe2", "Description2", List.of("Ingredient2", "Ingredient3"));
        Recipe recipe3 = new Recipe("Recipe3", "Description3", List.of("Ingredient3", "Ingredient4"));

        // Add recipes to the manager
        recipeManager.addRecipe(recipe1);
        recipeManager.addRecipe(recipe2);
        recipeManager.addRecipe(recipe3);

        // Search recipes by ingredients
        List<String> searchIngredients = List.of("Ingredient2", "Ingredient3");
        List<Recipe> matchingRecipes = recipeManager.searchRecipesByIngredients(searchIngredients);

        // Check if the correct recipes were found
        assert matchingRecipes.size() == 2 : "Incorrect number of matching recipes.";
        assert matchingRecipes.contains(recipe2) && matchingRecipes.contains(recipe3) : "Incorrect matching recipes.";

        System.out.println("Test: Search Recipes By Ingredients - Passed");
    }
}
