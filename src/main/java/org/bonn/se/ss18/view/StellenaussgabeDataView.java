package org.bonn.se.ss18.view;

import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.StellenausschreibungDAO;
import org.bonn.se.ss18.entity.Stellenausschreibung;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class StellenaussgabeDataView extends Window {
    private final Stellenausschreibung documentData;

    public StellenaussgabeDataView(Stellenausschreibung documentData) {
        this.documentData = documentData;

        //Create a form layout which holds all components
        FormLayout layout = new FormLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        //Topic TextField
        TextField topic = new TextField();
        topic.setCaption("Titel");
        if (documentData.getTitle() != null) {
            topic.setValue(documentData.getTitle());
        }
        layout.addComponent(topic);


        //Description TextArea
        RichTextArea description = new RichTextArea();
        description.setCaption("Text");
        description.setSizeUndefined();
        if (documentData.getText() != null) {
            description.setValue(documentData.getText());
        }
        layout.addComponent(description);


        //Add save and cancel buttons
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button save = new Button("save");
        save.setStyleName("friendly");
        buttonLayout.addComponent(save);
        Button cancel = new Button("cancel");
        cancel.addClickListener(event -> close());
        buttonLayout.addComponent(cancel);
        layout.addComponent(buttonLayout);


        save.addClickListener(event -> {
            if (topic.getValue().equals("") || description.getValue().equals("")) {
                Notification.show("Sie können nichts leeres speichern!");
            } else {
                documentData.setTitle(topic.getValue());
                documentData.setText(description.getValue());
                ConnectionFactory dao = null;
                try {
                    dao = ConnectionFactory.getInstance();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                StellenausschreibungDAO sDAO = null;
                try {
                    sDAO = (StellenausschreibungDAO) dao.getDAO(Tables.table_stellenunternehmen);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                sDAO.update(documentData);
                //Mark the object as changed
                //documentData.setChanged(true);

                //Close window
                close();
            }
        });

        //Set layout size to undefined to let it fit to its content
        layout.setSizeUndefined();

        //Set the layout as content of the hole window
        setContent(layout);

        //Configure position and caption of the window
        setCaption("Stellenausschreibung");
        center();

    }
}

