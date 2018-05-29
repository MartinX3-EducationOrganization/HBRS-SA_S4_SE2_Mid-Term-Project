package org.bonn.se.ss18.view;

import com.vaadin.annotations.Title;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import com.vaadin.ui.renderers.ButtonRenderer;
import org.bonn.se.ss18.entity.Stellenausschreibung;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author rjourd2s
 */
@Title("Grundgerüst - Unternehmen - Stellenausschreibung")
public class StellenausschreibungUnternehmen extends Abstract {

    private final Grid<Stellenausschreibung> grid = new Grid<>();
    // Paar Daten zum Anschauen hier später die Stellen der Unternehmen nach ID sammeln.
    List<Stellenausschreibung> stellen = Arrays.asList(
            new Stellenausschreibung("Java Entwickler", "Wir suchen einen Java-Entwickler....\n asdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"),
            new Stellenausschreibung("Datenschutzbeauftragten", "Datenschutzbeauftragter wird gesucht! ..."));

    public StellenausschreibungUnternehmen() {
        HorizontalLayout content = setSiteLayout();

        content.setSizeFull();

        setContent(content);
    }

    public static String getName() {
        return "Stellenausschreibung";
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

        layoutCentre.setSizeFull();
        layoutLeft.setSizeUndefined();
        layoutRight.setSizeUndefined();

        layout.setComponentAlignment(layoutLeft, Alignment.TOP_LEFT);
        layout.setComponentAlignment(layoutRight, Alignment.TOP_RIGHT);

        return layout;
    }

    private VerticalLayout setLayoutCentre() {
        VerticalLayout centre = new VerticalLayout();
        Label head = new Label("Aktuelle Stellenausschreibung");

        grid.setItems(stellen);
        grid.addColumn(Stellenausschreibung::getTitle).setCaption("Titel").setMaximumWidth(200);
        grid.addColumn(Stellenausschreibung::getText).setCaption("Stellenbeschreibung").setMaximumWidth(250);
        //TODO -> Löschfunktion Zeile zu löschen
        grid.addColumn(stellen -> "Delete",
                new ButtonRenderer(clickEvent -> {
                    stellen.remove(clickEvent.getItem());
                    grid.setItems(stellen);
                })).setMaximumWidth(100);
        grid.setWidth("550");
        //grid.addItemClickListener(event -> function(centre));
        SingleSelectionModel<Stellenausschreibung> singleSelect =
                (SingleSelectionModel<Stellenausschreibung>) grid.getSelectionModel();
        singleSelect.addSelectionListener(event -> function(centre));
        centre.addComponent(grid);


        HorizontalLayout foot = new HorizontalLayout(
                new Link(
                        "Datenschutz",
                        new ExternalResource("http://vaadin.com/")
                ),
                new Link(
                        "Nutzungsbedingungen",
                        new ExternalResource("http://vaadin.com/")
                )
        );
        VerticalLayout layout = new VerticalLayout(
                head,
                centre,
                foot
        );

        layout.setComponentAlignment(head, Alignment.TOP_CENTER);
        layout.setComponentAlignment(centre, Alignment.MIDDLE_CENTER);
        layout.setComponentAlignment(foot, Alignment.BOTTOM_CENTER);

        return layout;
    }

    private void function(VerticalLayout centre) {
        Binder<Stellenausschreibung> binder = new Binder<>();

        TextField titleField = new TextField();
        RichTextArea textField = new RichTextArea();
        titleField.setCaption("Titel");


        binder.forField(titleField)
                .bind(Stellenausschreibung::getTitle, Stellenausschreibung::setTitle);

        binder.bind(textField, Stellenausschreibung::getText, Stellenausschreibung::setText);
        Stellenausschreibung stelle = new Stellenausschreibung("Bezeichnung", "lalalalalalallalaa");
        binder.readBean(stelle);

        Button saveButton = new Button("Save",
                event -> {
                    try {
                        //TODO -> Backend
                        binder.writeBean(stelle);
                    } catch (ValidationException e) {
                        Notification.show("Stellenausschreibung konnte nicht gespeichert werden, " +
                                "überprüfen Sie ihre Eingaben!");
                    }
                });
// Stellt nur die Werte zurück wie Sie waren
        Button resetButton = new Button("Reset",
                event -> binder.readBean(stelle));
        centre.addComponent(titleField);
        centre.addComponent(textField);
        centre.addComponent(saveButton);
        centre.addComponent(resetButton);
    }

    private VerticalLayout setLayoutLeft() {
        Image logo = new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/../resources/logo.png"))
        );

        logo.setHeight("100");
        logo.setWidth("100");

        return new VerticalLayout(logo);
    }

    private VerticalLayout setLayoutRight() {
        Image logo = new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/../resources/logo.png"))
        );

        logo.setHeight("100");
        logo.setWidth("100");
        return new VerticalLayout(logo);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Page.getCurrent().setTitle("Stellenausschreibungen");
    }


}
