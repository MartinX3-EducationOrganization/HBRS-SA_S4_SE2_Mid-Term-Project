/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject3;

/**
 *
 * @author Abdullah.Mohamad
 */
import static com.sun.corba.se.impl.oa.poa.AOMEntry.VALID;
import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Binder;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.ClassResource;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.data.Binder.Binding;
import com.vaadin.data.BindingValidationStatus;
import com.vaadin.data.BindingValidationStatus.Status;
import com.vaadin.data.HasValue;
import com.vaadin.data.Validator;
import com.vaadin.data.converter.StringToIntegerConverter;

import com.vaadin.shared.ui.ContentMode;
import static com.vaadin.shared.ui.ContentMode.HTML;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import static com.vaadin.ui.themes.ValoTheme.OPTIONGROUP_HORIZONTAL;
import java.io.File;
import java.time.LocalDate;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Title("Registrierung")
public class UnternehmenRegistrationUI extends UI {
    

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
       
        
        
        
        //HauptLayout
        final VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        
        
        
        //Überschrift
        Label label1 = new Label(String.format("<font size = \"6\" color=\"black\"> Registrierung" ), ContentMode.HTML);
        
        
        
        //Logo
        String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
      FileResource resource1 = new FileResource(new File(basepath + "/../resources/logo.png"));
      Image logo = new Image("", resource1);
      logo.setWidth(200, Unit.PIXELS);
      logo.setHeight(200, Unit.PIXELS);
      layout.addComponent(logo);
      
      
        layout.addComponent(label1);
        layout.setComponentAlignment(logo, Alignment.TOP_LEFT);        
        layout.setComponentAlignment(label1, Alignment.TOP_CENTER);        
        Label label2 = new Label(String.format("<font size = \"4\" color=\"black\"> Jetzt als Unternehmen registrieren und potenzielle "
                 + "Bewerber finden" ), ContentMode.HTML);
        layout.addComponent(label2);
        layout.setComponentAlignment(label2, Alignment.TOP_CENTER);
        
        Label label3 = new Label(String.format("<font size = \"5\" color=\"black\"> Persöhnliche Daten" ), ContentMode.HTML);
        layout.addComponent(label3);
        layout.setComponentAlignment(label3, Alignment.TOP_CENTER);
        
        
        //Formular
        
        FormLayout form = new FormLayout();
        form.setStyleName("formpos");
        
        form.setMargin(true);
        form.setWidth(null);
                
        //form.setMargin(true);
        //form.addStyleName("outlined");
        
       
        
        //Anrede
        
        RadioButtonGroup<String> anrede =new RadioButtonGroup<>("Anrede");
        anrede.setItems("Herr", "Frau");
        form.addComponent(anrede);
        anrede.addStyleName(OPTIONGROUP_HORIZONTAL);
         
         


        //Name
        TextField name = new TextField("Name,Vorname");
        name.setIcon(VaadinIcons.USER);
        name.setRequiredIndicatorVisible(true);
        form.addComponent(name);
        
       
        
        
        //Unternehmen
       TextField unternehmen = new TextField("Unternehmen");
       unternehmen.setIcon(VaadinIcons.OFFICE);
       unternehmen.setRequiredIndicatorVisible(true);
       form.addComponent(unternehmen);
       
       
       
       //Geburtstag
       DateField bday = new DateField("Geburtstag");
       bday.setRequiredIndicatorVisible(true);
       bday.setIcon(VaadinIcons.CALENDAR_USER);
       form.addComponent(bday);
       
       
       
       //Email
       TextField email = new TextField("Email");
       email.setIcon(VaadinIcons.MAILBOX);
       email.setRequiredIndicatorVisible(true);
       form.addComponent(email);
       
       
       
       //Telefon
       TextField telefon = new TextField("Telefon");
       telefon.setIcon(VaadinIcons.PHONE);
       telefon.setRequiredIndicatorVisible(true);
       form.addComponent(telefon);
       
       
       
       //PLZ
        TextField plz = new TextField("Ort, PLZ");
       plz.setIcon(VaadinIcons.HOME);
       plz.setRequiredIndicatorVisible(true);
       form.addComponent(plz);
       form.setComponentAlignment(plz, Alignment.MIDDLE_CENTER);
       layout.addComponent(form);
       layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);
       
       //Nutzungsbedingungen
      HorizontalLayout fittingLayout = new HorizontalLayout();
       Label n = new Label("Ich habe die " +"<a href='http://google.de'> Nutzungsbedingungen</a>" + " gelesen und akzeptiere sie", ContentMode.HTML);
        CheckBoxGroup<String> agb = new CheckBoxGroup<>();
        agb.setItems("");
        agb.setRequiredIndicatorVisible(true);
        fittingLayout.addComponents(agb,n);
       
        
        //Button Registrieren
       HorizontalLayout buttonlayout = new HorizontalLayout();
       Button button = new Button("Registrieren", event -> UI.getCurrent().getNavigator().navigateTo(""));
       buttonlayout.addComponent(button);
       button.addClickListener(clickEvent ->
       Notification.show("Vielen Dank"));
      
       
       //Datenschutz/Nutzungsbedingungen
       HorizontalLayout datalayout = new HorizontalLayout();
       Label data = new Label("<a href='http://google.de'> Datenschutz</a>" , ContentMode.HTML);
       Label data2 = new Label("<a href='http://google.de'> Nutzungsbedingungen</a>", ContentMode.HTML);
       datalayout.addComponents(data,data2);
       datalayout.setMargin(true);
        datalayout.setWidth(null);
       
       
      
       
       
       //Formular
       layout.addComponent(fittingLayout);
       
       layout.setComponentAlignment(fittingLayout, Alignment.BOTTOM_CENTER);
       layout.addComponent(buttonlayout);
       layout.setComponentAlignment(buttonlayout, Alignment.BOTTOM_CENTER);
       layout.addComponent(datalayout);
       layout.setComponentAlignment(datalayout, Alignment.BOTTOM_CENTER);
       
       
       
       
      
       
       
      
        // layout.setComponentAlignment(form, Alignment.BOTTOM_CENTER);
     // layout.setSizeFull();
     setContent(layout);
       
        
       
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
    
    
    
}
