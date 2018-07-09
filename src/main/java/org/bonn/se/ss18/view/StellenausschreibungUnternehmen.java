package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.controller.UnternehmenController;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Anzeige;
import org.bonn.se.ss18.service.Roles;

import java.util.Iterator;
import java.util.Locale;
import java.util.Set;

/**
 * @author rjourd2s
 */
@Title("Grundgerüst - Unternehmen - Stellenausschreibung")
public class StellenausschreibungUnternehmen extends Abstract {
    private final UnternehmenController unternehmenController = new UnternehmenController();
    private final Grid<Anzeige> grid = new Grid<>(Anzeige.class);

    public StellenausschreibungUnternehmen() {

        VerticalLayout content = new VerticalLayout();
        setContent(content);
        content.setWidthUndefined();

        HorizontalLayout titleBar = new HorizontalLayout();
        content.addComponent(titleBar);

        Label title = new Label("Eine Liste ihrer Anzeigen");
        titleBar.addComponent(title);

        HorizontalLayout center = new HorizontalLayout();
        content.addComponent(center);


        Panel matter = new Panel();
        matter.setSizeFull();
        center.addComponent(matter);
        matter.setSizeFull();
        matter.setContent(grid);

        Button addButton = new Button(
                "Hinzufügen",
                (Button.ClickListener) event -> addRow()
        );
        content.addComponent(addButton);

        // Löschbutton
        Button deleteButton = new Button(
                "Löschen",
                (Button.ClickListener) event -> {
                    Set<Anzeige> selected = grid.getSelectedItems();
                    Iterator i = selected.iterator();
                    while (i.hasNext()) {
                        unternehmenController.deleteAnzeige((Anzeige) i.next());
                    }
                    updateGrid();
                }
        );
        deleteButton.addStyleName(ValoTheme.BUTTON_DANGER);
        content.addComponent(deleteButton);


        updateGrid();
        grid.setLocale(Locale.GERMAN);
        grid.setHeightMode(HeightMode.UNDEFINED);
        grid.setWidth("1000px");
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.addItemClickListener(event ->

        {
            //Check, if it is a double-click event
            if (event.getMouseEventDetails().isDoubleClick()) {
                //get the item which has been clicked
                Anzeige documentData = event.getItem();
                //open the item in a window
                StellenausgabeDataView window = new StellenausgabeDataView(documentData);
                getUI().addWindow(window);
                //add a listener, which will be executed when the window will be closed
                window.addCloseListener(closeEvent -> {
                    updateGrid(); //refresh grid to show any changes
                });
            }
        });
    }

    private void addRow() {
        Anzeige neue = new Anzeige();
        StellenausgabeDataView window = new StellenausgabeDataView(neue);
        getUI().addWindow(window);
        window.addCloseListener(closeEvent -> {
            updateGrid();
        });
    }


    private void updateGrid() {
        grid.setItems(unternehmenController.getAllAnzeigenByID(((UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER)).getId()));
        grid.setColumnOrder("datum", "titel", "typ", "anstellungsart", "ort", "arbeitszeit");
        grid.getColumn("aktiv").setHidden(true);
        grid.getColumn("id").setHidden(true);
        grid.getColumn("userid").setHidden(true);
        grid.getColumn("text").setHidden(true);
        grid.getColumn("brancheid").setHidden(true);
    }
}