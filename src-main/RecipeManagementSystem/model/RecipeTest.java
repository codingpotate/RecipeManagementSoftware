package RecipeManagementSystem.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RecipeTest {

    // Test method for checking if the creation date is set to the current date and
    // time
    public static void testRecipeCreationDate() {
        System.out.println("Running RecipeTest.testRecipeCreationDate...");

        // Create a recipe
        Recipe recipe = new Recipe("Test Recipe", "Description", Arrays.asList("Ingredient1", "Ingredient2"));

        // Get the current date
        Date currentDate = new Date();

        // Check if the creation date is not null and close to the current date
        if (recipe.getCreationDate() != null &&
                Math.abs(recipe.getCreationDate().getTime() - currentDate.getTime()) < 1000) {
            System.out.println("Recipe creation date test passed!");
        } else {
            System.out.println("Recipe creation date test failed!");
        }
    }

    // Test method for checking if the favorite status can be set and retrieved
    // correctly
    public static void testRecipeFavoriteStatus() {
        System.out.println("Running RecipeTest.testRecipeFavoriteStatus...");

        // Create a recipe
        Recipe recipe = new Recipe("Test Recipe", "Description", Arrays.asList("Ingredient1", "Ingredient2"));

        // Set and check favorite status
        recipe.setFavorite(true);
        if (recipe.isFavorite()) {
            System.out.println("Recipe favorite status test passed!");
        } else {
            System.out.println("Recipe favorite status test failed!");
        }
    }

    // Test method for checking if the formatted creation date is returned correctly
    public static void testRecipeFormattedCreationDate() {
        System.out.println("Running RecipeTest.testRecipeFormattedCreationDate...");

        // Create a recipe
        Recipe recipe = new Recipe("Test Recipe", "Description", Arrays.asList("Ingredient1", "Ingredient2"));

        // Get the formatted creation date
        String formattedDate = recipe.getFormattedCreationDate();

        // Check if the formatted date is not null or empty
        if (formattedDate != null && !formattedDate.isEmpty()) {
            System.out.println("Recipe formatted creation date test passed!");
        } else {
            System.out.println("Recipe formatted creation date test failed!");
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        // Run the individual test methods
        testRecipeCreationDate();
        testRecipeFavoriteStatus();
        testRecipeFormattedCreationDate();
    }
}
