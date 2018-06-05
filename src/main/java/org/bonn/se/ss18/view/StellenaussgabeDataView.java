package org.bonn.se.ss18.view;

import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.ConnectionFactory;
import org.bonn.se.ss18.dao.AnzeigeDAO;
import org.bonn.se.ss18.entity.Anzeige;
import org.bonn.se.ss18.service.Tables;

import java.sql.SQLException;

/**
 * @author rjourd2s
 */
public class StellenaussgabeDataView extends Window {
    private final Anzeige documentData;

    public StellenaussgabeDataView(Anzeige documentData) {
        this.documentData = documentData;

        //Create a form layout which holds all components
        FormLayout layout = new FormLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        //Topic TextField
        TextField title = new TextField();
        title.setCaption("Titel");
        if (documentData.getTitle() != null) {
            title.setValue(documentData.getTitle());
        }
        layout.addComponent(title);


        DateField datum = new DateField();
        datum.setCaption("Datum");
        if (documentData.getDatum() != null) {
            datum.setValue(documentData.getDatum());
        }
        layout.addComponent(datum);

        TextField ort = new TextField();
        ort.setCaption("Ort");
        if (documentData.getOrt() != null) {
            ort.setValue(documentData.getOrt());
        }
        layout.addComponent(ort);

        TextField topic = new TextField();
        topic.setCaption("Titel");
        if (documentData.getTitle() != null) {
            topic.setValue(documentData.getTitle());
        }
        layout.addComponent(topic);


        //Description TextArea
        RichTextArea description = new RichTextArea();
        description.setCaption("Text");
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
                AnzeigeDAO aDAO = null;
                try {
                    aDAO = (AnzeigeDAO) dao.getDAO(Tables.table_anzeige);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                aDAO.update(documentData);
                //Mark the object as changed
                //documentData.setChanged(true);

                //Close window
                close();
            }
        });

        //Set the layout as content of the hole window
        setContent(layout);

        //Configure position and caption of the window
        setCaption("Stellenausschreibung");
        center();

    }
}

