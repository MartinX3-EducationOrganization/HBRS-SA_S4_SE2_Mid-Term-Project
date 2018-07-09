/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.ui.*;


/**
 *
 * @author Mohamad
 */
@Title("Stellensuche")
class StellensucheFilter extends Abstract {

    public StellensucheFilter() {
        setContent(new VerticalLayout(
                new VerticalLayout(setLayoutCenterForm()))
        );
    }

    private FormLayout setLayoutCenterForm() {
        FormLayout form = new FormLayout();

        form.setWidth("40%");

        ComboBox<String> Berufsfeld = new ComboBox<>("Berufsfeld");
        Berufsfeld.setItems("Programmierung", "Datenabanken", "Webdesign",
                "Marketing", "Vertrieb", "Buchhaltung");

        Berufsfeld.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()) {
                Notification.show("Bitte wählen sie ein Berufsfeld aus");
            }
        });

        TextField Suchbegriff = new TextField("Suchbegriff");
        TextField ort = new TextField("Ort");
        ComboBox<String> radius = new ComboBox<>("Umkreis");
        radius.setItems("5 km", "15 km", "25 km", "50 km");
        radius.addValueChangeListener(event -> {
            if (event.getSource().isEmpty()) {
                Notification.show("No browser selected");
            }
        });

        Button button = new Button("Suchen");

        form.addComponent(Berufsfeld);
        form.addComponent(Suchbegriff);
        form.addComponents(ort, radius);
        form.addComponent(button);

        return form;
    }
}
