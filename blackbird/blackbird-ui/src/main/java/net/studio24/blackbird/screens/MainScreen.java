package net.studio24.blackbird.screens;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import net.studio24.blackbird.ErrorView;
import net.studio24.blackbird.SampleViewA;
import net.studio24.blackbird.SampleViewB;
import net.studio24.blackbird.SampleViewDefault;

/**
 * Screen that provides the frame for a menu and application views.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class MainScreen extends VerticalLayout {

    private ViewMenu viewMenu;

    public MainScreen() {
        buildUI();
    }

    private void buildUI() {
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("view-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(UI.getCurrent(), viewContainer);
        navigator.setErrorView(ErrorView.class);
        navigator.addViewChangeListener(viewChangeListener);

        Label appTitleLabel = new Label("Blackbird");
        appTitleLabel.addStyleName(ValoTheme.LABEL_H1);
        appTitleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        appTitleLabel.addStyleName("title");
        appTitleLabel.setWidthUndefined();

        Button logoutButton = new Button("Logout", FontAwesome.SIGN_OUT);
        logoutButton.addStyleName(ValoTheme.BUTTON_QUIET);

        logoutButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                VaadinSession.getCurrent().getSession().invalidate();
                Page.getCurrent().reload();
            }
        });

        viewMenu = new ViewMenu(navigator);
        viewMenu.addView(new SampleViewDefault(), SampleViewDefault.VIEW_NAME, "Default", FontAwesome.EDIT);
        viewMenu.addView(new SampleViewA(), SampleViewA.VIEW_NAME, SampleViewA.VIEW_NAME, FontAwesome.EDIT);
        viewMenu.addView(new SampleViewB(), SampleViewB.VIEW_NAME, SampleViewB.VIEW_NAME, FontAwesome.INFO_CIRCLE);

        Label spacer = new Label(" ");
        spacer.setWidth(21, Unit.PIXELS);

        final MenuBar userMenu = new MenuBar();
        userMenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

        final MenuItem userItem = userMenu.addItem("", FontAwesome.USER, null);
        userItem.setStyleName(ValoTheme.BUTTON_ICON_ONLY);

        userMenu.addItem("Logout", FontAwesome.SIGN_OUT, null);

        CssLayout toolbarLayout = new CssLayout(viewMenu, spacer, userMenu);
        toolbarLayout.setWidth(100, Unit.PERCENTAGE);
        toolbarLayout.addStyleName("toolbar");

        CssLayout headerLayout = new CssLayout(appTitleLabel, toolbarLayout);
        headerLayout.setWidth(100, Unit.PERCENTAGE);
        headerLayout.addStyleName("header");

        addComponent(headerLayout);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            viewMenu.setActiveView(event.getViewName());
        }

    };

}
