package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.User;

public class UserAuthenticationManager {
    private static UserAuthenticationManager instance;
    private User authenticatedUser;

    private UserAuthenticationManager() {

    }

    public static synchronized UserAuthenticationManager getInstance() {
        if (instance == null) {
            instance = new UserAuthenticationManager();
        }
        return instance;
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    public void authenticateUser(String username, String password) {
        if (username.equals("admin") && password.equals("admin")) {
            authenticatedUser = new User(username, password);
            System.out.println("Authentication successful!");
        } else {
            System.out.println("Authentication failed. Incorrect username or password.");
        }
    }

    public void logout() {
        authenticatedUser = null;
        System.out.println("Logged out successfully.");
    }
}
