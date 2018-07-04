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
import java.io.File;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Roles;

public class ProfilUnternehmen extends Abstract {
    
    private final Unternehmer user = (Unternehmer) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
    boolean formReadOnly = true; // toggle read only or read/write mode
    FormLayout form = new FormLayout();
                
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
        
        TextField firmenname = new TextField("Firmenname");
        firmenname.setId("firmenname");
        firmenname.setValue(user.getFirmenname());
        firmenname.setWidth("50%");
        form.addComponent(firmenname);
        
        TextField branche = new TextField("Branche");
        branche.setId("branche");
        // branche.setValue(user.getBranche(); // TODO: Branche anstatt BranchenID holen
        branche.setWidth("50%");
        form.addComponent(branche);
        
        // Section 2
        section = new Label("Kontakt");
        section.addStyleName(ValoTheme.LABEL_H3);
        section.addStyleName(ValoTheme.LABEL_COLORED);
        form.addComponent(section);
         
        TextField strasse = new TextField("Straße");
        strasse.setId("street");
        if(user.getStrasse() != null) strasse.setValue(user.getStrasse());
        strasse.setWidth("50%");
        form.addComponent(strasse);

        TextField hnr = new TextField("Hausnummer");
        hnr.setId("hs");
        if(user.getHausnr() != null) hnr.setValue(user.getHausnr());
        hnr.setWidth("10%");
        form.addComponent(hnr);

        TextField plz = new TextField("PLZ");
        plz.setId("plz");
        plz.setValue(user.getPlz());
        plz.setWidth("10%");
        form.addComponent(plz);

        TextField ort = new TextField("Ort");
        ort.setId("ort");
        ort.setValue(user.getOrt());
        ort.setWidth("50%");
        form.addComponent(ort);
        
        TextField website = new TextField("Website");
        website.setId("website");
        if(user.getWebsite() != null) website.setValue(user.getWebsite());
        website.setWidth("50%");
        form.addComponent(website);
        
        TextField email = new TextField("Email");
        email.setId("email");
        email.setValue(user.getEmail());
        email.setWidth("50%");
        form.addComponent(email);

        TextField phone = new TextField("Telefonnummer");
        phone.setId("tel");
        if(user.getTelNr() != null) phone.setValue(user.getTelNr());
        phone.setWidth("50%");
        form.addComponent(phone);

        TextField fax = new TextField("Faxnummer");
        fax.setId("fax");
        if(user.getFaxNr() != null) fax.setValue(user.getFaxNr());
        fax.setWidth("50%");
        form.addComponent(fax);
    
        TextField ansprechpartner = new TextField("Ansprechpartner");
        ansprechpartner.setId("ansprechpartner");
        if(user.getAnsprechpartner() != null) ansprechpartner.setValue(user.getAnsprechpartner());
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
        if(user.getKurzVorstellung() != null) bio.setValue(user.getKurzVorstellung());
        form.addComponent(bio);
        
        // Footer
        HorizontalLayout footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(true, false, true, false));
        footer.setSpacing(true);
        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        form.addComponent(footer);
                
        Button editButton = new Button("Bearbeiten", (Button.ClickListener) event -> {
            if(formReadOnly){
                event.getButton().setCaption("Speichern");
                event.getButton().addStyleName(ValoTheme.BUTTON_PRIMARY);
            }
            else{
                event.getButton().setCaption("Bearbeiten");
                event.getButton().removeStyleName(ValoTheme.BUTTON_PRIMARY);
            }
            bio.setReadOnly(!formReadOnly);
            setFormReadOnly(!formReadOnly);
        });
        editButton.setId("editButton");
        footer.addComponent(editButton);
        
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
        if(bool == formReadOnly) return; // if aspired mode is already set, return
        
        for(Component c : form) {
                if(c instanceof AbstractField) {
                    AbstractField field = (AbstractField) c;
                    field.setReadOnly(bool);
                }
        }
        formReadOnly = bool;
    }

}
