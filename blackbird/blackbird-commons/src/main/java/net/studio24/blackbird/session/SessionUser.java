package net.studio24.blackbird.session;

/**
 * Represents the user that is bound to the current session.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class SessionUser {

    private final String username;

    public SessionUser(String username) {
        super();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
