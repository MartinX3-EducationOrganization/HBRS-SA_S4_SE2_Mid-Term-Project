/*
 * Created by Martin Dünkelmann on 26.05.18 20:55
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 19:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

@Title("Grundgerüst - Profil - Unternehmen")
public class ProfilUnternehmen extends Abstract {
    public ProfilUnternehmen() {
        setContent(setSiteLayout());
    }

    public static String getName() {
        return "ProfilUnternehmen";
    }

    private HorizontalLayout setSiteLayout() {
        final VerticalLayout layoutCentre = setLayoutCentre();
        final VerticalLayout layoutRight = setLayoutRight();
        final HorizontalLayout layout = new HorizontalLayout(
                layoutCentre,
                layoutRight
        );

        layout.setComponentAlignment(layoutCentre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(layoutRight, Alignment.TOP_RIGHT);

        return layout;
    }

    private VerticalLayout setLayoutCentre() {
        final VerticalLayout centre = setLayoutCentreVertical();
        final HorizontalLayout foot = setCentreFoot();
        final VerticalLayout layout = new VerticalLayout(
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

    private VerticalLayout setLayoutLeft() {
        final Image logo = new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png"))
        );

        logo.setHeight("100");
        logo.setWidth("100");

        return new VerticalLayout(logo);
    }

    private VerticalLayout setLayoutRight() {
        return new VerticalLayout();
    }
}
