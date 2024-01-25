package RecipeManagementSystem.manager;

public class ConcreteRecipeManagerFactory implements RecipeManagerFactory {
    @Override
    public RecipeManager createRecipeManager() {
        return RecipeManager.createRecipeManager();
    }
}
