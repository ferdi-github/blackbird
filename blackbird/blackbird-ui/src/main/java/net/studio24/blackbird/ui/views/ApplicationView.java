package net.studio24.blackbird.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

import com.vaadin.navigator.View;
import com.vaadin.server.FontIcon;

/**
 * Interface to be implemented by all application views.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public interface ApplicationView {

    /**
     * Get the view ID. The id is used for navigation by URL.
     * 
     * @return the view ID
     */
    String getId();

    /**
     * Get the view title. The title is displayed in the view toolbar.
     * 
     * @return the view title
     */
    String getTitle();

    /**
     * Get the view icon. The title is displayed in the view icon.
     * 
     * @return the view icon
     */
    FontIcon getIcon();

    /**
     * Get the view class. The class in registered at the navigator.
     * 
     * @return the view class.
     */
    Class<? extends View> getViewClass();

    /**
     * Get all registered application views.
     * 
     * @return the list of registered application views.
     */
    public static Collection<ApplicationView> getRegisteredViews() {
        ServiceLoader<ApplicationView> loader = ServiceLoader.load(ApplicationView.class);
        List<ApplicationView> views = new ArrayList<>();
        loader.forEach((view) -> views.add(view));
        return views;
    }

}
