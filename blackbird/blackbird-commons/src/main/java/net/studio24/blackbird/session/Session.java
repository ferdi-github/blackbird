package net.studio24.blackbird.session;

public final class Session {

    private Session() {
        super();
    }

    public static SessionUser getCurrentUser() {
        return new SessionUser();
    }

}
