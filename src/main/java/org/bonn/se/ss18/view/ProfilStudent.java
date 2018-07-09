package org.bonn.se.ss18.view;


import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.controller.LoginController;
import org.bonn.se.ss18.controller.StudentController;
import org.bonn.se.ss18.dto.StudentDTO;
import org.bonn.se.ss18.dto.UserDTO;
import org.bonn.se.ss18.service.Roles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;

/**
 * @author rjourd2s
 */

public class ProfilStudent extends Abstract {
    private final FormLayout form = new FormLayout();
    private final StudentController studentController = new StudentController();
    private final LoginController loginController = new LoginController();

    public ProfilStudent() {
        StudentDTO studentDTO = (StudentDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
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

        Image profilImage = new Image(
                null,
                studentDTO.getFoto() == null
                        || studentDTO.getFoto().length == 0
                        ? new FileResource(Paths.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath(), "WEB-INF", "classes", "profile_default.jpg").toFile())
                        : new StreamResource((StreamResource.StreamSource) () -> new ByteArrayInputStream(studentDTO.getFoto()), "")
        );
        profilImage.setHeight(150, Unit.PIXELS);
        profilImage.setWidth(150, Unit.PIXELS);
        form.addComponent(profilImage);

        Upload upload = new Upload("Upload Profilbild", (Upload.Receiver) (filename, mimeType) -> new ByteArrayOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        upload.setReceiver((Upload.Receiver) (filename, mimeType) -> baos);
        upload.addSucceededListener((Upload.SucceededListener) succeededEvent -> {
            byte[] bytes = baos.toByteArray();
            studentDTO.setFoto(bytes);
            profilImage.setSource(new StreamResource((StreamResource.StreamSource) () -> new ByteArrayInputStream(bytes), ""));

        });
        upload.setImmediateMode(false);
        form.addComponent(upload);
        Binder<StudentDTO> binder = new Binder<>();

        TextField vorname = new TextField("Vorname");
        binder.forField(vorname)
                .asRequired("Bitte geben sie ihren Vornamen an!")
                .bind(StudentDTO::getVorname, StudentDTO::setVorname);
        vorname.setId("firstname");
        vorname.setWidth("50%");
        form.addComponent(vorname);


        TextField nachname = new TextField("Nachname");
        binder.forField(nachname)
                .asRequired("Bitte geben sie ihren Nachnamen an!")
                .bind(StudentDTO::getNachname, StudentDTO::setNachname);
        nachname.setId("lastname");
        nachname.setWidth("50%");
        form.addComponent(nachname);

        DateField birthday = new DateField("Geburtstag");
        binder.forField(birthday)
                .asRequired("Bitte geben sie ihren Geburtstag an!")
                .bind(StudentDTO::getGebDatum, StudentDTO::setGebDatum);
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
        binder.forField(plz)
                .asRequired("Bitte geben sie eine Postleitzahl an!")
                .bind(StudentDTO::getPlz, StudentDTO::setPlz);
        plz.setMaxLength(5);
        plz.setId("plz");
        plz.setWidth("10%");
        form.addComponent(plz);

        TextField ort = new TextField("Ort");
        binder.forField(ort)
                .asRequired("Bitte geben sie einen Ort an!")
                .bind(StudentDTO::getOrt, StudentDTO::setOrt);
        ort.setId("ort");
        ort.setWidth("50%");
        form.addComponent(ort);

        TextField email = new TextField("Email");
        binder.forField(email)
                .withValidator(new EmailValidator("Das sieht nicht nach einer Email aus"))
                .asRequired()
                .bind(StudentDTO::getEmail, StudentDTO::setEmail);
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

        form.addComponent(new Button("Hinzufügen", (Button.ClickListener) event -> form.addComponent(new TextField("Skill"))));

        // Footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        footer.setId("footer");
        form.addComponent(footer);

        binder.readBean(studentDTO);

        Button editButton = new Button("Bearbeiten", (Button.ClickListener) event -> {
            if ("Bearbeiten".equals(event.getButton().getCaption())) {
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
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
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
                (Button.ClickListener) event -> deletProfil()
        );
        deleteButton.setId("delete");
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
        yes.setId("yes");
        Button cancel = new Button("Abbrechen");
        cancel.setId("cancel");
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
        cancel.addClickListener(clickEvent -> deletWarning.close());
        deletWarning.center();
        getUI().addWindow(deletWarning);
    }

    private void setFormReadOnly(boolean bool) {
        for (Component c : form) {
            if (c instanceof AbstractComponent && !"footer".equals(c.getId())) {
                c.setEnabled(!bool);
            }
        }
    }
}
