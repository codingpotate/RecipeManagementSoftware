package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.Recipe;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileHandler {
    public void saveRecipesToFile(List<Recipe> recipes, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(recipes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Recipe> loadRecipesFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
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

    public void saveShoppingListsToFile(List<String> shoppingLists, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(shoppingLists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<String> loadShoppingListsFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
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

    public void saveMealPlanToFile(Map<String, Recipe> mealPlan, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(mealPlan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Recipe> loadMealPlanFromFile(String filePath) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object object = ois.readObject();

            if (object instanceof Map) {
                return (Map<String, Recipe>) object;
            } else {
                throw new ClassCastException("Unexpected object type found in file for meal plan.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Meal plan file not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading meal plan from file: " + e.getMessage());
        }

        return Map.of();
    }
}
