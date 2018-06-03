/*
 * Created by Martin Dünkelmann on 03.06.18 17:19
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 03.06.18 17:19
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * @author martin on 03.06.18
 * @project wi-inf_se2_2018_grundgeruest
 * <p>
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("maintheme")
public class MainUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        // Create a navigator to control the views
        UI.getCurrent().setNavigator(new Navigator(this, this));

        // Create and register the views
        UI.getCurrent().getNavigator().addView(BewerbenStudent.getName(), BewerbenStudent.class);
        UI.getCurrent().getNavigator().addView(Login.getName(), Login.class);
        UI.getCurrent().getNavigator().addView(ProfilUnternehmen.getName(), ProfilUnternehmen.class);
        UI.getCurrent().getNavigator().addView(RegistrationUnternehmen.getName(), RegistrationUnternehmen.class);
        UI.getCurrent().getNavigator().addView(StellenausschreibungUnternehmen.getName(), StellenausschreibungUnternehmen.class);
        UI.getCurrent().getNavigator().addView(MenueView.getName(), MenueView.class);

        //Move to the Login Page
        UI.getCurrent().getNavigator().navigateTo(Login.getName());
    }

    //        UI.getCurrent().getNavigator().addViewChangeListener();
}
