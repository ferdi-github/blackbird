package net.studio24.blackbird.session.impl;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.realm.AuthenticatingRealm;

/**
 * Placeholder realm for authentication.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class AuthenticationRealm extends AuthenticatingRealm {

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return new SimpleAccount(token.getPrincipal(), token.getCredentials(), "AUTH");
    }

}
