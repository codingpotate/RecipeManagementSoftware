package RecipeManagementSystem.model;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() {
        return this.username; // Replace 'username' with the actual field name for the username in your User class
    }
}