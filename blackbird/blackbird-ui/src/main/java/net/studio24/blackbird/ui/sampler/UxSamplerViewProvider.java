package net.studio24.blackbird.ui.sampler;

import com.vaadin.navigator.View;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.FontIcon;

import net.studio24.blackbird.ui.views.ApplicationView;

public class UxSamplerViewProvider implements ApplicationView {

    @Override
    public String getId() {
        return UxSamplerView.VIEW_ID;
    }

    @Override
    public String getTitle() {
        return UxSamplerView.VIEW_NAME;
    }

    @Override
    public FontIcon getIcon() {
        return FontAwesome.INFO_CIRCLE;
    }

    @Override
    public Class<? extends View> getViewClass() {
        return UxSamplerView.class;
    }

}
