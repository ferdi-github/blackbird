package net.studio24.blackbird.ui.sampler;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;

import net.studio24.blackbird.ui.views.ApplicationView;

public class GridSamplerViewProvider implements ApplicationView {

    @Override
    public String getId() {
        return GridSamplerView.VIEW_ID;
    }

    @Override
    public String getTitle() {
        return GridSamplerView.VIEW_NAME;
    }

    @Override
    public FontIcon getIcon() {
        return FontAwesome.EDIT;
    }

    @Override
    public Class<? extends View> getViewClass() {
        return GridSamplerView.class;
    }

}
