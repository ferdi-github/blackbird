package net.studio24.blackbird.session;

import net.studio24.blackbird.session.impl.ShiroSessionServiceImpl;

/**
 * Service that provides session related functionality.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public interface SessionService {

    static final SessionService instance = new ShiroSessionServiceImpl();

    public static SessionService get() {
        return instance;
    }

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
     * Checks if the current session is remembered.
     * 
     * @return the remember state
     */
    public boolean isRemembered();

    /**
     * Get the username of the remembered session.
     * 
     * @return the username or null if the session is not remembered
     */
    public String getRememberedUsername();

    /**
     * Check if the current session (user) has the requested permission.
     * 
     * @param permission
     *            the permission to check
     * @return {@code true} if the user is permitted, {@code false} otherwise
     */
    public boolean hasPermission(String permission);

}
