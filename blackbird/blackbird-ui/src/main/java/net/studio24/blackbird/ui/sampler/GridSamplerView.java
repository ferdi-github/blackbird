package net.studio24.blackbird.ui.sampler;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * A sample view.
 */
public class GridSamplerView extends HorizontalLayout implements View {

    public static final String VIEW_ID = "gridSampler";
    public static final String VIEW_NAME = "Grid Sampler";

    private Grid demoGrid;

    public GridSamplerView() {
        buildUI();
    }

    private void buildUI() {
        setSizeFull();

        VerticalLayout filterLayout = new VerticalLayout();
        filterLayout.setWidth(260, Unit.PIXELS);
        filterLayout.setMargin(true);
        filterLayout.setSpacing(true);
        addComponent(filterLayout);

        Label filerLabel = new Label("Filter items");
        filerLabel.addStyleName(ValoTheme.LABEL_SMALL);
        filerLabel.addStyleName(ValoTheme.LABEL_LIGHT);
        filerLabel.setWidthUndefined();
        filterLayout.addComponent(filerLabel);

        TextField nameFilterTextField = new TextField("Name");
        nameFilterTextField.setWidth(100, Unit.PERCENTAGE);
        filterLayout.addComponent(nameFilterTextField);

        Button applyFilterButon = new Button("Filter", FontAwesome.SEARCH);
        applyFilterButon.setWidth(100, Unit.PERCENTAGE);
        filterLayout.addComponent(applyFilterButon);

        demoGrid = new Grid();
        demoGrid.setSizeFull();
        // demoGrid.addStyleName(ValoTheme.TABLE_BORDERLESS);
        addComponent(demoGrid);
        setExpandRatio(demoGrid, 1f);

        VerticalLayout detailsLayout = new VerticalLayout();
        detailsLayout.setWidth(260, Unit.PIXELS);
        detailsLayout.setMargin(true);
        detailsLayout.setSpacing(true);
        addComponent(detailsLayout);

        Label detailsLabel = new Label("Item details & actions");
        detailsLabel.addStyleName(ValoTheme.LABEL_SMALL);
        detailsLabel.addStyleName(ValoTheme.LABEL_LIGHT);
        detailsLabel.setWidthUndefined();
        detailsLayout.addComponent(detailsLabel);

        Button editItemButon = new Button("Edit Person", FontAwesome.EDIT);
        editItemButon.setWidth(100, Unit.PERCENTAGE);
        detailsLayout.addComponent(editItemButon);

        Button deleteItemButon = new Button("Delete Person", FontAwesome.TRASH);
        deleteItemButon.setWidth(100, Unit.PERCENTAGE);
        deleteItemButon.addStyleName(ValoTheme.BUTTON_DANGER);
        // deleteItemButon.addStyleName(ValoTheme.BUTTON_ICON_ONLY);
        detailsLayout.addComponent(deleteItemButon);

        // CssLayout actionsContainer = new CssLayout(editItemButon,
        // deleteItemButon);
        // actionsContainer.setWidth(100, Unit.PERCENTAGE);
        // actionsContainer.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        // detailsLayout.addComponent(actionsContainer);
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // Have some data
        List<Person> people = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            people.add(new Person("Person " + i, 1000 + i));
        }

        // Have a container of some type to contain the data
        BeanItemContainer<Person> container = new BeanItemContainer<Person>(Person.class, people);
        demoGrid.setContainerDataSource(container);
        demoGrid.setColumnOrder("name", "born");
    }

    public static class Person {
        private String name;
        private int born;

        public Person(String name, int born) {
            super();
            this.name = name;
            this.born = born;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBorn() {
            return born;
        }

        public void setBorn(int born) {
            this.born = born;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Person other = (Person) obj;
            if (name == null) {
                if (other.name != null)
                    return false;
            } else if (!name.equals(other.name))
                return false;
            return true;
        }

    }

}
