/*
 * Created by Martin Dünkelmann on 26.05.18 20:55
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 26.05.18 19:26
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.ui.*;

@Title("Grundgerüst - Profil - Unternehmen")
public class ProfilUnternehmen extends Abstract {
    public ProfilUnternehmen() {
        setContent(
                new VerticalLayout(
                        new VerticalLayout(
                                setProfilePictureLayout()
                        ),
                        new VerticalLayout(
                                setLayoutCentreForm()
                        )
                )
        );
    }

    public static String getName() {
        return "ProfilUnternehmen";
    }

    private FormLayout setLayoutCentreForm() {
        TextArea area = new TextArea("Neuigkeiten");
        area.setWordWrap(true);
        area.setHeight(500, Unit.PIXELS);
        area.setWidth(500, Unit.PIXELS);

        return new FormLayout(
                area,
                new Button(
                        "submit",
                        event -> area.setReadOnly(true)
                )
        );
    }

    private VerticalLayout setProfilePictureLayout() {
//TODO: Hier später Profilbild hinzufügen
        Upload upload = new Upload(
                "Upload it here",
                null
        );
        upload.setImmediateMode(false);

        return new VerticalLayout(upload);
    }
}
