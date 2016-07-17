package net.studio24.blackbird.ui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import net.studio24.blackbird.i18n.I18n;

/**
 * View that is shown when trying to navigate to a view that does not exist
 * using {@link com.vaadin.navigator.Navigator}.
 * 
 * @author ferdi-github
 * @since 1.0
 */
public class ErrorView extends VerticalLayout implements View {

    private Label explanation;

    public ErrorView() {
        setMargin(true);
        setSpacing(true);

        Label header = new Label(ErrorViewMessages.get().cannotFindView());
        header.addStyleName(ValoTheme.LABEL_H1);
        addComponent(header);
        addComponent(explanation = new Label());
        explanation.addStyleName(ValoTheme.LABEL_FAILURE);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        explanation.setValue(ErrorViewMessages.get().navigationTargetDoesNotExist(event.getViewName()));
    }

    public interface ErrorViewMessages {

        public static ErrorViewMessages get() {
            return I18n.from(ErrorViewMessages.class);
        }

        String cannotFindView();

        String navigationTargetDoesNotExist(String viewId);
    }
}
