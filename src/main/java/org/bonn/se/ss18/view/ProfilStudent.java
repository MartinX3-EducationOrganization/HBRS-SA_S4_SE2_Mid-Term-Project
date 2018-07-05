package org.bonn.se.ss18.view;


import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.controller.LoginController;
import org.bonn.se.ss18.controller.StudentController;
import org.bonn.se.ss18.dto.StudentDTO;
import org.bonn.se.ss18.dto.UserDTO;
import org.bonn.se.ss18.service.Roles;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * @author rjourd2s
 */

public class ProfilStudent extends Abstract {
    private final FormLayout form = new FormLayout();
    private final StudentController studentController = new StudentController();
    private final LoginController loginController = new LoginController();

    public ProfilStudent() {
        Label title = new Label("Profil (Student)");
        title.addStyleName(ValoTheme.LABEL_H1);

        setContent(new VerticalLayout(title, form));

        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        Label section = new Label("Persönliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        form.addComponent(
                new Image(
                        null,
                        new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/profile_default.jpg"))
                )
        );

        Upload upload = new Upload("Upload Profilbild", (Upload.Receiver) (filename, mimeType) -> new ByteArrayOutputStream());
        upload.setImmediateMode(false);
        upload.addSucceededListener((Upload.SucceededListener) event -> ((StudentDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER)).setFoto(((ByteArrayOutputStream) upload.getReceiver().receiveUpload(event.getFilename(), event.getMIMEType())).toByteArray()));
        form.addComponent(upload);

        TextField vorname = new TextField("Vorname");
        vorname.setId("firstname");
        StudentDTO studentDTO = (StudentDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        vorname.setValue(studentDTO.getVorname());
        vorname.setWidth("50%");
        form.addComponent(vorname);

        TextField nachname = new TextField("Nachname");
        nachname.setId("lastname");
        nachname.setValue(studentDTO.getNachname());
        nachname.setWidth("50%");
        form.addComponent(nachname);

        DateField birthday = new DateField("Geburtstag");
        birthday.setId("birthday");
        birthday.setValue(studentDTO.getGebDatum());
        birthday.setReadOnly(true);
        form.addComponent(birthday);

        section = new Label("Kontakt");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField strasse = new TextField("Straße");
        strasse.setId("street");
        if (studentDTO.getStrasse() != null) {
            strasse.setValue(studentDTO.getStrasse());
        }
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        hnr.setId("hs");
        if (studentDTO.getHausnr() != null) {
            hnr.setValue(studentDTO.getHausnr());
        }
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("PLZ");
        plz.setId("plz");
        plz.setValue(studentDTO.getPlz());
        plz.setWidth("10%");
        form.addComponent(plz);

        TextField ort = new TextField("Ort");
        ort.setId("ort");
        ort.setValue(studentDTO.getOrt());
        ort.setWidth("50%");
        form.addComponent(ort);

        TextField email = new TextField("Email");
        email.setId("email");
        email.setValue(studentDTO.getEmail());
        email.setWidth("50%");
        form.addComponent(email);

        TextField phone = new TextField("Telefonnummer");
        phone.setId("tel");
        if (studentDTO.getTelNr() != null) {
            phone.setValue(studentDTO.getTelNr());
        }
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        fax.setId("fax");
        if (studentDTO.getFaxNr() != null) {
            fax.setValue(studentDTO.getFaxNr());
        }
        fax.setWidth("50%");
        form.addComponent(fax);

        section = new Label("Zusätzliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField social_media_link = new TextField("Social Media Link");
        social_media_link.setId("socialmedialink");
        social_media_link.setValue(studentDTO.getSocialMediaLink());
        social_media_link.setWidth("50%");
        social_media_link.setMaxLength(30);
        form.addComponent(social_media_link);

        RichTextArea bio = new RichTextArea("Kurzvorstellung");
        bio.setId("bio");
        bio.setWidth("100%");
        if (studentDTO.getKurzVorstellung() != null) {
            bio.setValue(studentDTO.getKurzVorstellung());
        }
        form.addComponent(bio);

        section = new Label("Fähigkeiten und Skills");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        Button skillButton = new Button("Hinzufügen", new Button.ClickListener() {
            int i = 1;

            @Override
            public void buttonClick(Button.ClickEvent event) {
                form.addComponent(new TextField("" + i++));
            }
        });
        form.addComponent(skillButton);

        // Footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        footer.setId("footer");
        form.addComponent(footer);

        Button editButton = new Button("Bearbeiten", (Button.ClickListener) event -> {
            if (event.getButton().getCaption().equals("Bearbeiten")) {
                setFormReadOnly(false);
                form.removeStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                event.getButton().setCaption("Speichern");
                event.getButton().addStyleName(ValoTheme.BUTTON_PRIMARY);
            } else {
                setFormReadOnly(true);
                form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                event.getButton().setCaption("Bearbeiten");
                event.getButton().removeStyleName(ValoTheme.BUTTON_PRIMARY);
            }
        });
        editButton.setId("editButton");
        footer.addComponent(editButton);

        Button deleteButton = new Button(
                "Löschen",
                (Button.ClickListener) event -> {
                    studentController.removeProfil((new UserDTO((StudentDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER))).getId());
                    loginController.logout();
                }
        );
        deleteButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        footer.addComponent(deleteButton);

        // Ausgangszustand: read-only
        setFormReadOnly(true);
    }

    public static String getName() {
        return "ProfilStudent";
    }

    private void setFormReadOnly(boolean bool) {
        for (Component c : form) {
            if (c instanceof AbstractComponent && !"footer".equals(c.getId())) {
                c.setEnabled(!bool);
            }
        }
    }
}
