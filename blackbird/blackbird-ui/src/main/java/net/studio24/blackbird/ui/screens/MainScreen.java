package net.studio24.blackbird.ui.screens;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import net.studio24.blackbird.ui.views.ApplicationView;
import net.studio24.blackbird.ui.views.DefaultView;
import net.studio24.blackbird.ui.views.ErrorView;
import net.studio24.blackbird.ui.views.ViewRegistry;

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

        viewMenu = new ViewMenu(navigator);
        viewMenu.addView(new DefaultView(), DefaultView.VIEW_ID, DefaultView.VIEW_NAME, FontAwesome.HOME);

        // TODO define order
        for (ApplicationView view : ViewRegistry.getRegisteredViews()) {
            viewMenu.addView(view.getViewClass(), view.getId(), view.getTitle(), view.getIcon());
        }

        Label spacer = new Label(" ");
        spacer.setWidth(21, Unit.PIXELS);

        final MenuBar userMenu = new MenuBar();
        userMenu.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

        final MenuItem userItem = userMenu.addItem("", FontAwesome.USER, null);
        userItem.setStyleName(ValoTheme.BUTTON_ICON_ONLY);

        userMenu.addItem("Logout", FontAwesome.SIGN_OUT, new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                VaadinSession.getCurrent().getSession().invalidate();
                Page.getCurrent().reload();
            }
        });

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
