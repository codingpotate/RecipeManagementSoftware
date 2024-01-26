package RecipeManagementSystem.ui;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import RecipeManagementSystem.manager.ConcreteRecipeManagerFactory;
import RecipeManagementSystem.manager.InputHandler;
import RecipeManagementSystem.manager.FileHandler;
import RecipeManagementSystem.manager.MealPlanManager;
import RecipeManagementSystem.manager.RecipeManager;
import RecipeManagementSystem.manager.RecipeManagerFactory;
import RecipeManagementSystem.manager.UserAuthenticationManager;
import RecipeManagementSystem.model.Recipe;
import RecipeManagementSystem.model.decorators.RecipeDecorator;
import RecipeManagementSystem.model.decorators.RecipeWithCreationDateDecorator;
import RecipeManagementSystem.model.Subject;
import RecipeManagementSystem.model.RecipeCatalog;
import RecipeManagementSystem.model.CreationDateSort;
import RecipeManagementSystem.ui.RecipeUI;
public class ConsoleUI {
    private static final Subject recipeCatalog = new RecipeCatalog();
    private static final InputHandler inputHandler = new InputHandler();
    private static final UserAuthenticationManager authenticationManager = UserAuthenticationManager.getInstance();
    private static final MealPlanManager mealPlanManager = new MealPlanManager();
    private static RecipeManager recipeManager;

