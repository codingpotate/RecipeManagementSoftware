package RecipeManagementSystem.ui.swing;

import RecipeManagementSystem.manager.ConcreteRecipeManagerFactory;
import RecipeManagementSystem.manager.RecipeManager;
import RecipeManagementSystem.manager.RecipeManagerFactory;
import RecipeManagementSystem.manager.UserAuthenticationManager;
import RecipeManagementSystem.manager.MealPlanManager;
import RecipeManagementSystem.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class MainUI extends JFrame {

    private RecipeManager recipeManager = new ConcreteRecipeManagerFactory().createRecipeManager();
    private MealPlanManager mealPlanManager;

    private JButton viewRecipesButton;
    private JButton addRecipeButton;
    private JButton planMealButton;
    private JButton viewMealPlanButton;
    private JButton viewShoppingListButton;

    private JButton logInButton;
    private JButton saveAndExitButton;
    private JButton logOutButton;
    private JButton createShoppingListButton;
    private JButton clearMealPlanButton;
    private JButton manageFavoritesButton;
    private JButton copyRecipeButton;
    private UserAuthenticationManager authenticationManager;
    private JPanel mainPanel;

    private JTabbedPane tabbedPane;

    public MainUI() {
        initializeManagers(); 
        initializeUI();
        createComponents();
        setUpLayout();
        addEventListeners();
        authenticationManager = UserAuthenticationManager.getInstance();
    }

       private void initializeManagers() {
        RecipeManagerFactory recipeManagerFactory = new ConcreteRecipeManagerFactory();
        recipeManager = recipeManagerFactory.createRecipeManager();
        mealPlanManager = new MealPlanManager(); // Instantiate MealPlanManager
    }

    private void initializeUI() {
        setTitle("Recipe Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
 

    private void createComponents() {
        viewRecipesButton = new JButton("View Recipes");
        addRecipeButton = new JButton("Add Recipe");
        planMealButton = new JButton("Plan Meal");
        viewMealPlanButton = new JButton("View Meal Plan");
        viewShoppingListButton = new JButton("View Shopping List");

        logInButton = new JButton("Log In");
        saveAndExitButton = new JButton("Save and Exit");
        logOutButton = new JButton("Log Out");
        createShoppingListButton = new JButton("Create Shopping List");
        clearMealPlanButton = new JButton("Clear Meal Plan");
        manageFavoritesButton = new JButton("Manage Favorites");
        copyRecipeButton = new JButton("Copy Recipe");

        mainPanel = new JPanel();

        tabbedPane = new JTabbedPane();
    }

    private void setUpLayout() {
        setLayout(new BorderLayout());
    
        // Create a tabbed pane for different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();
    
        // Panel for Recipe Management
        JPanel recipeManagementPanel = new JPanel();
        recipeManagementPanel.setLayout(new FlowLayout());
        recipeManagementPanel.add(addRecipeButton);
        recipeManagementPanel.add(viewRecipesButton);
        recipeManagementPanel.add(planMealButton);
        recipeManagementPanel.add(viewMealPlanButton);
        recipeManagementPanel.add(viewShoppingListButton);
    
        // Panel for Additional Actions
        JPanel additionalActionsPanel = new JPanel();
        additionalActionsPanel.setLayout(new FlowLayout());
        additionalActionsPanel.add(createShoppingListButton);
        additionalActionsPanel.add(clearMealPlanButton);
        additionalActionsPanel.add(manageFavoritesButton);
        additionalActionsPanel.add(copyRecipeButton);
        recipeManagementPanel.add(logInButton);
recipeManagementPanel.add(logOutButton);
additionalActionsPanel.add(saveAndExitButton);

    
        // Add panels to tabs
        tabbedPane.addTab("Recipe Management", recipeManagementPanel);
        tabbedPane.addTab("Additional Actions", additionalActionsPanel);
    
        // Add the tabbed pane to the main content pane
        add(tabbedPane, BorderLayout.CENTER);
    }
    
    private void showLogIn() {
        // If already logged in, show a message
        if (authenticationManager.getAuthenticatedUser() != null) {
            JOptionPane.showMessageDialog(this, "You are already logged in as " + authenticationManager.getAuthenticatedUser().getUsername());
        } else {
            // Replace this with the logic for logging in
            String username = JOptionPane.showInputDialog(this, "Enter your username:");
            String password = JOptionPane.showInputDialog(this, "Enter your password:");
    
            authenticationManager.authenticateUser(username, password);
    
            // Update the UI based on the authentication status
            if (authenticationManager.getAuthenticatedUser() != null) {
                mainPanel.removeAll();
                mainPanel.add(new JLabel("Welcome, " + username + "!\nYou are now logged in."));
            } else {
                JOptionPane.showMessageDialog(this, "Login failed. Please check your credentials.");
            }
    
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
    private void showSaveAndExit() {
        // Check if the user is logged in before saving and exiting
        if (authenticationManager.getAuthenticatedUser() != null) {
            // Replace this with the logic for saving and exiting
            mainPanel.removeAll();
            mainPanel.add(new JLabel("Save and Exit Panel"));
            mainPanel.revalidate();
            mainPanel.repaint();
        } else {
            JOptionPane.showMessageDialog(this, "You need to log in first.");
        }
    }
    private void showLogOut() {
        // If not logged in, show a message
        if (authenticationManager.getAuthenticatedUser() == null) {
            JOptionPane.showMessageDialog(this, "You are not logged in.");
        } else {
            // Replace this with the logic for logging out
            authenticationManager.logout();
    
            mainPanel.removeAll();
            mainPanel.add(new JLabel("Logged out successfully."));
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
    private void showCreateShoppingList() {
        // Replace this with the logic for creating a shopping list
        mainPanel.removeAll();
        mainPanel.add(new JLabel("Create Shopping List Panel"));
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void showClearMealPlan() {
        // Replace this with the logic for clearing a meal plan
        mainPanel.removeAll();
        mainPanel.add(new JLabel("Clear Meal Plan Panel"));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private void showManageFavorites() {
        // Replace this with the logic for managing favorites
        mainPanel.removeAll();
        mainPanel.add(new JLabel("Manage Favorites Panel"));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private void showCopyRecipe() {
        // Replace this with the logic for copying a recipe
        mainPanel.removeAll();
        mainPanel.add(new JLabel("Copy Recipe Panel"));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    private void addEventListeners() {
        viewRecipesButton.addActionListener(e -> showViewRecipes());
        addRecipeButton.addActionListener(e -> showAddRecipe());
        planMealButton.addActionListener(e -> showPlanMeal());
        viewMealPlanButton.addActionListener(e -> showViewMealPlan());
        viewShoppingListButton.addActionListener(e -> showViewShoppingList());
        logInButton.addActionListener(e -> showLogIn());
    saveAndExitButton.addActionListener(e -> showSaveAndExit());
    logOutButton.addActionListener(e -> showLogOut());

    createShoppingListButton.addActionListener(e -> showCreateShoppingList());
    clearMealPlanButton.addActionListener(e -> showClearMealPlan());
    manageFavoritesButton.addActionListener(e -> showManageFavorites());
    copyRecipeButton.addActionListener(e -> showCopyRecipe());
    }

    private void showViewRecipes() {
        List<Recipe> recipes = recipeManager.getRecipes();
    
        // Create a text area to display recipes
        JTextArea recipesTextArea = new JTextArea();
        recipesTextArea.setEditable(false);
    
        if (recipes.isEmpty()) {
            recipesTextArea.setText("No recipes available.");
        } else {
            // Display each recipe
            for (Recipe recipe : recipes) {
                recipesTextArea.append(recipe.getTitle() + " - " + recipe.getDescription() + "\n");
            }
        }
    
        // Replace the content of the main panel with the text area
        mainPanel.removeAll();
        mainPanel.add(new JScrollPane(recipesTextArea));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    

    private void showAddRecipe() {
        // Create components for input
        JTextField titleField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField ingredientsField = new JTextField();
    
        // Display an input dialog to get recipe information
        int result = JOptionPane.showConfirmDialog(
                this,
                new Object[]{"Title:", titleField, "Description:", descriptionField, "Ingredients:", ingredientsField},
                "Add Recipe",
                JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            // Extract input values
            String title = titleField.getText();
            String description = descriptionField.getText();
            String ingredientsInput = ingredientsField.getText();
            List<String> ingredients = List.of(ingredientsInput.split("\\s*,\\s*"));
    
            // Create a new recipe
            Recipe newRecipe = new Recipe(title, description, ingredients);
    
            // Add the new recipe using the RecipeManager instance
            recipeManager.addRecipe(newRecipe);
    
            // Display a success message
            JOptionPane.showMessageDialog(this, "Recipe added successfully!");
        }
        // If the user clicks "Cancel" or closes the dialog, do nothing
    }
    

    private void showPlanMeal() {
        List<Recipe> recipes = recipeManager.getRecipes();
    
        if (recipes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No recipes available for meal planning.");
            return;
        }
    
        // Create a combo box with recipe titles
        JComboBox<String> recipeComboBox = new JComboBox<>();
        for (Recipe recipe : recipes) {
            recipeComboBox.addItem(recipe.getTitle());
        }
    
        // Display an input dialog to get meal planning information
        int result = JOptionPane.showConfirmDialog(
                this,
                new Object[]{"Select a recipe:", recipeComboBox},
                "Plan Meal",
                JOptionPane.OK_CANCEL_OPTION);
    
        if (result == JOptionPane.OK_OPTION) {
            // Extract the selected recipe title
            String selectedRecipeTitle = (String) recipeComboBox.getSelectedItem();
    
            // Find the selected recipe
            Recipe selectedRecipe = recipeManager.getRecipeByTitle(selectedRecipeTitle);
    
            // Get the day for the meal plan
            String day = JOptionPane.showInputDialog(this, "Enter the day for the meal plan:");
    
            // Plan the meal using the MealPlanManager instance
            mealPlanManager.planMeal(day, selectedRecipe);
    
            // Display a success message
            JOptionPane.showMessageDialog(this, "Meal planned successfully!");
        }
        // If the user clicks "Cancel" or closes the dialog, do nothing
    }
    
    private void showViewMealPlan() {
        // Get the day for which to view the meal plan
        String day = JOptionPane.showInputDialog(this, "Enter the day to view the meal plan:");
    
        // Get the planned meal for the specified day using the MealPlanManager
        Recipe plannedMeal = mealPlanManager.getPlannedMeal(day);
    
        // Create a text area to display the meal plan
        JTextArea mealPlanTextArea = new JTextArea();
        mealPlanTextArea.setEditable(false);
    
        if (plannedMeal != null) {
            mealPlanTextArea.setText("Planned meal for " + day + ":\n" +
                    plannedMeal.getTitle() + " - " + plannedMeal.getDescription());
        } else {
            mealPlanTextArea.setText("No planned meal found for " + day + ".");
        }
    
        // Replace the content of the main panel with the text area
        mainPanel.removeAll();
        mainPanel.add(new JScrollPane(mealPlanTextArea));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    private void showViewShoppingList() {
        // Get the shopping lists from the RecipeManager
        List<String> shoppingLists = recipeManager.getShoppingLists();
    
        // Create a text area to display the shopping lists
        JTextArea shoppingListTextArea = new JTextArea();
        shoppingListTextArea.setEditable(false);
    
        if (shoppingLists.isEmpty()) {
            shoppingListTextArea.setText("No shopping lists available.");
        } else {
            // Display each shopping list
            for (int i = 0; i < shoppingLists.size(); i++) {
                shoppingListTextArea.append((i + 1) + ". " + shoppingLists.get(i) + "\n");
            }
        }
    
        // Replace the content of the main panel with the text area
        mainPanel.removeAll();
        mainPanel.add(new JScrollPane(shoppingListTextArea));
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainUI().setVisible(true));
    }
}
