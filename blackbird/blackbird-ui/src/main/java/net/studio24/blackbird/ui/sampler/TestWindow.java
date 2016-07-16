package net.studio24.blackbird.ui.sampler;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class TestWindow extends Window {

    public TestWindow() {
        super("A test window");
        buidUI();
    }

    private void buidUI() {
        setWidth(800, Unit.PIXELS);
        setHeight(600, Unit.PIXELS);
        setResizable(false);

        addCloseShortcut(KeyCode.ESCAPE);

        Button cancelButton = new Button("Cancel", FontAwesome.CLOSE);
        Button okButton = new Button("OK", FontAwesome.CHECK);
        okButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        CssLayout expander = new CssLayout();
        expander.setSizeFull();

        VerticalLayout contentLayout = new VerticalLayout();
        contentLayout.addStyleName("content");
        contentLayout.setSizeFull();

        HorizontalLayout buttonsLayout = new HorizontalLayout(expander, okButton, cancelButton);
        buttonsLayout.addStyleName("buttons");
        buttonsLayout.setWidth(100, Unit.PERCENTAGE);
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);
        buttonsLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        buttonsLayout.setExpandRatio(expander, 1f);

        VerticalLayout windowLayout = new VerticalLayout(contentLayout, buttonsLayout);
        windowLayout.setSizeFull();
        windowLayout.setExpandRatio(contentLayout, 1f);
        setContent(windowLayout);

        FormLayout f = new FormLayout();
        // f.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        f.setMargin(true);
        f.setSizeFull();

        Label h2 = new Label("Header 2");
        h2.addStyleName(ValoTheme.LABEL_COLORED);
        h2.addStyleName(ValoTheme.LABEL_H2);
        f.addComponent(h2);
        f.addComponent(new TextField("Field 1"));

        TextArea tt = new TextArea("Field 2");
        tt.setRows(4);
        tt.setWidth(100, Unit.PERCENTAGE);
        f.addComponent(tt);

        Label h3 = new Label("Header 3");
        h3.addStyleName(ValoTheme.LABEL_H3);
        f.addComponent(h3);

        f.addComponent(new DateField("Field 3"));

        contentLayout.addComponent(f);
    }

    @Override
    public void attach() {
        super.attach();
        focus();
    }

}
