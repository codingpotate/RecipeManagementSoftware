package RecipeManagementSystem.ui;

import RecipeManagementSystem.model.Observer;
import RecipeManagementSystem.model.Subject;

public class RecipeUI implements Observer {
    private int id;
    private Subject subject;

    public RecipeUI(int id, Subject subject) {
        this.id = id;
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update(String message) {
        System.out.println("Observer " + id + " received update: " + message);

        // Additional logic for UI update based on the message...
        if (message.equals("RecipeAdded")) {
            System.out.println("A new recipe has been added! Refreshing recipe list...");
            // Trigger a UI update to display the new recipe in the list
            // You may call methods or update variables related to your UI here
        } else if (message.equals("RecipeDeleted")) {
            System.out.println("A recipe has been deleted! Removing from the UI...");
            // Trigger a UI update to remove the deleted recipe from the list
            // You may call methods or update variables related to your UI here
        }
    }

}
