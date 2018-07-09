/*
 * Created by Martin Dünkelmann on 26.05.18 20:55
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 19:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.server.FileResource;
import com.vaadin.server.StreamResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.controller.LoginController;
import org.bonn.se.ss18.controller.TableController;
import org.bonn.se.ss18.controller.UnternehmenController;
import org.bonn.se.ss18.dto.BrancheDTO;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.dto.UserDTO;
import org.bonn.se.ss18.service.Roles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.Paths;
import java.util.List;

public class ProfilUnternehmen extends Abstract {
    private final FormLayout form = new FormLayout();
    private final UnternehmenController unternehmenController = new UnternehmenController();
    private final LoginController loginController = new LoginController();

    public ProfilUnternehmen() {
        UnternehmerDTO unternehmerDTO = (UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        Label title = new Label("Profil (Unternehmen)");
        title.addStyleName(ValoTheme.LABEL_H1);

        setContent(new VerticalLayout(title, form));

        form.setMargin(false);
        form.setWidth("800px");
        form.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

// Section 1
        Label section = new Label("Unternehmen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        Image profilImage = new Image(
                null,
                unternehmerDTO.getFoto() == null
                        || unternehmerDTO.getFoto().length == 0
                        ? new FileResource(Paths.get(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath(), "WEB-INF", "classes", "profile_default.jpg").toFile())
                        : new StreamResource((StreamResource.StreamSource) () -> new ByteArrayInputStream(unternehmerDTO.getFoto()), "")
        );
        profilImage.setHeight(150, Unit.PIXELS);
        profilImage.setWidth(150, Unit.PIXELS);
        form.addComponent(profilImage);

        Upload upload = new Upload("Upload Profilbild", (Upload.Receiver) (filename, mimeType) -> new ByteArrayOutputStream());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        upload.setReceiver((Upload.Receiver) (filename, mimeType) -> baos);
        upload.addSucceededListener((Upload.SucceededListener) succeededEvent -> {
            byte[] bytes = baos.toByteArray();
            unternehmerDTO.setFoto(bytes);
            profilImage.setSource(new StreamResource((StreamResource.StreamSource) () -> new ByteArrayInputStream(bytes), ""));

        });
        upload.setImmediateMode(false);
        form.addComponent(upload);

        TextField firmenname = new TextField("Firmenname");
        Binder<UnternehmerDTO> binder = new Binder<>();
        binder.bind(firmenname, UnternehmerDTO::getFirmenname, UnternehmerDTO::setFirmenname);
        firmenname.setId("firmenname");
        firmenname.setWidth("50%");
        form.addComponent(firmenname);

        TableController tablecontroller = new TableController();
        List<BrancheDTO> branches = tablecontroller.getBranches();
        ComboBox<BrancheDTO> branche = new ComboBox<>("Branchen", branches);
        branche.setEmptySelectionAllowed(false);
        branche.setSizeFull();
        branche.setSelectedItem(branches.get(unternehmerDTO.getBranchenid() - 1));
// Use the name property for item captions
        branche.setItemCaptionGenerator(BrancheDTO::getBezeichnung);
        form.addComponent(branche);
        branche.addValueChangeListener(x -> unternehmerDTO.setBranchenid(x.getValue().getId()));

// Section 2
        section = new Label("Kontakt");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        TextField strasse = new TextField("Straße");
        binder.bind(strasse, UnternehmerDTO::getStrasse, UnternehmerDTO::setStrasse);
        strasse.setId("street");
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        binder.bind(hnr, UnternehmerDTO::getHausnr, UnternehmerDTO::setHausnr);
        hnr.setId("hs");
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("PLZ");
        binder.bind(plz, UnternehmerDTO::getPlz, UnternehmerDTO::setPlz);
        plz.setId("plz");
        plz.setWidth("10%");
        form.addComponent(plz);

        TextField ort = new TextField("Ort");
        binder.bind(ort, UnternehmerDTO::getOrt, UnternehmerDTO::setOrt);
        ort.setId("ort");
        ort.setWidth("50%");
        form.addComponent(ort);

        TextField website = new TextField("Website");
        binder.bind(website, UnternehmerDTO::getWebsite, UnternehmerDTO::setWebsite);
        website.setId("website");
        website.setWidth("50%");
        form.addComponent(website);

        TextField email = new TextField("Email");
        binder.bind(email, UnternehmerDTO::getEmail, UnternehmerDTO::setEmail);
        email.setId("email");
        email.setWidth("50%");
        form.addComponent(email);

        TextField phone = new TextField("Telefonnummer");
        binder.bind(phone, UnternehmerDTO::getTelNr, UnternehmerDTO::setTelNr);
        phone.setId("tel");
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        binder.bind(fax, UnternehmerDTO::getFaxNr, UnternehmerDTO::setFaxNr);
        fax.setId("fax");
        fax.setWidth("50%");
        form.addComponent(fax);

        TextField ansprechpartner = new TextField("Ansprechpartner");
        binder.bind(ansprechpartner, UnternehmerDTO::getAnsprechpartner, UnternehmerDTO::setAnsprechpartner);
        ansprechpartner.setId("ansprechpartner");
        ansprechpartner.setWidth("50%");
        form.addComponent(ansprechpartner);

// Section 3
        section = new Label("Zusätzliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        RichTextArea bio = new RichTextArea("Kurzvorstellung");
        binder.bind(bio, UnternehmerDTO::getKurzVorstellung, UnternehmerDTO::setKurzVorstellung);
        bio.setId("bio");
        bio.setWidth("100%");
        form.addComponent(bio);

        binder.readBean(unternehmerDTO);
// Footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        footer.setId("footer");
        form.addComponent(footer);

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
                    binder.writeBean(unternehmerDTO);
                } catch (ValidationException e) {
                    Notification.show(e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
                unternehmenController.updateProfil(unternehmerDTO);
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
            unternehmenController.removeProfil((new UserDTO((UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER))).getId());
            loginController.logout();
        });
        cancel.addClickListener(clickEvent -> deletWarning.close());
        deletWarning.center();
        getUI().addWindow(deletWarning);
    }

//TODO: Hier später Profilbild hinzufügen
/*private VerticalLayout setProfilePictureLayout() {
Upload upload = new Upload(
"Upload it here",
null
);
upload.setImmediateMode(false);

return new VerticalLayout(upload);
}*/

    private void setFormReadOnly(boolean bool) {
        for (Component c : form) {
            if (c instanceof AbstractComponent && !"footer".equals(c.getId())) {
                c.setEnabled(!bool);
            }
        }
    }
}
