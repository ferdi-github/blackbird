package net.studio24.blackbird.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.server.FontIcon;

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

}
