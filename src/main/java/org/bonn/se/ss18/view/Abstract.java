/*
 * Created by Martin Dünkelmann on 26.05.18 20:57
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 20:57
 */

package org.bonn.se.ss18.view;

import com.vaadin.navigator.View;
import com.vaadin.ui.Panel;

/**
 * @author martin on 26.05.18
 * @project wi-inf_se2_2018_grundgeruest
 */
public abstract class Abstract extends Panel implements View {
    public static String getName() {
        throw new IllegalArgumentException("Child class did not override getName() method.  This must be done in order for the class to be used!");
    }
}
