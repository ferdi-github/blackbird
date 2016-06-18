package net.studio24.blackbird.screens;

import java.util.HashMap;
import java.util.Map;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.server.Resource;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.Command;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Navigation menu presenting a list of available views to the user.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class ViewMenu extends CssLayout {

    private Navigator navigator;
    private Map<String, MenuItem> viewItems = new HashMap<String, MenuItem>();

    private MenuBar viewMenuBar;

    public ViewMenu(Navigator navigator) {
        this.navigator = navigator;

        viewMenuBar = new MenuBar();
        viewMenuBar.addStyleName(ValoTheme.MENUBAR_BORDERLESS);

        addComponent(viewMenuBar);
    }

    /**
     * Register a pre-created view instance in the navigation menu and in the
     * {@link Navigator}.
     *
     * @see Navigator#addView(String, View)
     *
     * @param view
     *            view instance to register
     * @param name
     *            view name
     * @param caption
     *            view caption in the menu
     * @param icon
     *            view icon in the menu
     */
    public void addView(View view, final String name, String caption,
            Resource icon) {
        navigator.addView(name, view);
        createViewItem(name, caption, icon);
    }

    /**
     * Register a view in the navigation menu and in the {@link Navigator} based
     * on a view class.
     *
     * @see Navigator#addView(String, Class)
     *
     * @param viewClass
     *            class of the views to create
     * @param name
     *            view name
     * @param caption
     *            view caption in the menu
     * @param icon
     *            view icon in the menu
     */
    public void addView(Class<? extends View> viewClass, final String name,
            String caption, Resource icon) {
        navigator.addView(name, viewClass);
        createViewItem(name, caption, icon);
    }

    private void createViewItem(final String name, String caption, Resource icon) {
        MenuItem viewItem = viewMenuBar.addItem(caption, icon, new Command() {

            @Override
            public void menuSelected(MenuItem selectedItem) {
                navigator.navigateTo(name);
            }
        });
        viewItems.put(name, viewItem);
    }

    /**
     * Highlights a view navigation item as the currently active view in the
     * menu. This method does not perform the actual navigation.
     *
     * @param viewName
     *            the name of the view to show as active
     */
    public void setActiveView(String viewName) {
        for (MenuItem item : viewItems.values()) {
            item.setEnabled(true);
        }
        MenuItem selected = viewItems.get(viewName);
        if (selected != null) {
            selected.setEnabled(false);
        }
    }
}
