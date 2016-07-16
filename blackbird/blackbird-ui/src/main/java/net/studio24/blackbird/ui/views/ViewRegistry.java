package net.studio24.blackbird.ui.views;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

public final class ViewRegistry {

    public static Collection<ApplicationView> getRegisteredViews() {
        ServiceLoader<ApplicationView> loader = ServiceLoader.load(ApplicationView.class);
        List<ApplicationView> views = new ArrayList<>();
        loader.forEach((view) -> views.add(view));
        return views;
    }

}
