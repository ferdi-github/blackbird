package net.studio24.blackbird.session.impl;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.studio24.blackbird.session.Session;
import net.studio24.blackbird.session.SessionService;
import net.studio24.blackbird.session.SessionUser;

/**
 * A {@link SessionService} implementation that uses Apache Shiro.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class ShiroSessionServiceImpl implements SessionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShiroSessionServiceImpl.class);

    @Override
    public boolean signIn(@Nonnull String username, @Nonnull String password) {
        if (StringUtils.isBlank(username)) {
            throw new IllegalArgumentException("Username must not be null or blank");
        }
        if (StringUtils.isBlank(password)) {
            throw new IllegalArgumentException("Password must not be null or blank");
        }

        if (SecurityUtils.getSubject().isAuthenticated()) {
            SessionUser signedInUser = Session.getCurrentUser();
            if (!username.equals(signedInUser.getUsername())) {
                LOGGER.debug("Invalidating current session to allow new login");
                signOut();
            }
        }

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);

        SecurityUtils.getSubject().login(token);
        setSesionUser();

        return true;
    }

    private void setSesionUser() {
        SecurityUtils.getSubject().getSession().setAttribute(Session.USER_KEY, new SessionUser("test"));
    }

    @Override
    public void signOut() {
        SecurityUtils.getSubject().logout();
    }

    @Override
    public boolean isAuthenticated() {
        return SecurityUtils.getSubject().isAuthenticated();
    }

    @Override
    public boolean isRemembered() {
        return SecurityUtils.getSubject().isRemembered();
    }

    @Override
    public String getRememberedUsername() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered()) {
            Object principal = subject.getPrincipal();
            return (String) principal;
        }
        return null;
    }

    @Override
    public boolean hasPermission(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }

}
