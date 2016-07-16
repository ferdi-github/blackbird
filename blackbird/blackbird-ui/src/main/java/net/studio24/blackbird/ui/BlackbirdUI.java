package net.studio24.blackbird.ui;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;

import net.studio24.blackbird.session.SessionService;
import net.studio24.blackbird.ui.dialogs.UnexcpectedErrorDialog;
import net.studio24.blackbird.ui.screens.LoginScreen;
import net.studio24.blackbird.ui.screens.LoginScreen.LoginListener;
import net.studio24.blackbird.ui.screens.MainScreen;

/**
 * The application UI.
 * 
 * @author ferdi-github
 * @since 1.0
 */
@Theme("blackbird")
@Widgetset("net.studio24.blackbird.BlackbirdWidgetset")
public class BlackbirdUI extends UI {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlackbirdUI.class);

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        LOGGER.debug("Initializing application UI...");

        Responsive.makeResponsive(this);
        setLocale(vaadinRequest.getLocale());
        getPage().setTitle("Blackbird");

        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                Throwable cause = event.getThrowable();
                while (cause.getCause() != null) {
                    cause = cause.getCause();
                }
                UnexcpectedErrorDialog.showForMessage(cause.getMessage());
                LOGGER.error("Unexpected failure", event.getThrowable());
            }
        });

        if (SessionService.get().isAuthenticated()) {
            showMainView();
        } else {
            setContent(new LoginScreen(new LoginListener() {

                @Override
                public void onLoginSuccessful() {
                    showMainView();
                }
            }));
        }
    }

    protected void showMainView() {
        addStyleName(ValoTheme.UI_WITH_MENU);
        setContent(new MainScreen());
        getNavigator().navigateTo(getNavigator().getState());
    }

    /**
     * The application servlet.
     */
    @WebServlet(urlPatterns = "/*", name = "BlackbirdUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BlackbirdUI.class, productionMode = false)
    public static class BlackbirdUIServlet extends VaadinServlet {

        @Override
        public void init(ServletConfig servletConfig) throws ServletException {
            LOGGER.debug("Initializing application servlet...");
            super.init(servletConfig);
        }

    }
}
