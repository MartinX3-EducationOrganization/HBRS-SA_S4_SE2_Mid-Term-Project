package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.AnzeigeDAO;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.entity.Anzeige;
import org.bonn.se.ss18.service.Roles;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;
import java.util.Locale;

/**
 * @author rjourd2s
 */
@Title("Grundgerüst - Unternehmen - Stellenausschreibung")
public class StellenausschreibungUnternehmen extends Abstract {
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
                StellenaussgabeDataView window = new StellenaussgabeDataView(documentData);
                getUI().addWindow(window);
                //add a listener, which will be executed when the window will be closed
                window.addCloseListener(closeEvent -> {
                    updateGrid(); //refresh grid to show any changes
                });
            }
        });

        Panel footer = new Panel();
        footer.setWidth("100%");
        //content.addComponent(footer);

    }

    public static String getName() {
        return "Stellenausschreibung";
    }

    private void updateGrid() {
        try {
            ConnectionFactory dao = ConnectionFactory.getInstance();
            AnzeigeDAO aDAO = (AnzeigeDAO) dao.getDAO(Tables.table_anzeige);
            UnternehmerDTO unternehmerDTO = (UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
            grid.setItems(aDAO.getAllByID(unternehmerDTO.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}