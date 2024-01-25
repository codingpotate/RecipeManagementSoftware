package RecipeManagementSystem.manager;

import java.util.HashMap;
import java.util.Map;

import RecipeManagementSystem.model.Recipe;

public class MealPlan {
    private Map<String, Recipe> plannedMeals;

    public MealPlan() {
        this.plannedMeals = new HashMap<>();
    }

    public void planMeal(String day, Recipe recipe) {
        plannedMeals.put(day, recipe);
    }

    public Recipe getPlannedMeal(String day) {
        return plannedMeals.get(day);
    }

    public void clearMealPlan() {
        plannedMeals.clear();
    }
}
