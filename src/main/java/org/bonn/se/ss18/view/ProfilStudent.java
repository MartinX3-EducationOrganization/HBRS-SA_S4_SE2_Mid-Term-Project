package org.bonn.se.ss18.view;


import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
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

        StudentDTO studentDTO = (StudentDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Binder<StudentDTO> binder = new Binder<>();

        TextField vorname = new TextField("Vorname");
        binder.bind(vorname, StudentDTO::getVorname, StudentDTO::setVorname);
        vorname.setId("firstname");
        vorname.setWidth("50%");
        form.addComponent(vorname);


        TextField nachname = new TextField("Nachname");
        binder.bind(nachname, StudentDTO::getNachname, StudentDTO::setNachname);
        nachname.setId("lastname");
        nachname.setWidth("50%");
        form.addComponent(nachname);

        DateField birthday = new DateField("Geburtstag");
        binder.bind(birthday, StudentDTO::getGebDatum, StudentDTO::setGebDatum);
        birthday.setId("birthday");
        form.addComponent(birthday);

        section = new Label("Kontakt");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField strasse = new TextField("Straße");
        binder.bind(strasse, StudentDTO::getStrasse, StudentDTO::setStrasse);
        strasse.setId("street");
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        binder.bind(hnr, StudentDTO::getHausnr, StudentDTO::setHausnr);
        hnr.setId("hs");
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("PLZ");
        binder.bind(plz, StudentDTO::getPlz, StudentDTO::setPlz);
        plz.setId("plz");
        plz.setWidth("10%");
        form.addComponent(plz);

        TextField ort = new TextField("Ort");
        binder.bind(ort, StudentDTO::getOrt, StudentDTO::setOrt);
        ort.setId("ort");
        ort.setWidth("50%");
        form.addComponent(ort);

        TextField email = new TextField("Email");
        binder.bind(email, StudentDTO::getEmail, StudentDTO::setEmail);
        email.setId("email");
        email.setWidth("50%");
        form.addComponent(email);

        TextField phone = new TextField("Telefonnummer");
        binder.bind(phone, StudentDTO::getTelNr, StudentDTO::setTelNr);
        phone.setId("tel");
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        binder.bind(fax, StudentDTO::getFaxNr, StudentDTO::setFaxNr);
        fax.setId("fax");
        fax.setWidth("50%");
        form.addComponent(fax);

        section = new Label("Zusätzliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField social_media_link = new TextField("Social Media Link");
        binder.bind(social_media_link, StudentDTO::getSocialMediaLink, StudentDTO::setSocialMediaLink);
        social_media_link.setId("socialmedialink");
        social_media_link.setWidth("50%");
        social_media_link.setMaxLength(30);
        form.addComponent(social_media_link);

        RichTextArea bio = new RichTextArea("Kurzvorstellung");
        binder.bind(bio, StudentDTO::getKurzVorstellung, StudentDTO::setKurzVorstellung);
        bio.setId("bio");
        bio.setWidth("100%");
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

        binder.readBean(studentDTO);

        Button editButton = new Button("Bearbeiten", (Button.ClickListener) event -> {
            if (event.getButton().getCaption().equals("Bearbeiten")) {
                setFormReadOnly(false);
                form.removeStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                event.getButton().setCaption("Speichern");
                event.getButton().addStyleName(ValoTheme.BUTTON_PRIMARY);
            } else {
                setFormReadOnly(true);
                form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);
                try {
                    binder.writeBean(studentDTO);
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
                studentController.updateProfil(studentDTO);
                event.getButton().setCaption("Bearbeiten");
                event.getButton().removeStyleName(ValoTheme.BUTTON_PRIMARY);
            }
        });
        editButton.setId("editButton");
        footer.addComponent(editButton);

        Button deleteButton = new Button(
                "Profil Löschen",
                (Button.ClickListener) event -> {
                    deletProfil();
                }
        );
        deleteButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        footer.addComponent(deleteButton);

        // Ausgangszustand: read-only
        setFormReadOnly(true);
    }

    private void deletProfil() {
        Window deletWarning = new Window("Warnung löschen des eigenen Profils");
        VerticalLayout deletWarningContent = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();
        deletWarning.setContent(deletWarningContent);

        Label warning = new Label("Wollen Sie wirklich ihr Profil löschen?");
        Button yes = new Button("Ja");
        Button cancel = new Button("Abbrechen");
        yes.addStyleName(ValoTheme.BUTTON_DANGER);
        deletWarningContent.addComponent(warning);
        buttons.addComponent(yes);
        buttons.addComponent(cancel);
        deletWarningContent.addComponent(buttons);
        deletWarningContent.addComponent(buttons);
        yes.addClickListener(clickEvent -> {
            studentController.removeProfil((new UserDTO((StudentDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER))).getId());
            loginController.logout();
        });
        cancel.addClickListener(clickEvent -> {
            deletWarning.close();
        });
        deletWarning.center();
        getUI().addWindow(deletWarning);
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
