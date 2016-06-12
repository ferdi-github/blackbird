package net.studio24.blackbird;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * A sample view.
 */
public class SampleViewDefault extends VerticalLayout implements View {

    public static final String VIEW_NAME = "";

    public SampleViewDefault() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label("View Default");
        header.addStyleName(Reindeer.LABEL_H1);
        addComponent(header);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // nothing
    }

}
