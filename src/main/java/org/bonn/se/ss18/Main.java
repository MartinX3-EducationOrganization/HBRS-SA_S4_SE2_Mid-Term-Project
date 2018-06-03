/*
 * Created by Martin Dünkelmann on 22.05.18 19:51
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 22.05.18 19:44
 */

package org.bonn.se.ss18;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;
import org.bonn.se.ss18.view.MainUI;

import javax.servlet.annotation.WebServlet;

public class Main {
    @WebServlet(urlPatterns = "/*", name = "MainServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MainUI.class, productionMode = false)
    public static class MainServlet extends VaadinServlet {
        @Override
        protected void servletInitialized() {
        }
    }
}
