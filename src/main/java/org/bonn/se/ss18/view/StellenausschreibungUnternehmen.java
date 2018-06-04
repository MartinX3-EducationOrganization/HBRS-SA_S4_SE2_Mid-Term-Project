package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.HtmlRenderer;
import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.StellenausschreibungDAO;
import org.bonn.se.ss18.entity.Stellenausschreibung;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;
import java.util.Locale;

/**
 * @author rjourd2s
 */
@Title("Grundgerüst - Unternehmen - Stellenausschreibung")
public class StellenausschreibungUnternehmen extends Abstract {
    private final Grid<Stellenausschreibung> grid = new Grid<>(Stellenausschreibung.class);

    public StellenausschreibungUnternehmen() {

        VerticalLayout content = new VerticalLayout();
        setContent(content);

        HorizontalLayout titleBar = new HorizontalLayout();
        titleBar.setWidth("100%");
        content.addComponent(titleBar);

        Label title = new Label("Eine gute Stelle!");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f);

        Label titleComment = new Label("Schlechte Bezahlung!");
        titleBar.addComponent(titleComment);


        HorizontalLayout center = new HorizontalLayout();
        content.addComponent(center);

        Panel menue = new Panel();
        center.addComponent(menue);
        menue.setContent(new VerticalLayout(

        ));

        Panel matter = new Panel();
        center.addComponent(matter);
        matter.setContent(grid);


        grid.setColumns("title");
        grid.addColumn("text", new

                HtmlRenderer());

        updateGrid();
        grid.setLocale(Locale.GERMAN);
        grid.setHeightMode(HeightMode.UNDEFINED);


        grid.addItemClickListener(event ->

        {
            //Check, if it is a double-click event
            if (event.getMouseEventDetails().isDoubleClick()) {
                //get the item which has been clicked
                Stellenausschreibung documentData = event.getItem();
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
            StellenausschreibungDAO sDAO = (StellenausschreibungDAO) dao.getDAO(Tables.table_stellenunternehmen);
            grid.setItems(sDAO.getAllbyId(UI.getCurrent().getUIId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}