package RecipeManagementSystem.model;

import java.util.List;

public interface SortingStrategy {
    void sort(List<Recipe> recipes);
}
