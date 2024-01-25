// RecipeDecorator.java
package RecipeManagementSystem.model.decorators;

import RecipeManagementSystem.model.Recipe;
import java.io.Serializable;
import java.util.Date;

public abstract class RecipeDecorator extends Recipe implements Serializable {
    private Recipe recipe;

    public RecipeDecorator(Recipe recipe) {
        super(recipe.getTitle(), recipe.getDescription(), recipe.getIngredients());
        this.recipe = recipe;
    }

    // Override methods to delegate to the original recipe
    @Override
    public Date getCreationDate() {
        return recipe.getCreationDate();
    }

    @Override
    public void setCreationDate(Date creationDate) {
        recipe.setCreationDate(creationDate);
    }
}
