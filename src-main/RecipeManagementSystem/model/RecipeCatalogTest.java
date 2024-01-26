package RecipeManagementSystem.model;

import java.util.ArrayList;
import java.util.List;

public class RecipeCatalogTest {

    // Test method for adding and retrieving recipes from the catalog
    public static void testRecipeCatalogAddAndRetrieve() {
        System.out.println("Running RecipeCatalogTest.testRecipeCatalogAddAndRetrieve...");

        // Create a recipe catalog
        RecipeCatalog recipeCatalog = new RecipeCatalog();

        // Create a sample recipe
        Recipe recipe = new Recipe("Test Recipe", "Description", List.of("Ingredient1", "Ingredient2"));

        // Add the recipe to the catalog
        recipeCatalog.addRecipe(recipe);

        // Retrieve the recipes from the catalog
        List<Recipe> retrievedRecipes = recipeCatalog.getRecipes();

        // Check if the retrieved recipes list is not null and contains the added recipe
        if (retrievedRecipes != null && retrievedRecipes.size() == 1 && retrievedRecipes.get(0).equals(recipe)) {
            System.out.println("RecipeCatalog add and retrieve test passed!");
        } else {
            System.out.println("RecipeCatalog add and retrieve test failed!");
        }
    }

    // Test method for removing recipes from the catalog
    public static void testRecipeCatalogRemove() {
        System.out.println("Running RecipeCatalogTest.testRecipeCatalogRemove...");

        // Create a recipe catalog
        RecipeCatalog recipeCatalog = new RecipeCatalog();

        // Create a sample recipe
        Recipe recipe = new Recipe("Test Recipe", "Description", List.of("Ingredient1", "Ingredient2"));

        // Add the recipe to the catalog
        recipeCatalog.addRecipe(recipe);

        // Remove the recipe from the catalog
        recipeCatalog.deleteRecipe(recipe);

        // Retrieve the recipes from the catalog
        List<Recipe> retrievedRecipes = recipeCatalog.getRecipes();

        // Check if the retrieved recipes list is empty
        if (retrievedRecipes != null && retrievedRecipes.isEmpty()) {
            System.out.println("RecipeCatalog remove test passed!");
        } else {
            System.out.println("RecipeCatalog remove test failed!");
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        // Run the individual test methods
        testRecipeCatalogAddAndRetrieve();
        testRecipeCatalogRemove();
    }
}
