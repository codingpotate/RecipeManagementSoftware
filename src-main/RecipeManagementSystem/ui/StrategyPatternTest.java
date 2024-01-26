// StrategyPatternTest.java
package RecipeManagementSystem.ui;

import RecipeManagementSystem.model.CreationDateSort;
import RecipeManagementSystem.model.Recipe;
import RecipeManagementSystem.model.RecipeSorter;
import RecipeManagementSystem.model.TitleSort;
import RecipeManagementSystem.model.decorators.RecipeWithCreationDateDecorator;

import java.util.ArrayList;
import java.util.List;

public class StrategyPatternTest {
    public static void main(String[] args) {
        // Create a list of recipes (replace this with your actual list)
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Amama", "Healthy salad", List.of("Lettuce", "Tomato", "Cucumber"))));
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Salad", "Healthy salad", List.of("Lettuce", "Tomato", "Cucumber"))));
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Yasta", "Delicious pasta dish", List.of("Pasta", "Tomato sauce"))));
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Zake", "Sweet dessert", List.of("Flour", "Sugar", "Eggs"))));
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Cake", "Sweet dessert", List.of("Flour", "Sugar", "Eggs"))));
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Jake", "Sweet dessert", List.of("Flour", "Sugar", "Eggs"))));
        recipes.add(new RecipeWithCreationDateDecorator(new Recipe("Lake", "Sweet dessert", List.of("Flour", "Sugar", "Eggs"))));
        // Test with different strategies
        RecipeSorter sorter = new RecipeSorter();

        System.out.println("Sorting by Title:");
        sorter.setSortingStrategy(new TitleSort());
        sorter.performSort(recipes);
        recipes.forEach(recipe -> System.out.println(recipe.getTitle()));

    }
}
