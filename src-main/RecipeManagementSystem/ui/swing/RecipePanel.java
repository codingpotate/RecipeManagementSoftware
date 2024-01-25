package RecipeManagementSystem.ui.swing;

import RecipeManagementSystem.model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RecipePanel extends JPanel {

    private JLabel titleLabel;
    private JTextArea descriptionTextArea;
    private JTextArea ingredientsTextArea;
    private JButton favoriteButton;

    public RecipePanel() {
        initializeUI();
        createComponents();
        setUpLayout();
    }

    private void initializeUI() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEtchedBorder());
    }

    private void createComponents() {
        titleLabel = new JLabel();
        descriptionTextArea = new JTextArea();
        ingredientsTextArea = new JTextArea();
        favoriteButton = new JButton("Add to Favorites");
    }

    private void setUpLayout() {
        add(titleLabel);
        add(descriptionTextArea);
        add(new JLabel("Ingredients:"));
        add(ingredientsTextArea);
        add(favoriteButton);
    }

    public void displayRecipe(Recipe recipe) {
        titleLabel.setText(recipe.getTitle());
        descriptionTextArea.setText(recipe.getDescription());
        ingredientsTextArea.setText(String.join(", ", recipe.getIngredients()));

        // Customize this part based on your actual logic for marking as favorite
        favoriteButton.addActionListener(e -> {
            // Handle adding to favorites logic here
            JOptionPane.showMessageDialog(this, "Recipe added to favorites!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(400, 300);

                RecipePanel recipePanel = new RecipePanel();
                Recipe sampleRecipe = new Recipe(
                        "Sample Recipe",
                        "This is a sample recipe description.",
                        List.of("Ingredient 1", "Ingredient 2", "Ingredient 3")
                );
                recipePanel.displayRecipe(sampleRecipe);

                frame.add(recipePanel);
                frame.setVisible(true);
            }
        });
    }
}
