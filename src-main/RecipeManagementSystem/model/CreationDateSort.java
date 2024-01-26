// CreationDateSort.java
package RecipeManagementSystem.model;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import RecipeManagementSystem.model.decorators.RecipeDecorator;
import RecipeManagementSystem.model.decorators.RecipeWithCreationDateDecorator;
public class CreationDateSort implements SortingStrategy {
    @Override
    public void sort(List<Recipe> recipes) {
        // Implementation for sorting by creation date

        Collections.sort(recipes, Comparator.nullsLast(Comparator.comparing(recipe -> {
            Date creationDate;

            if (recipe instanceof RecipeDecorator) {
                creationDate = ((RecipeDecorator) recipe).getCreationDate();
            } else {
                creationDate = recipe.getCreationDate();
            }

            return creationDate;
        })));
    }
}
