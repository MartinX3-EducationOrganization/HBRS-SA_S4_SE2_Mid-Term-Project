/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.ss18.view;
import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

import static com.vaadin.ui.themes.ValoTheme.OPTIONGROUP_HORIZONTAL;


import com.vaadin.ui.*;


/**
 *
 * @author Mohamad
 * 
 */
@Title("SubView")
public class SubView extends Abstract {
    
    public SubView(){
        
       setContent(
                new VerticalLayout(
                        new VerticalLayout(
                                setProfilePictureLayout()
                        ),
                        new VerticalLayout(
                                setLayoutCentreForm()
                        )
                )
        );
    }

    public static String getName() {
        return "SubView";
    }

    private FormLayout setLayoutCentreForm() {
        FormLayout form = 
                new FormLayout(
                        
                        
                       
                        setItemGroup(new RadioButtonGroup<>("Anrede"), OPTIONGROUP_HORIZONTAL, "Herr", "Frau"),
                        setFormularItem(new TextField(), "Name, Vorname", VaadinIcons.USER),
                        setFormularItem(new DateField(), "Geburtstag", VaadinIcons.CALENDAR_USER),
                        setFormularItem(new TextField(), "Starsse/Hausnummer", VaadinIcons.HOME),
                        setFormularItem(new TextField(), "Ort/PLZ", VaadinIcons.OFFICE),
                        setFormularItem(new TextField(), "Email", VaadinIcons.MAILBOX),
                        setFormularItem(new TextField(), "Telefon(optional)", VaadinIcons.PHONE)
                        
                );
         
        
        
        Button button = new Button("Speichern");
        form.addComponent(button);
        form.setComponentAlignment(button, Alignment.BOTTOM_CENTER);
        return form;

    }

    private VerticalLayout setProfilePictureLayout() {
//TODO: Hier später Profilbild hinzufügen
        Upload upload = new Upload(
                "Upload it here",
                null
        );
        upload.setImmediateMode(false);

        return new VerticalLayout(upload);
    }
    
    
    private <T extends AbstractField> T setFormularItem(T item, String title, VaadinIcons icon) {
        item.setCaption(title);
        item.setIcon(icon);
        item.setRequiredIndicatorVisible(true);

        return item;
    }

   

    private <T extends AbstractListing> T setItemGroup(T group, String style, String... items) {
        group.setItems(items);
        group.addStyleName(style);

        return group;
    }

   

}
    