    public static void main(String[] args) {
        RecipeUI recipeUI1 = new RecipeUI(1, recipeCatalog);
        RecipeUI recipeUI2 = new RecipeUI(2, recipeCatalog);
        RecipeCatalog recipeCatalog = (RecipeCatalog) ConsoleUI.recipeCatalog;
        recipeCatalog.addRecipe(new Recipe("New Recipe", "Description", List.of("Ingredient1", "Ingredient2")));

        recipeManager = initializeRecipeManager();
        boolean exitRequested = false;

        while (!exitRequested) {
            printMainMenu();

            int mainChoice = getChoice();

            switch (mainChoice) {
                case 1:
                    authenticateUser();
                    break;
                case 2:
                    handleRecipeMenu(recipeManager);
                    break;
                case 3:
                    searchRecipe(recipeManager);
                    break;
                case 4:
                    viewRecipes(recipeManager);
                    break;
                case 5:
                    logoutUser();
                    break;
                case 6:
                    saveRecipes(recipeManager);
                    System.out.println("Exiting program...");
                    exitRequested = true;
                    break;
                case 7:
                    searchRecipe(recipeManager);
                    break;
                case 8:
                    createShoppingList(recipeManager);
                    break;
                case 9:
                    viewShoppingLists(recipeManager);
                    break;
                case 10:
                    planMeal(recipeManager);
                    break;
                case 11:
                    viewMealPlan(recipeManager);
                    break;
                case 12:
                    clearMealPlan(recipeManager);
                    break;
                case 13:
                    handleFavoriteMenu(recipeManager);
                    break;
                case 14:
                    shareRecipe(recipeManager);
                    break;
                case 15:
                    addDecoratedRecipe();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static RecipeManager initializeRecipeManager() {
        RecipeManagerFactory recipeManagerFactory = new ConcreteRecipeManagerFactory();
        return recipeManagerFactory.createRecipeManager();
    }

    private static void printMainMenu() {
        System.out.println("Main Menu:");
        System.out.println("1. Log In");
        System.out.println("2. Recipe Management");
        System.out.println("3. Search Recipe");
        System.out.println("4. View Recipes");
        System.out.println("5. Log Out");
        System.out.println("6. Save and Exit");
        System.out.println("7. Search Recipe");
        System.out.println("8. Create Shopping List");
        System.out.println("9. View Shopping List");
        System.out.println("10. Make a Meal Plan");
        System.out.println("11. View Meal Plans");
        System.out.println("12. Clear a Meal Plan");
        System.out.println("13. Manage Favorites");
        System.out.println("14. Copy Recipe");
        System.out.println("15. Add Recipe with date");
    }

    private static void printRecipeMenu() {
        System.out.println("Recipe Management:");
        System.out.println("1. Add Recipe");
        System.out.println("2. Delete Recipe");
        System.out.println("3. Back to Main Menu");
    }

    private static String getLineInput(String prompt) {
        return inputHandler.nextLine(prompt);
    }

    private static void handleRecipeMenu(RecipeManager recipeManager) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            printRecipeMenu();

            int recipeChoice = getChoice();

            switch (recipeChoice) {
                case 1:
                    addRecipe(recipeManager);
                    break;
                case 2:
                    deleteRecipe(recipeManager);
                    break;
                case 3:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static int getChoice() {
        try {
            System.out.print("Enter your choice: ");
            return inputHandler.getIntInput("");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            inputHandler.nextLine("Press Enter to continue...");
            return -1;
        }
    }

    private static void shareRecipe(RecipeManager recipeManager) {
        System.out.print("Enter the title of the recipe to share: ");
        String titleToShare = inputHandler.getLineInput("");
        Recipe recipeToShare = recipeManager.getRecipeByTitle(titleToShare);

        if (recipeToShare != null) {
            String sharedRecipeDetails = generateSharedRecipeDetails(recipeToShare);
            System.out.println("Recipe details copied to clipboard: ");
            System.out.println(sharedRecipeDetails);
        } else {
            System.out.println("Recipe not found.");
        }
    }

    private static String generateSharedRecipeDetails(Recipe recipe) {
        StringBuilder sharedDetails = new StringBuilder();
        sharedDetails.append("Title: ").append(recipe.getTitle()).append("\n");
        sharedDetails.append("Description: ").append(recipe.getDescription()).append("\n");
        sharedDetails.append("Ingredients: ").append(String.join(", ", recipe.getIngredients())).append("\n");

        if (recipe instanceof RecipeDecorator) {
            Date creationDate = ((RecipeDecorator) recipe).getCreationDate();
            if (creationDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sharedDetails.append("Creation Date: ").append(dateFormat.format(creationDate)).append("\n");
            }
        }
        return sharedDetails.toString();
    }

    private static void handleFavoriteMenu(RecipeManager recipeManager) {
        boolean backToMainMenu = false;

        while (!backToMainMenu) {
            printFavoriteMenu();

            int favoriteChoice = getChoice();

            switch (favoriteChoice) {
                case 1:
                    markAsFavorite(recipeManager);
                    break;
                case 2:
                    viewFavoriteRecipes(recipeManager);
                    break;
                case 3:
                    backToMainMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private static void printFavoriteMenu() {
        System.out.println("Favorite Menu:");
        System.out.println("1. Mark Recipe as Favorite");
        System.out.println("2. View Favorite Recipes");
        System.out.println("3. Back to Main Menu");
    }

    private static void markAsFavorite(RecipeManager recipeManager) {
        List<Recipe> recipes = recipeManager.getRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No recipes available to mark as favorite.");
            return;
        }
        System.out.println("Available Recipes: ");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getTitle());
        }

        System.out.print("Enter the index of the recipe to mark as favorite: ");
        int recipeIndex = getChoice();

        if (recipeIndex >= 1 && recipeIndex <= recipes.size()) {
            Recipe selectedRecipe = recipes.get(recipeIndex - 1);
            selectedRecipe.setFavorite(true);
            System.out.println(selectedRecipe.getTitle() + " marked as favorite!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewFavoriteRecipes(RecipeManager recipeManager) {
        List<Recipe> recipes = recipeManager.getRecipes();
        List<Recipe> favoriteRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            if (recipe.isFavorite()) {
                favoriteRecipes.add(recipe);
            }
        }

        if (favoriteRecipes.isEmpty()) {
            System.out.println("No favorite recipes available.");
        } else {
            System.out.println("Favorite Recipes:");

            for (int i = 0; i < favoriteRecipes.size(); i++) {
                System.out.println((i + 1) + ". " + favoriteRecipes.get(i).getTitle());
            }
        }
    }

    private static void addRecipe(RecipeManager recipeManager) {
        if (authenticationManager.getAuthenticatedUser() == null) {
            System.out.println("Please log in first.");
            return;
        }

        System.out.println("Enter recipe title:");
        String title = inputHandler.getLineInput("");
        System.out.println("Enter recipe description:");
        String description = inputHandler.getLineInput("");
        System.out.println("Enter ingredients separated by commas:");
        String ingredientsInput = inputHandler.getLineInput("");
        List<String> ingredients = List.of(ingredientsInput.split("\\s*,\\s*"));

        Recipe recipe = new Recipe(title, description, ingredients);
        recipeManager.addRecipe(recipe);
        System.out.println("Recipe added successfully!");
    }

    private static void deleteRecipe(RecipeManager recipeManager) {
        if (authenticationManager.getAuthenticatedUser() == null) {
            System.out.println("Please log in first.");
            return;
        }

        List<Recipe> recipes = recipeManager.getRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
            return;
        }

        System.out.println("Select a recipe to delete:");

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getTitle());
        }

        try {
            int choice = inputHandler.getIntInput("Enter the number of the recipe to delete: ");

            if (choice >= 1 && choice <= recipes.size()) {
                Recipe selectedRecipe = recipes.get(choice - 1);
                recipeManager.deleteRecipe(selectedRecipe);
                System.out.println("Recipe deleted successfully!");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            inputHandler.nextLine("Press Enter to continue...");
        }
    }

    private static void authenticateUser() {
        System.out.println("Enter username:");
        String username = inputHandler.getLineInput("");
        System.out.println("Enter password:");
        String password = inputHandler.getLineInput("");
        authenticationManager.authenticateUser(username, password);
    }

    private static void viewRecipes(RecipeManager recipeManager) {
        List<Recipe> recipes = recipeManager.getRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No recipes available.");
        } else {
            System.out.println("Recipes:");

            for (Recipe recipe : recipes) {
                // Check if the recipe is a decorator, if yes, use the decorator's description
                String description = (recipe instanceof RecipeDecorator) ? ((RecipeDecorator) recipe).getDescription()
                        : recipe.getDescription();

                System.out.println(recipe.getTitle() + " - " + description);
            }
        }
    }

    private static void addDecoratedRecipe() {
        if (authenticationManager.getAuthenticatedUser() == null) {
            System.out.println("Please log in first.");
            return;
        }

        System.out.println("Enter recipe title:");
        String title = inputHandler.getLineInput("");
        System.out.println("Enter recipe description:");
        String description = inputHandler.getLineInput("");
        System.out.println("Enter ingredients separated by commas:");
        String ingredientsInput = inputHandler.getLineInput("");
        List<String> ingredients = List.of(ingredientsInput.split("\\s*,\\s*"));

        // Create a basic recipe
        Recipe originalRecipe = new Recipe(title, description, ingredients);

        // Decorate the recipe with a creation date
        Recipe decoratedRecipe = new RecipeWithCreationDateDecorator(originalRecipe);

        // Add the decorated recipe to the manager
        recipeManager.addRecipe(decoratedRecipe);
        System.out.println("Decorated recipe added successfully!");
    }

    private static void logoutUser() {
        authenticationManager.logout();
    }

    private static void planMeal(RecipeManager recipeManager) {
        System.out.print("Enter the day for the meal plan: ");
        String day = inputHandler.getLineInput("");

        List<Recipe> recipes = recipeManager.getRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No recipes available for meal planning.");
            return;
        }
        System.out.println("Available recipes: ");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getTitle());
        }
        System.out.printf("Enter the index of the recipe to plan for " + day + ": ");
        int recipeIndex = getChoice();

