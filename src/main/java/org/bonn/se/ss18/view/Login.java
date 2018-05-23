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
public class Login extends VerticalLayout implements View {
    public Login() {
        setSizeFull();

        TextField name = new TextField();
        name.setCaption("Type your name here:");

        Button button = new Button("Click Me.");
        button.addClickListener(e -> addComponent(new Label("Thanks " + name.getValue()
                + ", it works!")));

        addComponents(name, button);
        setComponentAlignment(name, Alignment.MIDDLE_CENTER);
        setComponentAlignment(button, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("Welcome Message Example");
    }
}
