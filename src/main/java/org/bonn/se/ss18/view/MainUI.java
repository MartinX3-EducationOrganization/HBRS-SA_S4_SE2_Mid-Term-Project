/*
 * Created by Martin Dünkelmann on 03.06.18 17:19
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 03.06.18 17:19
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.bonn.se.ss18.entity.Student;
import org.bonn.se.ss18.entity.Unternehmer;
import org.bonn.se.ss18.service.Roles;

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
    //        UI.getCurrent().getNavigator().addViewChangeListener();

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        CssLayout viewContainer = new CssLayout();

        UI.getCurrent().setNavigator(new Navigator(this, viewContainer));
        addViews();
        UI.getCurrent().getNavigator().navigateTo(Login.getName());

        UI.getCurrent().getNavigator().addViewChangeListener(
                event -> rebuildMenu(event, viewContainer)
        );
    }

    private boolean rebuildMenu(ViewChangeListener.ViewChangeEvent event, CssLayout viewContainer) {
        HorizontalLayout layout = new HorizontalLayout();

        if (UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER) != null) {
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

        if (UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER) != null) {
            HorizontalLayout headerMenu = getHeaderMenu();
            centerLayout.addComponent(headerMenu);
            centerLayout.setComponentAlignment(headerMenu, Alignment.MIDDLE_RIGHT);
        }

        centerLayout.addComponent(viewContainer);

        return centerLayout;
    }

    private HorizontalLayout getHeaderMenu() { //TODO: getHeaderMenu
        return new HorizontalLayout(
                new Button(
                        "Logout",
                        event -> {
                            UI.getCurrent().getNavigator().navigateTo(Login.getName());
                            UI.getCurrent().getSession().close();
                        }
                )
        );
    }

    private CssLayout getLeftSideMenu() { //TODO: getLeftSideMenu
        Button[] buttons = null;

        if (UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER) instanceof Student) {
            buttons = getStudentMenu();
        } else if (UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER) instanceof Unternehmer) {
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
                e -> getNavigator().navigateTo("")//TODO: Stellen
        );
        button.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );
        buttons.add(button);

        return buttons.toArray(new Button[0]);
    }

    private void addViews() {
        UI.getCurrent().getNavigator().addView(BewerbenStudent.getName(), BewerbenStudent.class);
        UI.getCurrent().getNavigator().addView(Login.getName(), Login.class);
        UI.getCurrent().getNavigator().addView(MenueView.getName(), MenueView.class);
        UI.getCurrent().getNavigator().addView(ProfilStudent.getName(), ProfilStudent.class);
        UI.getCurrent().getNavigator().addView(ProfilUnternehmen.getName(), ProfilUnternehmen.class);
        UI.getCurrent().getNavigator().addView(RegistrationUnternehmen.getName(), RegistrationUnternehmen.class);
        UI.getCurrent().getNavigator().addView(StellenausschreibungUnternehmen.getName(), StellenausschreibungUnternehmen.class);
    }
}
