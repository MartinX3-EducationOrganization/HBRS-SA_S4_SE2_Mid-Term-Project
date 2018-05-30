/*
 * Created by Martin Dünkelmann on 29.05.18 21:49
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 28.05.18 22:04
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;

import java.io.File;

@Title("Grundgerüst - Profil - Student - Bewerben")
public class BewerbenStudent extends Abstract {
    public BewerbenStudent() {
        HorizontalLayout content = setSiteLayout();

        content.setSizeFull();

        setContent(content);
    }

    public static String getName() {
        return "BewerbenStudent";
    }

    private HorizontalLayout setSiteLayout() {
        VerticalLayout layoutLeft = setLayoutLeft();
        VerticalLayout layoutCentre = setLayoutCentre();
        VerticalLayout layoutRight = setLayoutRight();
        HorizontalLayout layout = new HorizontalLayout(
                layoutLeft,
                layoutCentre,
                layoutRight
        );

        layoutLeft.setSizeUndefined();
        layoutCentre.setSizeFull();
        layoutRight.setSizeUndefined();

        layout.setComponentAlignment(layoutLeft, Alignment.TOP_LEFT);
        layout.setComponentAlignment(layoutCentre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(layoutRight, Alignment.TOP_RIGHT);

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

    private VerticalLayout setLayoutLeft() {
        Image logo = new Image(
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

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
