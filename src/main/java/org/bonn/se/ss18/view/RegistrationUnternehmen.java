
package org.bonn.se.ss18.view;

/**
 *
 * @author Abdullah.Mohamad
 */

import com.vaadin.annotations.Title;
import com.vaadin.data.HasValue;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.RegistrationController;
import org.bonn.se.ss18.dto.UnternehmerDTO;

import java.io.File;

@Title("Registrierung")
public class RegistrationUnternehmen extends Abstract {
    UnternehmerDTO unternehmerDTO = new UnternehmerDTO();

    public RegistrationUnternehmen() {
//HauptLayout
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

//Überschrift #
        Label label = new Label(
                "<font size = \"6\" color=\"black\"> Registrierung",
                ContentMode.HTML
        );
        layout.addComponent(label);
        layout.setComponentAlignment(label, Alignment.TOP_CENTER);

//Logo
        Image logo = setLogo();
        layout.addComponent(logo);
        layout.setComponentAlignment(logo, Alignment.TOP_LEFT);

        Label label2 = new Label(
                "<font size = \"4\" color=\"black\"> Jetzt als Unternehmen registrieren und potenzielle Bewerber finden",
                ContentMode.HTML
        );
        layout.addComponent(label2);
        layout.setComponentAlignment(label2, Alignment.TOP_CENTER);

        Label label3 = new Label(
                "<font size = \"5\" color=\"black\"> Persöhnliche Daten",
                ContentMode.HTML
        );
        layout.addComponent(label3);
        layout.setComponentAlignment(label3, Alignment.TOP_CENTER);

//Formular
        FormLayout form = setForm();
        layout.addComponent(form);
        layout.setComponentAlignment(form, Alignment.MIDDLE_CENTER);

//Nutzungsbedingungen
        CheckBox useConditionsCheckbox = new CheckBox(null, false);
        useConditionsCheckbox.setRequiredIndicatorVisible(true);
        useConditionsCheckbox.addValueChangeListener(x -> unternehmerDTO.setCheckboxAGB(x.getValue()));
        HorizontalLayout fittingLayout = setLayout(
                new HorizontalLayout(
                        useConditionsCheckbox,
                        new Label(
                                "Ich habe die " + "<a href='http://google.de'> Nutzungsbedingungen</a>" + " gelesen und akzeptiere sie",
                                ContentMode.HTML
                        )
                )
        );
        layout.addComponent(fittingLayout);
        layout.setComponentAlignment(fittingLayout, Alignment.BOTTOM_CENTER);

//Button Registrieren
        Button registrieren = new Button(
                "Registrieren",
                event -> {
                    if (unternehmerDTO.isCheckboxAGB()) {
                        new RegistrationController().registration(unternehmerDTO);
                        UI.getCurrent().getNavigator().navigateTo(Login.getName());
                        Notification.show("Vielen Dank");
                    } else {
                        Notification.show("Bitte AGB's bestätigen!");
                    }
                });
        registrieren.setId("registrieren");
        HorizontalLayout buttonlayout = setLayout(
                new HorizontalLayout(registrieren)
        );
        layout.addComponent(buttonlayout);
        layout.setComponentAlignment(buttonlayout, Alignment.BOTTOM_CENTER);

        setContent(layout);
    }

    public static String getName() {
        return "RegistrationUnternehmen";
    }

    private Image setLogo() {
        Image logo = new Image(
                null,
                new FileResource(
                        new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png")
                )
        );
        logo.setHeight(100, Unit.PIXELS);
        return logo;
    }

    private FormLayout setForm() {
        return setLayout(
                new FormLayout(
                        setFormularItem(new TextField(), "Unternehmen", VaadinIcons.OFFICE, x -> unternehmerDTO.setFirmenname(x.getValue())),
                        setFormularItem(new TextField(), "Email", VaadinIcons.MAILBOX, x -> unternehmerDTO.setEmail(x.getValue())),
                        setFormularItem(new PasswordField(), "Passwort", VaadinIcons.PASSWORD, x -> unternehmerDTO.setPasswort(x.getValue())),
                        setFormularItem(new TextField(), "Telefon", VaadinIcons.PHONE, x -> unternehmerDTO.setTelNr(x.getValue())),
                        setFormularItem(new TextField(), "Ort", VaadinIcons.HOME, x -> unternehmerDTO.setOrt(x.getValue())),
                        setFormularItem(new TextField(), "PLZ", VaadinIcons.HOME, x -> unternehmerDTO.setPlz(x.getValue()))
                )
        );
    }

    private TextField setFormularItem(TextField item, String title, VaadinIcons icon, HasValue.ValueChangeListener<String> listener) {
        item.setCaption(title);
        item.setIcon(icon);
        item.setRequiredIndicatorVisible(true);
        item.addValueChangeListener(listener);

        return item;
    }

    private <T extends AbstractOrderedLayout> T setLayout(T layout) {
        layout.setMargin(true);
        layout.setWidth(null);

        return layout;
    }
}
