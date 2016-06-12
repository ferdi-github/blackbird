package net.studio24.blackbird;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class MainScreen extends VerticalLayout {

	public MainScreen() {
		buildUI();
	}

	private void buildUI() {
		setStyleName("main-screen");

		setMargin(true);
		setSizeFull();

		Label label = new Label("Blackbird");
		label.addStyleName(ValoTheme.LABEL_H1);
		label.setSizeUndefined();
		addComponent(label);
		setComponentAlignment(label, Alignment.MIDDLE_CENTER);
	}

}
