package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.Recipe;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MealPlanManager {
    private static final String MEAL_PLAN_FILE_PATH = "mealPlan.txt";
    private Map<String, Recipe> plannedMeals;

    public MealPlanManager() {
        this.plannedMeals = new HashMap<>();
        loadMealPlanFromFile(); // Load meal plan from file upon instantiation
    }

    public void planMeal(String day, Recipe recipe) {
        plannedMeals.put(day, recipe);
        saveMealPlanToFile();
    }

    public Recipe getPlannedMeal(String day) {
        return plannedMeals.get(day);
    }

    public void clearMealPlan() {
        plannedMeals.clear();
        saveMealPlanToFile();
    }

    public Map<String, Recipe> getPlannedMeals() {
        return new HashMap<>(plannedMeals); // Return a copy to prevent external modification
    }

    public void saveMealPlanToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEAL_PLAN_FILE_PATH))) {
            oos.writeObject(plannedMeals);
        } catch (IOException e) {
            System.out.println("Error saving meal plan to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadMealPlanFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MEAL_PLAN_FILE_PATH))) {
            Object object = ois.readObject();

            if (object instanceof Map) {
                plannedMeals = (Map<String, Recipe>) object;
            } else {
                throw new ClassCastException("Unexpected object type found in file for meal plan.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Meal plan file not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading meal plan from file: " + e.getMessage());
        }
    }
}
