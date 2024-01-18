package RecipeManagementSystem.manager;

public class ConcreteUserAuthManagerFactory implements UserAuthManagerFactory {
    @Override
    public UserAuthenticationManager createAuthManager() {
        return UserAuthenticationManager.getInstance();
    }
}
