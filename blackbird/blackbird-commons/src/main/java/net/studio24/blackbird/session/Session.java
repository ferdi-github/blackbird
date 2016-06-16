package net.studio24.blackbird.session;

import org.apache.shiro.SecurityUtils;

/**
 * Represents a user session. Provides access to session scoped information.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public final class Session {

    /**
     * Key that identifies the session user in the list of session attributes.
     */
    public static final String USER_KEY = "session.user";

    private Session() {
        super();
    }

    /**
     * Get the user that is bound to the current session.
     * 
     * @return the user or null if no user is bound (or no session exists)
     */
    public static SessionUser getCurrentUser() {
        SessionUser sessionUser = (SessionUser) SecurityUtils.getSubject().getSession(false).getAttribute(USER_KEY);
        return sessionUser;
    }

}
