package net.studio24.blackbird.ui;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

/**
 * A sample view.
 */
public class SampleViewB extends VerticalLayout implements View {

    public static final String VIEW_NAME = "vB";

    public SampleViewB() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label("View B");
        header.addStyleName(Reindeer.LABEL_H1);
        addComponent(header);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // nothing
    }

}
