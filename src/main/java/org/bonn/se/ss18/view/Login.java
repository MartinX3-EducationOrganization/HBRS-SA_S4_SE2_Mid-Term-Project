/*
 * Created by Martin Dünkelmann on 23.05.18 12:28
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.05.18 12:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.server.Page;
import com.vaadin.ui.*;

public class Login extends Panel implements View {
    public static final String NAME = "";

    public Login() {
        setContent(new HorizontalLayout(setLayoutLeft(), setLayoutCentre(), setlayoutRight()));
    }

    private VerticalLayout setLayoutCentre() {
        return new VerticalLayout();
    }

    private VerticalLayout setLayoutLeft() {
        return new VerticalLayout(new Image("Logo", new ClassResource("../resources/logo.png")));
    }

    private VerticalLayout setlayoutRight() {
        return new VerticalLayout(
                new Label("Noch nicht registriert?"),
                new Button("Jetzt registrieren!", //TODO: Login -> Registration
                        event -> UI.getCurrent().getNavigator().navigateTo("Registration")));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Page.getCurrent().setTitle("Grundgerüst - Login");

        Notification.show("Welcome Message Example");
    }
}
