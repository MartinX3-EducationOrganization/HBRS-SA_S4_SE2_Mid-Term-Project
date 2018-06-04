/*
 * Created by Martin Dünkelmann on 29.05.18 21:49
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 28.05.18 22:04
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.ui.VerticalLayout;

@Title("Grundgerüst - Profil - Student - Bewerben")
public class BewerbenStudent extends Abstract {
    public BewerbenStudent() {
        setContent(setSiteLayout());
    }

    public static String getName() {
        return "BewerbenStudent";
    }

    private VerticalLayout setSiteLayout() {
        VerticalLayout layout = new VerticalLayout(
                
        );

        return layout;
    }
}
