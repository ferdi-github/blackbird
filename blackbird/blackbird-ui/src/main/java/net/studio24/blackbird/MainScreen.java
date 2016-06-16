package net.studio24.blackbird;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;

/**
 * Screen that provides the frame for a menu and application views.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class MainScreen extends HorizontalLayout {

    private Menu menu;

    public MainScreen() {
        buildUI();
    }

    private void buildUI() {
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(UI.getCurrent(), viewContainer);
        navigator.setErrorView(ErrorView.class);
        navigator.addViewChangeListener(viewChangeListener);

        menu = new Menu(navigator);
        menu.addView(new SampleViewDefault(), SampleViewDefault.VIEW_NAME, "Default", FontAwesome.EDIT);
        menu.addView(new SampleViewA(), SampleViewA.VIEW_NAME, SampleViewA.VIEW_NAME, FontAwesome.EDIT);
        menu.addView(new SampleViewB(), SampleViewB.VIEW_NAME, SampleViewB.VIEW_NAME, FontAwesome.INFO_CIRCLE);

        addComponent(menu);
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
            menu.setActiveView(event.getViewName());
        }

    };

}
