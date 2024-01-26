package RecipeManagementSystem.manager;

import RecipeManagementSystem.model.User;

public class UserAuthenticationManagerTest {

    public static void main(String[] args) {
        testAuthenticationSuccess();
        testAuthenticationFailure();
        testLogout();
    }

    private static void testAuthenticationSuccess() {
        UserAuthenticationManager authManager = UserAuthenticationManager.getInstance();

        // Test successful authentication
        authManager.authenticateUser("admin", "admin");

        // Check if the authenticated user is not null
        User authenticatedUser = authManager.getAuthenticatedUser();
        assert authenticatedUser != null : "Authentication failed for correct credentials.";

        // Check if the username and password match
        assert authenticatedUser.getUsername().equals("admin") : "Incorrect username after authentication.";
        assert authenticatedUser.getPassword().equals("admin") : "Incorrect password after authentication.";

        System.out.println("Test: Authentication Success - Passed");
    }

    private static void testAuthenticationFailure() {
        UserAuthenticationManager authManager = UserAuthenticationManager.getInstance();

        // Test authentication failure
        authManager.authenticateUser("incorrectUser", "incorrectPassword");

        // Check if the authenticated user is null
        User authenticatedUser = authManager.getAuthenticatedUser();
        assert authenticatedUser == null : "Authentication succeeded for incorrect credentials.";

        System.out.println("Test: Authentication Failure - Passed");
    }

    private static void testLogout() {
        UserAuthenticationManager authManager = UserAuthenticationManager.getInstance();

        // Authenticate user before logout
        authManager.authenticateUser("admin", "admin");

        // Test logout
        authManager.logout();

        // Check if the authenticated user is null after logout
        User authenticatedUser = authManager.getAuthenticatedUser();
        assert authenticatedUser == null : "Logout failed. Authenticated user not null after logout.";

        System.out.println("Test: Logout - Passed");
    }
}
