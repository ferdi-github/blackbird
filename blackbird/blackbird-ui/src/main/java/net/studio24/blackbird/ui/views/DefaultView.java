package net.studio24.blackbird.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * The default view.
 *
 * @author ferdi-github
 * @since 1.0
 */
public class DefaultView extends VerticalLayout implements View {

    public static final String VIEW_ID = "";
    public static final String VIEW_NAME = "Overview";

    public DefaultView() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label("Default View");
        header.addStyleName(Reindeer.LABEL_H1);
        addComponent(header);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // nothing
    }

}
