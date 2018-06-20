package org.bonn.se.ss18.view;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.service.Roles;

import java.io.File;

/**
 * @author rjourd2s
 */

public class ProfilStudent extends Abstract {
    private final Student user = (Student) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
    boolean read;
    FormLayout form = new FormLayout();

    public ProfilStudent() {
        Label title = new Label("ProfilStudent");
        title.addStyleName(ValoTheme.LABEL_H1);

        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        Label section = new Label("Persönliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H2);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        form.addComponent(
                new Image(
                        null,
                        new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/profile_default.jpg"))
                )
        );

        TextField vorname = new TextField("Vorame");
        vorname.setId("firstname");
        vorname.setValue(user.getVorname());
        vorname.setWidth("50%");
        form.addComponent(vorname);


        TextField nachname = new TextField("Nachame");
        nachname.setId("lastname");
        nachname.setValue(user.getNachname());
        nachname.setWidth("50%");
        form.addComponent(nachname);


        DateField birthday = new DateField("Birthday");
        birthday.setId("birthday");
        birthday.setValue(user.getGebDatum());
        birthday.setReadOnly(true);
        form.addComponent(birthday);


        section = new Label("Kontakt Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField strasse = new TextField("Straße");
        strasse.setId("street");
        strasse.setValue(user.getStrasse());
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        hnr.setId("hs");
        hnr.setValue(user.getHausnr());
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("Plz");
        plz.setId("plz");
        plz.setValue(user.getPlz());
        plz.setWidth("10%");
        form.addComponent(plz);


        TextField ort = new TextField("Ort");
        ort.setId("ort");
        ort.setValue(user.getOrt());
        ort.setWidth("50%");
        form.addComponent(ort);


        TextField email = new TextField("Email");
        email.setId("email");
        email.setValue(user.getEmail());
        email.setWidth("50%");
        form.addComponent(email);


        TextField phone = new TextField("Telefonnummer");
        phone.setId("tel");
        phone.setValue(user.getTelNr());
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        fax.setId("fax");
        fax.setValue(user.getFaxNr());
        fax.setWidth("50%");
        form.addComponent(fax);


        section = new Label("Zusätzliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H4);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);


        RichTextArea bio = new RichTextArea("Kurzvorstellung");
        bio.setId("bio");
        bio.setWidth("100%");
        bio.setValue(user.getKurzVorstellung());
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
        edit.setId("edit");


        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
        footer.addComponent(edit);

        setContent(
                new VerticalLayout(
                        title,
                        form
                )
        );

    }

    public static String getName() {
        return "ProfilStudent";
    }

    private void readOnly(boolean isReadonly) {
        if (isReadonly) {
            for (Component c : form) {
                if (c instanceof AbstractField) {
                    AbstractField field = (AbstractField) c;
                    field.setReadOnly(true);

                }
            }
            read = true;
        } else {
            for (Component c : form) {
                if (c instanceof AbstractField) {
                    AbstractField field = (AbstractField) c;
                    field.setReadOnly(false);
                }

            }
            read = false;
        }
    }
}
