package RecipeManagementSystem.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Recipe implements Serializable {
    private static final long serialVersionUID = 1L;

    private String title;
    private String description;
    private List<String> ingredients;
    private boolean isFavorite;
    private Date creationDate; // Change the type to Date

    public Recipe(String title, String description, List<String> ingredients) {
        this.title = title;
        this.description = description;
        this.ingredients = ingredients;
        this.isFavorite = false;
        this.creationDate = new Date(); // Set creation date to the current date and time
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public String getFormattedIngredients() {
        return String.join(", ", ingredients);
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getFormattedCreationDate() {
        return creationDate.toString();
    }
}
