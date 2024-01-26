package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.Recipe;
import RecipeManagementSystem.model.MealPlan;
import RecipeManagementSystem.manager.MealPlanManager;
import java.util.ArrayList;
import java.util.List;

public class FileHandlerTest {

    // Test method for checking if shopping lists are saved to a file successfully
    public static void testSaveShoppingListsToFile() {
        System.out.println("Running FileHandlerTest.testSaveShoppingListsToFile...");

        // Create a sample shopping list
        List<String> shoppingList = new ArrayList<>();
        shoppingList.add("Item1");
        shoppingList.add("Item2");
        shoppingList.add("Item3");

        // Create a FileHandler instance
        FileHandler fileHandler = new FileHandler();

        // Save the shopping list to a file
        fileHandler.saveShoppingListsToFile(shoppingList, "testShoppingList.txt");

        // You may add additional checks here, like reading the file and verifying its
        // content

        System.out.println("Save shopping lists to file test passed!");
    }

    // Main method to run the tests
    public static void main(String[] args) {
        // Run the individual test methods
        testSaveShoppingListsToFile();
    }
}
