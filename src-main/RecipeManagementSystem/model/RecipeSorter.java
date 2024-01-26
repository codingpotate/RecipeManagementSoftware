package RecipeManagementSystem.model;

import java.util.List;
import java.util.Collections;
public class RecipeSorter {
    private SortingStrategy sortingStrategy;

    public void setSortingStrategy(SortingStrategy sortingStrategy) {
        this.sortingStrategy = sortingStrategy;
    }

    public void performSort(List<Recipe> recipes) {
        if (sortingStrategy != null) {
            sortingStrategy.sort(recipes);
        }
    }
}
