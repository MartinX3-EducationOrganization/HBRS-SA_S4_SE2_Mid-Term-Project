/*
 * Created by Martin Dünkelmann on 29.05.18 21:49
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 28.05.18 22:04
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

@Title("Grundgerüst - Profil - Student - Bewerben")
public class BewerbenStudent extends Abstract {
    public BewerbenStudent() {
        setContent(setSiteLayout());
    }

    public static String getName() {
        return "BewerbenStudent";
    }

    private HorizontalLayout setSiteLayout() {
        VerticalLayout layoutCentre = setLayoutCentre();
        HorizontalLayout layout = new HorizontalLayout(
                layoutCentre
        );

        layout.setComponentAlignment(layoutCentre, Alignment.MIDDLE_CENTER);

        return layout;
    }

    private VerticalLayout setLayoutCentre() {
        VerticalLayout centre = setLayoutCentreVertical();
        HorizontalLayout foot = setCentreFoot();
        VerticalLayout layout = new VerticalLayout(
                centre,
                foot
        );

        layout.setComponentAlignment(centre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(foot, Alignment.BOTTOM_CENTER);

        return layout;
    }

    private HorizontalLayout setCentreFoot() {
        return new HorizontalLayout();
    }

    private VerticalLayout setLayoutCentreVertical() {
        return new VerticalLayout(
                setLayoutCentreForm()
        );
    }

    private FormLayout setLayoutCentreForm() {
        return new FormLayout();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
