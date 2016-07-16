package net.studio24.blackbird.ui.sampler;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;

import net.studio24.blackbird.ui.dialogs.UnexcpectedErrorDialog;

/**
 * A view that provides samples of application UX.
 */
public class UxSamplerView extends VerticalLayout implements View {

    static final String VIEW_ID = "uxSampler";
    static final String VIEW_NAME = "UX Sampler";

    public UxSamplerView() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label(VIEW_NAME);
        header.addStyleName(Reindeer.LABEL_H1);
        addComponent(header);

        Button showErrorDialog = new Button("Show error dialog", FontAwesome.WARNING);
        showErrorDialog.addClickListener((e) -> UnexcpectedErrorDialog.showForMessage(
                "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. "
                        + "Aenean commodo ligula eget dolor. Aenean massa. "
                        + "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus."));
        addComponent(showErrorDialog);

        Button openWindowButton = new Button("Open window", FontAwesome.DESKTOP);
        openWindowButton.addClickListener(new ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                TestWindow window = new TestWindow();
                window.center();
                UI.getCurrent().addWindow(window);
            }
        });
        addComponent(openWindowButton);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // nothing
    }

}
