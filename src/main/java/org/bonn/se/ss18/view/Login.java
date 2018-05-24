/*
 * Created by Martin Dünkelmann on 23.05.18 12:28
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.05.18 12:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;

@Theme("logintheme")
public class Login extends Panel implements View {
    public static final String NAME = "";

    public Login() {
        VerticalLayout layout = new VerticalLayout();

        TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me.");
        button.addClickListener(e -> {

            layout.addComponent(new Label("Thanks " + name.getValue()
                    + ", it works!"));
        });

        layout.addComponents(name, button);
        layout.setComponentAlignment(name, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(button, Alignment.MIDDLE_CENTER);

        setContent(layout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome Message Example");
    }
}
