
package org.bonn.se.ss18.view;

/**
 *
 * @author Abdullah.Mohamad
 */

import com.vaadin.annotations.Title;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.io.File;

import static com.vaadin.ui.themes.ValoTheme.OPTIONGROUP_HORIZONTAL;

@Title("Registrierung")
public class RegistrationUnternehmen extends Abstract {
    public RegistrationUnternehmen() {
//HauptLayout
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);

//Überschrift
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
        CheckBoxGroup<String> useConditionsCheckbox = setItemGroup(new CheckBoxGroup<>(), null, "");
        useConditionsCheckbox.setRequiredIndicatorVisible(true);
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
        HorizontalLayout buttonlayout = setLayout(
                new HorizontalLayout(
                        new Button(
                        "Registrieren",
                        event -> {
                            UI.getCurrent().getNavigator().navigateTo(ProfilUnternehmen.getName());
                            Notification.show("Vielen Dank");
                        })
                )
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
        logo.setWidth(200, Unit.PIXELS);
        logo.setHeight(200, Unit.PIXELS);
        return logo;
    }

    private FormLayout setForm() {
        FormLayout form = setLayout(
                new FormLayout(
                        setItemGroup(new RadioButtonGroup<>("Anrede"), OPTIONGROUP_HORIZONTAL, "Herr", "Frau"),
                        setFormularItem(new TextField(), "Name, Vorname", VaadinIcons.USER),
                        setFormularItem(new TextField(), "Unternehmen", VaadinIcons.OFFICE),
                        setFormularItem(new DateField(), "Geburtstag", VaadinIcons.CALENDAR_USER),
                        setFormularItem(new TextField(), "Email", VaadinIcons.MAILBOX),
                        setFormularItem(new TextField(), "Telefon", VaadinIcons.PHONE)
                )
        );

//PLZ
        AbstractField<String> plz = setFormularItem(new TextField(), "Ort, PLZ", VaadinIcons.HOME);
        form.addComponent(plz);
        form.setComponentAlignment(plz, Alignment.MIDDLE_CENTER);
        return form;
    }

    private <T extends AbstractField> T setFormularItem(T item, String title, VaadinIcons icon) {
        item.setCaption(title);
        item.setIcon(icon);
        item.setRequiredIndicatorVisible(true);

        return item;
    }

    private <T extends AbstractOrderedLayout> T setLayout(T layout) {
        layout.setMargin(true);
        layout.setWidth(null);

        return layout;
    }

    private <T extends AbstractListing> T setItemGroup(T group, String style, String... items) {
        group.setItems(items);
        group.addStyleName(style);

        return group;
    }
}
