/*
 * Created by Martin Dünkelmann on 23.05.18 12:28
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.05.18 12:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.LoggingControl;

import java.io.File;

@Title("Grundgerüst - Login")
public class Login extends Abstract {
    // Link zum Backend
    LoggingControl main = new LoggingControl();

    public Login() {
        HorizontalLayout content = setSiteLayout();

        content.setSizeFull();

        setContent(content);
    }

    public static String getName() {
        return "";
    }

    private HorizontalLayout setSiteLayout() {
        VerticalLayout layoutLeft = setLayoutLeft();
        VerticalLayout layoutCentre = setLayoutCentre();
        VerticalLayout layoutRight = new VerticalLayout(
                new Label("Noch nicht registriert?"),
                new Button("Jetzt registrieren!",
                        event -> UI.getCurrent().getNavigator().navigateTo(RegistrationUnternehmen.getName())
                )
        );
        layoutLeft.setSizeUndefined();
        layoutCentre.setSizeFull();
        layoutRight.setSizeUndefined();

        HorizontalLayout layout = new HorizontalLayout(
                layoutLeft,
                layoutCentre,
                layoutRight
        );
        layout.setComponentAlignment(layoutLeft, Alignment.TOP_LEFT);
        layout.setComponentAlignment(layoutCentre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(layoutRight, Alignment.TOP_RIGHT);

        return layout;
    }

    private VerticalLayout setLayoutCentre() {
        TextField user = new TextField("Linux-Kennung / Benutzername");
        PasswordField pass = new PasswordField("Passwort");
        Button loginButton = new Button("Anmelden", (Button.ClickListener) event -> {
            try {
                main.login(user.getValue(), pass.getValue());
            }
            // Wenn Loging erfolgreich
            catch (Exception ex) {
                // Falls incorrect
                user.setValue("");
                pass.setValue("");
                Notification.show("Logindaten wurden nicht richtig eingeben.", Notification.Type.ERROR_MESSAGE);
            }
        });
        loginButton.addStyleName("friendly");
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        HorizontalLayout foot = new HorizontalLayout(
                new Link(
                        "Datenschutz",
                        new ExternalResource("http://vaadin.com/")
                ),
                new Link(
                        "Nutzungsbedingungen",
                        new ExternalResource("http://vaadin.com/")
                )
        );

        Label head = new Label("Herzlich Willkommen auf Coll@HBRS");
        VerticalLayout centre = new VerticalLayout(
                new Label("Anmeldung"),
                new Label("Bitte geben Sie ihren Benutzernamen und ihr Passwort ein"),
                new FormLayout(user, pass, loginButton),
                head
        );

        VerticalLayout layout = new VerticalLayout(
                head,
                centre,
                foot
        );

        layout.setComponentAlignment(head, Alignment.TOP_CENTER);
        layout.setComponentAlignment(centre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(foot, Alignment.BOTTOM_CENTER);

        return layout;
    }

    private VerticalLayout setLayoutLeft() {
        Image logo = new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png"))
        );

        logo.setHeight("100");
        logo.setWidth("100");

        return new VerticalLayout(logo);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome to the Grundgerüst");
    }
}
