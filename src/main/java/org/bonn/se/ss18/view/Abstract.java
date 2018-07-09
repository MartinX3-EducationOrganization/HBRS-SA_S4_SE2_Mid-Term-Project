/*
 * Created by Martin Dünkelmann on 26.05.18 20:57
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 20:57
 */

package org.bonn.se.ss18.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Page;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import org.bonn.se.ss18.service.Roles;
import org.bonn.se.ss18.service.Views;

/**
 * @author martin on 26.05.18
 * @project wi-inf_se2_2018_grundgeruest
 */
abstract class Abstract extends Panel implements View {
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) != null
                || Views.LoginView.equals(UI.getCurrent().getNavigator().getState())
                || Views.RegistrationUnternehmen.equals(UI.getCurrent().getNavigator().getState())) {
            return;
        }
        UI.getCurrent().getNavigator().navigateTo(Views.LoginView);
        Page.getCurrent().reload();
    }
}
