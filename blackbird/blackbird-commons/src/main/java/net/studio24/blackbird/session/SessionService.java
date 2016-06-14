package net.studio24.blackbird.session;

public interface SessionService {

    /**
     * Sign in with a given username and password.
     * 
     * @param username
     *            the username
     * @param password
     *            the password
     * @return {@code true} if the principal matches the credentials
     */
    public boolean signIn(String username, String password);

    /**
     * Invalidates the current session.
     */
    public void signOut();

    /**
     * Checks if the current session is authenticated.
     * 
     * @return the authentication state
     */
    public boolean isAuthenticated();

    /**
     * Check if the current session (user) has the requested permission.
     * 
     * @param permission
     *            the permission to check
     * @return {@code true} if the user is permitted, {@code false} otherwise
     */
    public boolean hasPermission(String permission);

}
