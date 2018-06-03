package org.bonn.se.ss18.view;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.util.Iterator;

/**
 * @author rjourd2s
 */

public class Profil extends VerticalLayout implements View {
    boolean read;
    FormLayout form = new FormLayout();

    public Profil() {
        setSpacing(true);
        setMargin(true);

        Label title = new Label("Profil");
        title.addStyleName(ValoTheme.LABEL_H1);
        addComponent(title);


        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
        addComponent(form);

        Label section = new Label("Persönliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H2);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);


        TextField vorname = new TextField("Vorame");
        //TODO get Name from Tables ...  name.setValue(TODO get Name from Tables);
        vorname.setValue("Vorname");
        vorname.setWidth("50%");
        form.addComponent(vorname);

        TextField nachname = new TextField("Nachame");
        //TODO get Name from Tables ...  name.setValue(TODO get Name from Tables);
        nachname.setValue("Nachname");
        nachname.setWidth("50%");
        form.addComponent(nachname);

        DateField birthday = new DateField("Birthday");
        //TODO get Name from Tables birthday.getValue();
        birthday.setReadOnly(true);
        form.addComponent(birthday);


        section = new Label("Kontakt Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField strasse = new TextField("Straße");
        strasse.setValue("Straße");
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        hnr.setValue("16");
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("Plz");
        plz.setValue("53757");
        plz.setWidth("10%");
        form.addComponent(plz);


        TextField ort = new TextField("Ort");
        ort.setValue("Sankt Augustin");
        ort.setWidth("50%");
        form.addComponent(ort);


        TextField email = new TextField("Email");
        //TODO get email from Tables email.getValue();
        email.setWidth("50%");
        form.addComponent(email);


        TextField phone = new TextField("Telefonnummer");
        //TODO get Phone
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        //TODO get Fax
        fax.setWidth("50%");
        form.addComponent(fax);


        section = new Label("Zusätzliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H4);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField website = new TextField("Website");
        //TODO website.getValue();
        website.setWidth("100%");
        form.addComponent(website);

        RichTextArea bio = new RichTextArea("Kurzvorstellung");
        bio.setWidth("100%");
        bio.setValue("<p>blalblabla</p>");
        form.addComponent(bio);
        readOnly(true);
        read = true;

        Button edit = new Button("Edit", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if (read) {
                    bio.setReadOnly(false);
                    readOnly(false);
                    form.removeStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                    event.getButton().setCaption("Save");
                    event.getButton().addStyleName(ValoTheme.BUTTON_PRIMARY);
                } else {
                    bio.setReadOnly(true);
                    readOnly(true);
                    form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                    event.getButton().setCaption("Edit");
                    event.getButton().removeStyleName(ValoTheme.BUTTON_PRIMARY);
                }
            }
        });


        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
        footer.addComponent(edit);

    }

    private void readOnly(boolean b) {
        if (b) {
            Iterator<Component> i = form.getComponentIterator();
            while (i.hasNext()) {
                Component c = i.next();
                if (c instanceof com.vaadin.ui.AbstractField) {
                    AbstractField field = (AbstractField) c;
                    field.setReadOnly(true);

                }
            }
            read = true;
        } else {
            Iterator<Component> i = form.getComponentIterator();
            while (i.hasNext()) {
                Component c = i.next();
                if (c instanceof com.vaadin.ui.AbstractField) {
                    AbstractField field = (AbstractField) c;
                    field.setReadOnly(false);
                }

            }
            read = false;
        }
    }

    public static String getName() {
        return "Profil";
    }

    @Override
    public void enter(ViewChangeEvent event) {
        // TODO Auto-generated method stub

    }
}