        if (recipeIndex >= 1 && recipeIndex <= recipes.size()) {
            Recipe selectedRecipe = recipes.get(recipeIndex - 1);
            mealPlanManager.planMeal(day, selectedRecipe); // Call planMeal on the instance
            System.out.println("Meal planned successfully!");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void viewMealPlan(RecipeManager recipeManager) {
        System.out.print("Enter the day to view the meal plan: ");
        String day = inputHandler.getLineInput("");

        Recipe plannedMeal = mealPlanManager.getPlannedMeal(day); // Call getPlannedMeal on the instance

        if (plannedMeal != null) {
            System.out.println("Planned meal for " + day + ": " + plannedMeal.getTitle());
        } else {
            System.out.println("No planned meal found for " + day + ". ");
        }
    }

    private static void clearMealPlan(RecipeManager recipeManager) {
        System.out.print("Enter the day to clear the meal plan: ");
        String day = inputHandler.getLineInput("");

        mealPlanManager.clearMealPlan(); // Call clearMealPlan on the instance
        System.out.println("Meal plan cleared for " + day + ".");
    }

    private static void searchRecipe(RecipeManager recipeManager) {
        System.out.println("Choose search criteria: ");
        System.out.println("1. Title");
        System.out.println("2. Ingredients");

        int searchChoice = inputHandler.getIntInput("Enter your choice: ");

        switch (searchChoice) {
            case 1:
                searchByTitle(recipeManager);
                break;
            case 2:
                searchByIngredients(recipeManager);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void searchByTitle(RecipeManager recipeManager) {
        System.out.println("Enter the title of the recipe you want to search: ");
        String titleToSearch = inputHandler.getLineInput(""); // Provide an empty string or a suitable prompt
        Recipe recipe = recipeManager.getRecipeByTitle(titleToSearch);

        if (recipe != null) {
            displayRecipeDetails(recipe);
        } else {
            System.out.println("Recipe not found.");
        }
    }

    private static void searchByIngredients(RecipeManager recipeManager) {
        System.out.println("Enter the ingredients you want to search (separated by commas): ");
        String ingredientsInput = inputHandler.getLineInput("");
        List<String> ingredients = List.of(ingredientsInput.split("\\s*,\\s*"));
        List<Recipe> matchingRecipes = recipeManager.searchRecipesByIngredients(ingredients);

        if (!matchingRecipes.isEmpty()) {
            System.out.println("Matching Recipes:");
            for (Recipe recipe : matchingRecipes) {
                displayRecipeDetails(recipe);
            }
        } else {
            System.out.println("No recipes found with the specified ingredients.");
        }
    }

    private static void displayRecipeDetails(Recipe recipe) {
        System.out.println("Recipe Details: ");
        System.out.println("Title: " + recipe.getTitle());
        System.out.println("Description: " + recipe.getDescription());
        System.out.println("Ingredients: " + recipe.getIngredients());

        // Check if the recipe is a decorator and has a creation date
        if (recipe instanceof RecipeDecorator) {
            Date creationDate = ((RecipeDecorator) recipe).getCreationDate();
            if (creationDate != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println("Creation Date: " + dateFormat.format(creationDate));
            }
        }
    }

    private static void saveRecipes(RecipeManager recipeManager) {
        recipeManager.saveRecipes();
        System.out.println("Recipes saved successfully.");
    }

    private static void createShoppingList(RecipeManager recipeManager) {
        List<Recipe> recipes = recipeManager.getRecipes();

        if (recipes.isEmpty()) {
            System.out.println("No recipes available to create a shopping list.");
            return;
        }

        // Print the available recipes
        System.out.println("Available Recipes:");
        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + ". " + recipes.get(i).getTitle());
        }

        List<Integer> selectedRecipeIndices = getSelectedRecipeIndices();

        List<String> shoppingList = generateShoppingList(recipes, selectedRecipeIndices);

        if (!shoppingList.isEmpty()) {
            System.out.println("Shopping List:");
            for (String item : shoppingList) {
                System.out.println(item);
            }
            recipeManager.addShoppingList(shoppingList.toString()); // Save shopping list
            FileHandler fileHandler = new FileHandler();
            fileHandler.saveShoppingListsToFile(shoppingList, "filename.txt");
        } else {
            System.out.println("No recipes selected for the shopping list.");
        }
    }

    private static List<Integer> getSelectedRecipeIndices() {
        List<Integer> selectedIndices = new ArrayList<>();
        System.out.print("Enter recipe indices (separated by commas): ");
        String indiceInput = inputHandler.getLineInput("");
        String[] indices = indiceInput.split("\\s*,\\s*");
        for (String index : indices) {
            try {
                selectedIndices.add(Integer.parseInt(index) - 1);
            } catch (NumberFormatException e) {
                System.out.println("Invalid index: " + index);
            }
        }
        return selectedIndices;
    }

    private static List<String> generateShoppingList(List<Recipe> recipes, List<Integer> selectedRecipeIndices) {
        List<String> shoppingList = new ArrayList<>();

        for (int index : selectedRecipeIndices) {
            if (index >= 0 && index < recipes.size()) {
                Recipe recipe = recipes.get(index);
                String formattedIngredients = recipe.getFormattedIngredients();
                shoppingList.add(recipe.getTitle() + ": " + formattedIngredients);
            }
        }

        return shoppingList;
    }

    private static void viewShoppingLists(RecipeManager recipeManager) {
        List<String> shoppingLists = recipeManager.getShoppingLists();

        if (shoppingLists.isEmpty()) {
            System.out.println("No shopping lists available.");
        } else {
            System.out.println("Shopping Lists:");

            for (int i = 0; i < shoppingLists.size(); i++) {
                System.out.println((i + 1) + ". " + shoppingLists.get(i));
            }
        }
    }

}
