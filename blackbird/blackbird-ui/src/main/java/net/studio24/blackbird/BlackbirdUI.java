package net.studio24.blackbird;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 *
 */
@Theme("blackbird")
@Widgetset("net.studio24.blackbird.BlackbirdWidgetset")
public class BlackbirdUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSizeFull();
        setContent(layout);

        Label label = new Label("Blackbird");
        label.addStyleName(ValoTheme.LABEL_H1);
        label.setSizeUndefined();
        layout.addComponent(label);
        layout.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }

    @WebServlet(urlPatterns = "/*", name = "BlackbirdUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = BlackbirdUI.class, productionMode = false)
    public static class BlackbirdUIServlet extends VaadinServlet {
    }
}
