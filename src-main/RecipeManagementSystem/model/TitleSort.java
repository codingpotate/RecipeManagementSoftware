package RecipeManagementSystem.model;

import java.util.Collections;
import java.util.List;

public class TitleSort implements SortingStrategy {
    @Override
    public void sort(List<Recipe> recipes) {
        // Implementation for sorting by title
        Collections.sort(recipes, (r1, r2) -> r1.getTitle().compareToIgnoreCase(r2.getTitle()));
    }
}
