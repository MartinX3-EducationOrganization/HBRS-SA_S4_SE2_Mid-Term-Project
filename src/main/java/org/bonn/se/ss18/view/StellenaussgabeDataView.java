package org.bonn.se.ss18.view;

import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.UnternehmenController;
import org.bonn.se.ss18.entity.Anzeige;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * @author rjourd2s
 */
public class StellenaussgabeDataView extends Window {
    private final UnternehmenController unternehmenController = new UnternehmenController();

    public StellenaussgabeDataView(Anzeige documentData) {
        List<String> anstellungslist = new ArrayList<>();
        anstellungslist.add("Ausbildung");
        anstellungslist.add("befristete Antellung");
        anstellungslist.add("Festanstellung");
        anstellungslist.add("Praktikum");
        anstellungslist.add("Werkstudent/-in");

        List<String> arbeitszeiten = new ArrayList<>();
        arbeitszeiten.add("Minijob");
        arbeitszeiten.add("Teilzeit");
        arbeitszeiten.add("Vollzeit");

        List<String> typlist = new ArrayList<>();
        typlist.add("Angebot");
        typlist.add("Gesuch");

        //Create a form layout which holds all components
        FormLayout layout = new FormLayout();
        layout.setMargin(true);
        layout.setSpacing(true);

        //Topic TextField
        TextField title = new TextField();
        title.setCaption("Titel");
        if (documentData.getTitel() != null) {
            title.setValue(documentData.getTitel());
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

        ComboBox<String> selectType = new ComboBox<>("Wählen Sie aus");
        selectType.setValue(documentData.getTyp());
        selectType.setEmptySelectionAllowed(false);
        selectType.setTextInputAllowed(false);
        selectType.setItems(typlist);
        layout.addComponent(selectType);


        ComboBox<String> selectAnstellung = new ComboBox<>("Wählen Sie aus");
        selectAnstellung.setItems(anstellungslist);
        selectAnstellung.setEmptySelectionAllowed(false);
        selectAnstellung.setTextInputAllowed(false);
        selectAnstellung.setValue(documentData.getAnstellungsart());
        layout.addComponent(selectAnstellung);


        ComboBox<String> selectArbeitszeit = new ComboBox<>("Wählen Sie aus");
        selectArbeitszeit.setItems(arbeitszeiten);
        selectArbeitszeit.setEmptySelectionAllowed(false);
        selectArbeitszeit.setTextInputAllowed(false);
        selectArbeitszeit.setValue(documentData.getArbeitszeit());
        layout.addComponent(selectArbeitszeit);

        DateField beginn = new DateField();
        beginn.setCaption("Beginn");
        if (documentData.getBeginn() != null) {
            beginn.setValue(documentData.getBeginn());
        }
        layout.addComponent(beginn);

        //Description TextArea
        RichTextArea description = new RichTextArea();
        description.setCaption("Text");
        if (documentData.getText() != null) {
            description.setValue(documentData.getText());
        }
        layout.addComponent(description);

        layout.setSizeUndefined();
        HorizontalLayout buttonLayout = new HorizontalLayout();
        Button save = new Button("save");
        save.setStyleName("friendly");
        buttonLayout.addComponent(save);
        Button cancel = new Button("cancel");
        cancel.addClickListener(event -> close());
        buttonLayout.addComponent(cancel);
        layout.addComponent(buttonLayout);

        save.addClickListener(event -> {
            if (title.getValue().equals("") || description.getValue().equals("")) {
                Notification.show("Sie können nichts leeres speichern!");
            } else {
                documentData.setTitel(title.getValue());
                documentData.setDatum(Date.valueOf(datum.getValue()));
                documentData.setOrt(ort.getValue());
                documentData.setTyp(selectType.getValue());
                documentData.setAnstellungsart(selectAnstellung.getValue());
                documentData.setArbeitszeit(selectArbeitszeit.getValue());
                documentData.setBeginn(Date.valueOf(beginn.getValue()));
                documentData.setText(description.getValue());

                unternehmenController.updateAnzeige(documentData);
                close();
            }
        });
        setContent(layout);
        setCaption("Stellenausschreibung");
        center();

    }
}

