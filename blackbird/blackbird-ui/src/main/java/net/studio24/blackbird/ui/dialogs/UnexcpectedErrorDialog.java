package net.studio24.blackbird.ui.dialogs;

import com.vaadin.ui.Label;
import com.vaadin.ui.UI;

public class UnexcpectedErrorDialog extends AbstractDialog {

    public static void showForMessage(String message) {
        UnexcpectedErrorDialog dialog = new UnexcpectedErrorDialog(message);
        dialog.center();
        UI.getCurrent().addWindow(dialog);
    }

    public static void showForException(Exception exception) {
        UnexcpectedErrorDialog dialog = new UnexcpectedErrorDialog(exception);
        dialog.center();
        UI.getCurrent().addWindow(dialog);
    }

    public UnexcpectedErrorDialog(Exception exception) {
        this(exception.getMessage());
    }

    public UnexcpectedErrorDialog(String message) {
        super("Unexcpected Error");
        buidUI(message);
    }

    private void buidUI(String message) {
        setWidth(600, Unit.PIXELS);
        setHeight(250, Unit.PIXELS);
        setResizable(false);
        setModal(true);
        addStyleName("error");

        Label messageLabel = new Label(message);
        messageLabel.setSizeFull();
        setDialogContent(messageLabel);

        setCloseButton();
    }

}
