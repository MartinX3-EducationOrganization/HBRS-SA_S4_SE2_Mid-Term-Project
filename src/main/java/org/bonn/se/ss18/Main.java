/*
 * Created by Martin Dünkelmann on 22.05.18 19:51
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 22.05.18 19:44
 */

package org.bonn.se.ss18;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;
import org.bonn.se.ss18.view.Login;

import javax.servlet.annotation.WebServlet;

/**
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
public class Main extends UI {
    private Navigator navigator;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        getPage().setTitle("Grundgerüst Slave Inc.");

        // Create a navigator to control the views
        UI.getCurrent().setNavigator(new Navigator(this, this));

        // Create and register the views
        UI.getCurrent().getNavigator().addView(Login.NAME, Login.class);

        UI.getCurrent().getNavigator().navigateTo(Login.NAME);
    }

    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = Main.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
    }
}
