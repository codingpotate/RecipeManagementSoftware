package RecipeManagementSystem.manager.test;

import RecipeManagementSystem.manager.UserAuthenticationManager;

public class SingletonPatternTest {
    public static void main(String[] args) {
        UserAuthenticationManager authenticationManager1 = UserAuthenticationManager.getInstance();
        UserAuthenticationManager authenticationManager2 = UserAuthenticationManager.getInstance();

        System.out.println(authenticationManager1 == authenticationManager2); // Should print true
    }
}
