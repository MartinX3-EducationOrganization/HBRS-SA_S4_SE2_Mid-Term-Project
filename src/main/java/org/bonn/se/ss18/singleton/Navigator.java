/*
 * Created by Martin Dünkelmann on 23.05.18 12:51
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 23.05.18 12:51
 */

package org.bonn.se.ss18.singleton;

import org.bonn.se.ss18.Main;
import org.bonn.se.ss18.view.Login;

/**
 * @author martin on 23.05.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public class Navigator {
    private static com.vaadin.navigator.Navigator navigator;

    public static void init(Main ui, Main container) {
        Navigator.navigator = new com.vaadin.navigator.Navigator(ui, container);
    }

    public static void addView(String viewName, Login component) {
        Navigator.navigator.addView(viewName, component);
    }

    public static void navigateTo(String viewName) {
        Navigator.navigator.navigateTo(viewName);
    }
}
