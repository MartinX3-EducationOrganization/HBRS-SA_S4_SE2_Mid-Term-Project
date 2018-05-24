/*
 * Created by Martin Dünkelmann on 23.05.18 12:28
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.05.18 12:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.*;

public class Login extends Panel implements View {
    public static final String NAME = "";

    public Login() {
        VerticalLayout layoutLeft = new VerticalLayout();
        layoutLeft.addComponent(new Image("Logo", new ThemeResource("../resources/logo.png")));

        VerticalLayout layoutCentre = new VerticalLayout();

        VerticalLayout layoutRight = new VerticalLayout();
        layoutRight.addComponents(
                new Label("Noch nicht registriert?"),
                new Button("Jetzt registrieren!", //TODO: Login -> Registration
                        event -> UI.getCurrent().getNavigator().navigateTo("Registration")));

        HorizontalLayout mainLayout = new HorizontalLayout();
        mainLayout.addComponents(layoutLeft, layoutCentre, layoutRight);

        setContent(mainLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Page.getCurrent().setTitle("Ficken");
        Notification.show("Welcome Message Example");
    }
}
