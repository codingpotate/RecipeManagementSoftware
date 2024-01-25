package RecipeManagementSystem.model.decorators;

import RecipeManagementSystem.model.Recipe;

import java.util.Date;

public class RecipeWithCreationDateDecorator extends RecipeDecorator {
    private Date creationDate;

    public RecipeWithCreationDateDecorator(Recipe recipe) {
        super(recipe);
        this.creationDate = new Date();
    }

    @Override
    public String getDescription() {
        // Add creation date to the original description
        return super.getDescription() + "\nCreated on: " + creationDate;
    }
}
