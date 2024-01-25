package RecipeManagementSystem.manager.test;

import RecipeManagementSystem.manager.ConcreteRecipeManagerFactory;
import RecipeManagementSystem.manager.RecipeManagerFactory;
import RecipeManagementSystem.manager.RecipeManager;

public class FactoryMethodPatternTest {
    public static void main(String[] args) {
        // Test Factory Method
        RecipeManagerFactory factory = new ConcreteRecipeManagerFactory();
        RecipeManager recipeManager = factory.createRecipeManager();
    }
}
