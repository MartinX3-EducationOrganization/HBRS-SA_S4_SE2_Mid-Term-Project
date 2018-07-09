package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.UnternehmenController;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Anzeige;
import org.bonn.se.ss18.service.Roles;

import java.util.Locale;

/**
 * @author rjourd2s
 */
@Title("Grundgerüst - Unternehmen - Stellenausschreibung")
class StellenausschreibungUnternehmen extends Abstract {
    private final UnternehmenController unternehmenController = new UnternehmenController();
    private final Grid<Anzeige> grid = new Grid<>(Anzeige.class);

    public StellenausschreibungUnternehmen() {

        VerticalLayout content = new VerticalLayout();
        setContent(content);

        HorizontalLayout titleBar = new HorizontalLayout();
        content.addComponent(titleBar);

        Label title = new Label("Eine Liste ihrer Anzeigen");
        titleBar.addComponent(title);

        HorizontalLayout center = new HorizontalLayout();
        content.addComponent(center);


        Panel matter = new Panel();
        center.addComponent(matter);
        matter.setSizeFull();
        matter.setContent(grid);


        updateGrid();
        grid.setLocale(Locale.GERMAN);
        grid.setHeightMode(HeightMode.UNDEFINED);

        center.setSizeUndefined();

        grid.setSizeFull();

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

    private void updateGrid() {
        grid.setItems(unternehmenController.getAllAnzeigenByID(((UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER)).getId()));
    }
}