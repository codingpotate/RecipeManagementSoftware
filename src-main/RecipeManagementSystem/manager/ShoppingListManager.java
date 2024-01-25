package RecipeManagementSystem.manager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingListManager {
    private static final String SHOPPING_LISTS_FILE_PATH = "shoppingLists.txt";
    private List<String> shoppingLists;

    public ShoppingListManager() {
        this.shoppingLists = new ArrayList<>();
        loadShoppingListsFromFile(); // Load shopping lists from file upon instantiation
    }

    public void addShoppingList(String shoppingList) {
        shoppingLists.add(shoppingList);
        saveShoppingListsToFile();
    }

    public List<String> getShoppingLists() {
        return new ArrayList<>(shoppingLists); // Return a copy to prevent external modification
    }

    public void saveShoppingListsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SHOPPING_LISTS_FILE_PATH))) {
            oos.writeObject(shoppingLists);
        } catch (IOException e) {
            System.out.println("Error saving shopping lists to file: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadShoppingListsFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SHOPPING_LISTS_FILE_PATH))) {
            Object object = ois.readObject();

            if (object instanceof List) {
                shoppingLists = (List<String>) object;
            } else {
                throw new ClassCastException("Unexpected object type found in file for shopping lists.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Shopping lists file not found. Creating a new file.");
        } catch (IOException | ClassNotFoundException | ClassCastException e) {
            System.out.println("Error loading shopping lists from file: " + e.getMessage());
        }
    }
}
