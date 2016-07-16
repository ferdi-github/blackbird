package net.studio24.blackbird.ui.dialogs;

import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class AbstractDialog extends Window {

    private VerticalLayout contentLayout;
    private HorizontalLayout buttonsLayout;

    public AbstractDialog(String caption) {
        super(caption);
        addCloseShortcut(KeyCode.ESCAPE);
        buildUI();
    }

    private void buildUI() {
        contentLayout = new VerticalLayout();
        contentLayout.addStyleName("content");
        contentLayout.setMargin(true);
        contentLayout.setSizeFull();

        CssLayout buttonsExpander = new CssLayout();
        buttonsExpander.setSizeFull();

        buttonsLayout = new HorizontalLayout(buttonsExpander);
        buttonsLayout.addStyleName("buttons");
        buttonsLayout.setWidth(100, Unit.PERCENTAGE);
        buttonsLayout.setSpacing(true);
        buttonsLayout.setMargin(true);
        buttonsLayout.setDefaultComponentAlignment(Alignment.TOP_RIGHT);
        buttonsLayout.setExpandRatio(buttonsExpander, 1f);

        VerticalLayout windowLayout = new VerticalLayout(contentLayout, buttonsLayout);
        windowLayout.setSizeFull();
        windowLayout.setExpandRatio(contentLayout, 1f);
        setContent(windowLayout);
    }

    public void setDialogContent(Component content) {
        contentLayout.addComponent(content);
    }

    public void setCloseButton() {
        Button closeButton = new Button("Close", FontAwesome.CLOSE);
        closeButton.addClickListener((e) -> close());
        buttonsLayout.addComponent(closeButton);
    }

    @Override
    public void attach() {
        super.attach();
        focus();
    }

}
