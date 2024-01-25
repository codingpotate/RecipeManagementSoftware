package RecipeManagementSystem.model.decorators.test;

import java.util.*;
import RecipeManagementSystem.model.*;
import RecipeManagementSystem.model.decorators.*;

public class DecoratorPatternTest {
    public static void main(String[] args) {
        // Create a basic recipe
        Recipe originalRecipe = new Recipe("Pasta", "Spaghetti with tomato sauce",
                Arrays.asList("Pasta", "Tomato Sauce"));

        // Decorate the recipe with a creation date
        Recipe decoratedRecipe = new RecipeWithCreationDateDecorator(originalRecipe);

        // Display original and decorated recipe details
        System.out.println("Original Recipe:");
        System.out.println(originalRecipe.getDescription());

        System.out.println("\nDecorated Recipe:");
        System.out.println(decoratedRecipe.getDescription());
    }
}
