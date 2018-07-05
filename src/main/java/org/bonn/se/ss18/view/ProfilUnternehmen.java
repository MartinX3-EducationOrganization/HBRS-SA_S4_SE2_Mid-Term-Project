/*
 * Created by Martin Dünkelmann on 26.05.18 20:55
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 19:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.controller.LoginController;
import org.bonn.se.ss18.controller.UnternehmenController;
import org.bonn.se.ss18.dto.UnternehmerDTO;
import org.bonn.se.ss18.dto.UserDTO;
import org.bonn.se.ss18.service.Roles;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ProfilUnternehmen extends Abstract {
    private final FormLayout form = new FormLayout();
    private final UnternehmenController unternehmenController = new UnternehmenController();
    private final LoginController loginController = new LoginController();
                
    public ProfilUnternehmen() {
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

        form.addComponent(
            new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/profile_default.jpg"))
            )
        );

        Upload upload = new Upload("Upload Profilbild", (Upload.Receiver) (filename, mimeType) -> new ByteArrayOutputStream());
        upload.setImmediateMode(false);
        upload.addSucceededListener((Upload.SucceededListener) event -> ((UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER)).setFoto(((ByteArrayOutputStream) upload.getReceiver().receiveUpload(event.getFilename(), event.getMIMEType())).toByteArray()));
        form.addComponent(upload);
        
        TextField firmenname = new TextField("Firmenname");
        firmenname.setId("firmenname");
        UnternehmerDTO unternehmerDTO = (UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        firmenname.setValue(unternehmerDTO.getFirmenname());
        firmenname.setWidth("50%");
        form.addComponent(firmenname);
        
        TextField branche = new TextField("Branche");
        branche.setId("branche");
        // branche.setValue(unternehmerDTO.getBranche(); // TODO: Branche anstatt BranchenID holen
        branche.setWidth("50%");
        form.addComponent(branche);
        
        // Section 2
        section = new Label("Kontakt");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);
         
        TextField strasse = new TextField("Straße");
        strasse.setId("street");
        if (unternehmerDTO.getStrasse() != null) {
            strasse.setValue(unternehmerDTO.getStrasse());
        }
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        hnr.setId("hs");
        if (unternehmerDTO.getHausnr() != null) {
            hnr.setValue(unternehmerDTO.getHausnr());
        }
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("PLZ");
        plz.setId("plz");
        plz.setValue(unternehmerDTO.getPlz());
        plz.setWidth("10%");
        form.addComponent(plz);

        TextField ort = new TextField("Ort");
        ort.setId("ort");
        ort.setValue(unternehmerDTO.getOrt());
        ort.setWidth("50%");
        form.addComponent(ort);
        
        TextField website = new TextField("Website");
        website.setId("website");
        if (unternehmerDTO.getWebsite() != null) {
            website.setValue(unternehmerDTO.getWebsite());
        }
        website.setWidth("50%");
        form.addComponent(website);
        
        TextField email = new TextField("Email");
        email.setId("email");
        email.setValue(unternehmerDTO.getEmail());
        email.setWidth("50%");
        form.addComponent(email);

        TextField phone = new TextField("Telefonnummer");
        phone.setId("tel");
        if (unternehmerDTO.getTelNr() != null) {
            phone.setValue(unternehmerDTO.getTelNr());
        }
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        fax.setId("fax");
        if (unternehmerDTO.getFaxNr() != null) {
            fax.setValue(unternehmerDTO.getFaxNr());
        }
        fax.setWidth("50%");
        form.addComponent(fax);
    
        TextField ansprechpartner = new TextField("Ansprechpartner");
        ansprechpartner.setId("ansprechpartner");
        if (unternehmerDTO.getAnsprechpartner() != null) {
            ansprechpartner.setValue(unternehmerDTO.getAnsprechpartner());
        }
        ansprechpartner.setWidth("50%");
        form.addComponent(ansprechpartner);

        // Section 3
        section = new Label("Zusätzliche Informationen");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);

        RichTextArea bio = new RichTextArea("Kurzvorstellung");
        bio.setId("bio");
        bio.setWidth("100%");
        if (unternehmerDTO.getKurzVorstellung() != null) {
            bio.setValue(unternehmerDTO.getKurzVorstellung());
        }
        form.addComponent(bio);
        
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
                    unternehmenController.removeProfil((new UserDTO((UnternehmerDTO) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER))).getId());
                    loginController.logout();
                }
        );
        deleteButton.addStyleName(ValoTheme.BUTTON_PRIMARY);
        footer.addComponent(deleteButton);

        // Ausgangszustand: read-only
        setFormReadOnly(true);
    }

    public static String getName() {
        return "ProfilUnternehmen";
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
