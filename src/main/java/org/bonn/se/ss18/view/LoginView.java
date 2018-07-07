/*
 * Created by Martin Dünkelmann on 23.05.18 12:28
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.05.18 12:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.LoginController;
import org.bonn.se.ss18.exception.NoSuchUserOrPasswort;

import java.io.File;

@Title("Grundgerüst - Login")
public class LoginView extends Abstract {
    // Link zum Backend
    private final LoginController loginController = new LoginController();

    public LoginView() {
        VerticalLayout layoutCentre = getLayoutCentre();

        Button register = new Button("Jetzt registrieren!",
                event -> {
            UI.getCurrent().getNavigator().navigateTo(RegistrationUnternehmen.getName());
            //UI.getCurrent().getNavigator().navigateTo(RegistrationUnternehmen.getName());
                }
        );
        register.setId("registrieren_in");
        VerticalLayout layoutRight = new VerticalLayout(
                new Label("Noch nicht registriert?"),
                register

        );

        HorizontalLayout layout = new HorizontalLayout(
                layoutCentre,
                layoutRight
        );
        layout.setComponentAlignment(layoutCentre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(layoutRight, Alignment.TOP_RIGHT);
        setContent(layout);
    }

    public static String getName() {
        return "";
    }

    private VerticalLayout getLayoutCentre() {
        TextField user = new TextField("Linux-Kennung / Benutzername");
        user.setId("user");
        PasswordField pass = new PasswordField("Passwort");
        pass.setId("passwort");
        Button loginButton = new Button("Anmelden", (Button.ClickListener) event -> {
            try {
                loginController.login(user.getValue(), pass.getValue());
            } catch (NoSuchUserOrPasswort ex) {
                user.setValue("");
                pass.setValue("");
                Notification.show("Logindaten wurden nicht richtig eingeben.", Notification.Type.ERROR_MESSAGE);
            }
        });
        //loginButton.setId("anmenlden");

        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        loginButton.setId("log-in");
        VerticalLayout centre = new VerticalLayout(
                new Label("Anmeldung"),
                new Label("Bitte geben Sie ihren Benutzernamen und ihr Passwort ein"),
                new FormLayout(user, pass, loginButton)
        );

        Image logo = new Image(
                null,
                new FileResource(
                        new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png")
                )
        );
        logo.setHeight(100, Unit.PIXELS);

        Label head = new Label("Herzlich Willkommen auf Coll@HBRS");
        VerticalLayout layout = new VerticalLayout(
                logo,
                head,
                centre
        );
        layout.setComponentAlignment(head, Alignment.TOP_CENTER);
        layout.setComponentAlignment(centre, Alignment.MIDDLE_CENTER);
        return layout;
    }
}
