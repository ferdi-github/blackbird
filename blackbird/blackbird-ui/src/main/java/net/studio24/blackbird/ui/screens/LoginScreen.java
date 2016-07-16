package net.studio24.blackbird.ui.screens;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import net.studio24.blackbird.i18n.I18n;
import net.studio24.blackbird.session.SessionService;

/**
 * Screen that provides a login mask.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class LoginScreen extends CssLayout {

    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    private LoginListener loginListener;

    public LoginScreen(LoginListener loginListener) {
        this.loginListener = loginListener;
        buildUI();

        if (SessionService.get().isRemembered()) {
            usernameField.setValue(SessionService.get().getRememberedUsername());
            passwordField.focus();
        } else {
            usernameField.focus();
        }
    }

    private void buildUI() {
        addStyleName("login-screen");

        Label appTitleLabel = new Label("Blackbird");
        appTitleLabel.addStyleName(ValoTheme.LABEL_H1);
        appTitleLabel.addStyleName("title");
        appTitleLabel.setWidthUndefined();

        CssLayout headerLayout = new CssLayout(appTitleLabel);
        headerLayout.addStyleName("header");

        Component loginPanel = buildLoginForm();

        Label sourceRefLabel = new Label(
                "Sources on <a href='https://github.com/ferdi-github/blackbird' target='_blank'>GitHub</a>",
                ContentMode.HTML);
        sourceRefLabel.setWidthUndefined();

        Label copyrightLabel = new Label("© 2016 studio24");
        copyrightLabel.setWidthUndefined();

        CssLayout footerLayout = new CssLayout(sourceRefLabel, copyrightLabel);
        footerLayout.addStyleName("footer");

        addComponent(headerLayout);
        addComponent(loginPanel);
        addComponent(footerLayout);
    }

    private Component buildLoginForm() {
        VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSpacing(true);

        loginPanel.addStyleName("login-form");
        loginPanel.setSizeUndefined();
        loginPanel.setMargin(true);

        usernameField = new TextField(LoginMessages.get().username());
        usernameField.setIcon(FontAwesome.USER);
        usernameField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        usernameField.setWidth(15, Unit.EM);
        loginPanel.addComponent(usernameField);

        passwordField = new PasswordField(LoginMessages.get().password());
        passwordField.setIcon(FontAwesome.LOCK);
        passwordField.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        passwordField.setWidth(15, Unit.EM);
        loginPanel.addComponent(passwordField);

        CssLayout buttons = new CssLayout();
        buttons.setStyleName("buttons");
        loginPanel.addComponent(buttons);

        buttons.addComponent(loginButton = new Button(LoginMessages.get().login(), FontAwesome.SIGN_IN));
        loginButton.setDisableOnClick(true);
        loginButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    login();
                } finally {
                    loginButton.setEnabled(true);
                }
            }
        });
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.addStyleName(ValoTheme.BUTTON_PRIMARY);

        VerticalLayout centeringLayout = new VerticalLayout(loginPanel);
        centeringLayout.setComponentAlignment(loginPanel, Alignment.MIDDLE_CENTER);
        return centeringLayout;
    }

    private void login() {
        if (StringUtils.isBlank(usernameField.getValue()) || StringUtils.isBlank(passwordField.getValue())) {
            return;
        }
        if (SessionService.get().signIn(usernameField.getValue(), passwordField.getValue())) {
            loginListener.onLoginSuccessful();
        }
    }

    public interface LoginListener extends Serializable {
        void onLoginSuccessful();
    }

    public interface LoginMessages {

        public static LoginMessages get() {
            return I18n.from(LoginMessages.class);
        }

        String username();

        String password();

        String login();
    }
}
