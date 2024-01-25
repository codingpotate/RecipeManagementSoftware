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

public class RecipeManagementFacade {
    private UserAuthenticationManager authenticationManager;
    private RecipeManager recipeManager;

    public RecipeManagementFacade() {
        this.authenticationManager = UserAuthenticationManager.getInstance();
        this.recipeManager = initializeRecipeManager();
    }

    private RecipeManager initializeRecipeManager() {
        RecipeManagerFactory recipeManagerFactory = new ConcreteRecipeManagerFactory();
        return recipeManagerFactory.createRecipeManager();
    }

    public void login(String username, String password) {
        authenticationManager.authenticateUser(username, password);
    }

    public void addRecipe(String title, String description, String ingredientsInput) {
        List<String> ingredients = Arrays.asList(ingredientsInput.split("\\s*,\\s*"));
        Recipe recipe = new Recipe(title, description, ingredients);
        recipeManager.addRecipe(recipe);
    }

    public List<Recipe> searchRecipesByIngredients(List<String> ingredients) {
        return recipeManager.searchRecipesByIngredients(ingredients);
    }

    public void performUserActionExample() {
        System.out.println("Facade is simplifying user action...");
        login("testuser", "password123");
        List<String> searchIngredients = Arrays.asList("Ingredient1", "Ingredient2");
        List<Recipe> matchingRecipes = searchRecipesByIngredients(searchIngredients);
        System.out.println("Matching recipes: " + matchingRecipes);
    }
}
