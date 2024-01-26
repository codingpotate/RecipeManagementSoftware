package RecipeManagementSystem.manager;

// Import statements for the test
import java.util.List;
import java.util.ArrayList;
import RecipeManagementSystem.model.Recipe;

public class MealPlanManagerTest {

    // Test method for planning a meal and getting a planned meal
    public static void testPlanMealAndGetPlannedMeal() {
        System.out.println("Running MealPlanManagerTest.testPlanMealAndGetPlannedMeal...");

        // Create a sample recipe
        Recipe sampleRecipe = new Recipe("Test Recipe", "Test Description", List.of("Ingredient1", "Ingredient2"));

        // Create a MealPlanManager instance
        MealPlanManager mealPlanManager = new MealPlanManager();

        // Plan a meal for a specific day
        mealPlanManager.planMeal("Monday", sampleRecipe);

        // Get the planned meal for the same day
        Recipe plannedMeal = mealPlanManager.getPlannedMeal("Monday");

        // Verify that the planned meal is the same as the sample recipe
        if (plannedMeal != null && plannedMeal.getTitle().equals(sampleRecipe.getTitle())) {
            System.out.println("Plan meal and get planned meal test passed!");
        } else {
            System.out.println("Plan meal and get planned meal test failed.");
        }
    }

    // Test method for clearing a meal plan
    public static void testClearMealPlan() {
        System.out.println("Running MealPlanManagerTest.testClearMealPlan...");

        // Create a MealPlanManager instance
        MealPlanManager mealPlanManager = new MealPlanManager();

        // Plan a meal for a specific day
        mealPlanManager.planMeal("Monday",
                new Recipe("Test Recipe", "Test Description", List.of("Ingredient1", "Ingredient2")));

        // Clear the meal plan
        mealPlanManager.clearMealPlan();

        // Get the planned meal for the day (should be null after clearing)
        Recipe plannedMeal = mealPlanManager.getPlannedMeal("Monday");

        // Verify that the planned meal is null after clearing
        if (plannedMeal == null) {
            System.out.println("Clear meal plan test passed!");
        } else {
            System.out.println("Clear meal plan test failed.");
        }
    }

    // Main method to run the tests
    public static void main(String[] args) {
        // Run the individual test methods
        testPlanMealAndGetPlannedMeal();
        testClearMealPlan();
    }
}
