/*
 * Created by Martin Dünkelmann on 26.05.18 20:55
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 19:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.MenuBar.MenuItem;

import java.io.File;

@Title("Grundgerüst - Profil - Unternehmen")
public class ProfilUnternehmen extends Abstract {
    public ProfilUnternehmen() {
        HorizontalLayout content = setSiteLayout();

        content.setSizeFull();

        setContent(content);
    }

    public static String getName() {
        return "ProfilUnternehmen";
    }

    private HorizontalLayout setSiteLayout() {
        VerticalLayout layoutLeft = setLayoutLeft();
        VerticalLayout layoutCentre = setLayoutCentre();
        VerticalLayout layoutRight = setLayoutRight();
        HorizontalLayout layout = new HorizontalLayout(
                layoutLeft,
                layoutCentre,
                layoutRight
        );

        layoutLeft.setSizeUndefined();
        layoutCentre.setSizeFull();
        layoutRight.setSizeUndefined();

        layout.setComponentAlignment(layoutLeft, Alignment.TOP_LEFT);
        layout.setComponentAlignment(layoutCentre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(layoutRight, Alignment.TOP_RIGHT);

        return layout;
    }

    private VerticalLayout setLayoutCentre() {
        
        VerticalLayout centre3 = setLayoutCentreVertical3();
        VerticalLayout centre1 = setLayoutCentreVertical1();
        VerticalLayout centre2 = setLayoutCentreVertical2();
        
        HorizontalLayout foot = setCentreFoot();
        VerticalLayout layout = new VerticalLayout(
                centre3,centre1,centre2,
                foot
        );
        layout.setComponentAlignment(centre3, Alignment.TOP_CENTER);
        layout.setComponentAlignment(centre1, Alignment.TOP_CENTER);
        layout.setComponentAlignment(centre1, Alignment.MIDDLE_CENTER);
      
        layout.setComponentAlignment(foot, Alignment.BOTTOM_CENTER);

        return layout;
    }

    private HorizontalLayout setCentreFoot() {
        
        Label data = new Label("<a href='http://google.de'> Datenschutz</a>" , ContentMode.HTML);
       Label data2 = new Label("<a href='http://google.de'> Nutzungsbedingungen</a>", ContentMode.HTML);
        return new HorizontalLayout(data,data2);
    }

    private VerticalLayout setLayoutCentreVertical1() {
        return new VerticalLayout(
                
                setProfilePictureLayout()
        );
    }
private VerticalLayout setLayoutCentreVertical2() {
        return new VerticalLayout(
                
                setLayoutCentreForm()
        );
    }
    private FormLayout setLayoutCentreForm() {
        FormLayout form = new FormLayout();
        TextArea area1 = new TextArea("Neuigkeiten");
      area1.setWordWrap(true);
      area1.setHeight(500, Unit.PIXELS);
      area1.setWidth(500, Unit.PIXELS);
      form.addComponent(area1);
      Button submitbutton = new Button("submit", event -> area1.setReadOnly(true));
                            
      form.addComponent(submitbutton);
     
     
        return new FormLayout(form);
    }

    private VerticalLayout setLayoutLeft() {
     FormLayout form = new FormLayout();
     
       Image logo = new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png"))
        );

        logo.setHeight("100");
        logo.setWidth("100");
        form.addComponent(logo);
        
       
         Button button1 =new Button("Profil");
               button1.setWidth(180, Unit.PIXELS);
               form.addComponent(button1);
        Button button2 =new Button("Persönliche Daten");
               button2.setWidth(180, Unit.PIXELS);
               form.addComponent(button2);
        Button button3 =new Button("Profileinstellungen");
               button3.setWidth(180, Unit.PIXELS);
               form.addComponent(button3);
        Button button4 =new Button("Stellen");
               button4.setWidth(180, Unit.PIXELS);
               form.addComponent(button4);
               
              
        
               
              

        return new VerticalLayout(form);
    }

    private VerticalLayout setLayoutRight() {
        
      Label label = new Label("<a href='http://google.de'> Logout</a>" , ContentMode.HTML);
        return new VerticalLayout(label);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private VerticalLayout setProfilePictureLayout() {
       //Hier später Profilbild hinzufügen
      
        Upload upload = new Upload("Upload it here", null);
                upload.setImmediateMode(false);
                
                return new VerticalLayout(upload);
}

    private VerticalLayout setLayoutCentreVertical3() {
        MenuBar menu = new MenuBar();
      MenuItem start = menu.addItem("Startseite", null,null);
      MenuItem nachrichten = menu.addItem("Nachrichten", null,null);
      MenuItem kontakt = menu.addItem("Kontakte", null,null);
      
      return new VerticalLayout(menu);
    }

    
}
