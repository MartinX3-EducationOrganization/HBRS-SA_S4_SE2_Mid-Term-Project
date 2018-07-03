/*
 * Created by Martin Dünkelmann on 03.06.18 17:19
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 03.06.18 17:19
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.controller.LoginController;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Roles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author martin on 03.06.18
 * @project wi-inf_se2_2018_grundgeruest
 * <p>
 * This UI is the application entry point. A UI may either represent a browser window
 * (or tab) or some part of an HTML page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@PreserveOnRefresh
@Theme("maintheme")
public class MainUI extends UI {
    LoginController loginController = new LoginController();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        CssLayout viewContainer = new CssLayout();

        UI.getCurrent().setNavigator(new Navigator(this, viewContainer));
        addViews();
        UI.getCurrent().getNavigator().navigateTo(LoginView.getName());

        UI.getCurrent().getNavigator().addViewChangeListener(
                event -> rebuildMenu(event, viewContainer)
        );
    }

    private boolean rebuildMenu(ViewChangeListener.ViewChangeEvent event, CssLayout viewContainer) {
        HorizontalLayout layout = new HorizontalLayout();

        if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) != null) {
            CssLayout leftSideMenu = getLeftSideMenu();
            leftSideMenu.setHeight(100, Unit.PERCENTAGE);
            layout.addComponent(leftSideMenu);
        }

        layout.addComponent(getCenterLayout(viewContainer));
        setContent(layout);

        return true;
    }

    private VerticalLayout getCenterLayout(CssLayout viewContainer) {
        VerticalLayout centerLayout = new VerticalLayout();

        if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) != null) {
            HorizontalLayout headerMenu = getHeaderMenu();
            centerLayout.addComponent(headerMenu);
            centerLayout.setComponentAlignment(headerMenu, Alignment.TOP_CENTER);
        }

        HorizontalLayout footer = getFooter();
        centerLayout.addComponents(viewContainer, footer);
        centerLayout.setComponentAlignment(footer, Alignment.BOTTOM_CENTER);

        return centerLayout;
    }

    private HorizontalLayout getFooter() {
        return new HorizontalLayout(
                new Link(
                        "Datenschutz",
                        new ExternalResource("http://vaadin.com/")
                ),
                new Link(
                        "Nutzungsbedingungen",
                        new ExternalResource("http://vaadin.com/")
                )
        );
    }

    private HorizontalLayout getHeaderMenu() {
        MenuBar menuBar = getHeaderMenuBar();

        Image logo = new Image(
                null,
                new FileResource(
                        new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png")
                )
        );
        logo.setHeight(37, Unit.PIXELS);

        HorizontalLayout layout = new HorizontalLayout(
                logo,
                menuBar
        );

        layout.setComponentAlignment(logo, Alignment.TOP_LEFT);
        layout.setComponentAlignment(menuBar, Alignment.TOP_RIGHT);

        return layout;
    }

    private MenuBar getHeaderMenuBar() {
        MenuBar menuBar = new MenuBar();
        menuBar.addItem("Nachrichten(0)");
        menuBar.addItem("Kontakt");
        menuBar.addItem(
                "Hilfe",
                VaadinIcons.QUESTION_CIRCLE,
                null
        );
        MenuBar.MenuItem item = menuBar.addItem(
                "Logout", event -> {
                    loginController.logout();
                   // menuBar.setId("logout");
                }
        );
        item.setStyleName("log-out");



        return menuBar;
    }

    private CssLayout getLeftSideMenu() {
        Button[] buttons = null;

        if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Student) {
            buttons = getStudentMenu();
        } else if (UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER) instanceof Unternehmer) {
            buttons = getUnternehmerMenu();
        }

        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
        CssLayout layout = new CssLayout(
                title
        );

        if (buttons != null) {
            layout.addComponents(buttons);
        }
        layout.addStyleName(ValoTheme.MENU_ROOT);


        return layout;
    }

    private Button[] getStudentMenu() {
        List<Button> buttons = new ArrayList<>();

        Button button = new Button(
                "Profil",
                e -> getNavigator().navigateTo(ProfilStudent.getName())
        );
        button.setId("profil");
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Persönliche Daten",
                e -> getNavigator().navigateTo("")//TODO: Persönliche Daten
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Profileinstellungen",
                e -> getNavigator().navigateTo("")//TODO: Profileinstellungen
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Bewerben",
                e -> getNavigator().navigateTo(BewerbenStudent.getName())
        );
        button.setId("bewerben");
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Dokumente",
                e -> getNavigator().navigateTo("")//TODO: Dokumente
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        return buttons.toArray(new Button[0]);
    }

    private Button[] getUnternehmerMenu() {
        List<Button> buttons = new ArrayList<>();

        Button button = new Button(
                "Profil",
                e -> getNavigator().navigateTo(ProfilUnternehmen.getName())
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Persönliche Daten",
                e -> getNavigator().navigateTo("")//TODO: Persönliche Daten
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Profileinstellungen",
                e -> getNavigator().navigateTo("")//TODO: Profileinstellungen
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        button = new Button(
                "Stellen",
                e -> getNavigator().navigateTo("Stellenausschreibung")//TODO: Stellen
        );
        button.setId("stellen");
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        return buttons.toArray(new Button[0]);
    }

    private void addViews() {
        UI.getCurrent().getNavigator().addView(BewerbenStudent.getName(), BewerbenStudent.class);
        UI.getCurrent().getNavigator().addView(LoginView.getName(), LoginView.class);
        UI.getCurrent().getNavigator().addView(ProfilStudent.getName(), ProfilStudent.class);
        UI.getCurrent().getNavigator().addView(ProfilUnternehmen.getName(), ProfilUnternehmen.class);
        UI.getCurrent().getNavigator().addView(RegistrationUnternehmen.getName(), RegistrationUnternehmen.class);
        UI.getCurrent().getNavigator().addView(StellenausschreibungUnternehmen.getName(), StellenausschreibungUnternehmen.class);
    }
}
