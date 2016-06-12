package net.studio24.blackbird;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 *
 */
@Theme("blackbird")
@Widgetset("net.studio24.blackbird.BlackbirdWidgetset")
public class BlackbirdUI extends UI {

	private static final Logger LOGGER = LoggerFactory.getLogger(BlackbirdUI.class);

	@Override
	protected void init(VaadinRequest vaadinRequest) {
		LOGGER.debug("Initializing application UI...");

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

		@Override
		public void init(ServletConfig servletConfig) throws ServletException {
			LOGGER.debug("Initializing application servlet...");
			super.init(servletConfig);
		}

	}
}
