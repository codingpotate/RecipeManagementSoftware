// RecipeWithCreationDateDecorator.java
package RecipeManagementSystem.model.decorators;

import RecipeManagementSystem.model.Recipe;

import java.util.Date;

public class RecipeWithCreationDateDecorator extends RecipeDecorator implements Comparable<RecipeWithCreationDateDecorator> {
    private Date creationDate;

    public RecipeWithCreationDateDecorator(Recipe recipe) {
        super(recipe);
        this.creationDate = new Date();
    }

    @Override
    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String getDescription() {
        // Add creation date to the original description
        return super.getDescription() + "\nCreated on: " + creationDate;
    }

    @Override
    public int compareTo(RecipeWithCreationDateDecorator other) {
        // Compare recipes based on their creation dates
        return this.creationDate.compareTo(other.creationDate);
    }
}
