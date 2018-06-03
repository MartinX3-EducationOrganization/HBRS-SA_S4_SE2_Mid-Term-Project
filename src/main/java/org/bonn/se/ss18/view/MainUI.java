/*
 * Created by Martin Dünkelmann on 03.06.18 17:19
 * Copyright (c) 2018. All rights reserved.
 *
 * Last modified 03.06.18 17:19
 */

package org.bonn.se.ss18.view;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import java.io.File;

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
@Theme("maintheme")
public class MainUI extends UI {
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        CssLayout viewContainer = new CssLayout();
        viewContainer.setSizeFull();
        UI.getCurrent().setNavigator(new Navigator(this, viewContainer));
        addViews();
        UI.getCurrent().getNavigator().navigateTo(Login.getName());

        HorizontalLayout layout = new HorizontalLayout(
                getLeftSideMenu(),
                getCenterLayout(viewContainer)
        );
        layout.setHeight(100, Unit.PERCENTAGE);
        setContent(layout);
    }

    private VerticalLayout getCenterLayout(CssLayout viewContainer) {
        HorizontalLayout headerMenu = getHeaderMenu();
        VerticalLayout layout = new VerticalLayout(
                headerMenu,
                viewContainer
        );
        layout.setComponentAlignment(headerMenu, Alignment.MIDDLE_CENTER);
        return layout;
    }

    private HorizontalLayout getHeaderMenu() { //TODO: getHeaderMenu
        return new HorizontalLayout(
                new Button(
                        "Startseite",
                        event -> UI.getCurrent().getNavigator().navigateTo(Profil.getName())
                ),
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
        Image logo = new Image(
                null,
                new FileResource(new File(VaadinService.getCurrent().getBaseDirectory().getAbsolutePath() + "/WEB-INF/classes/logo.png"))
        );
        logo.addClickListener(
                event -> UI.getCurrent().getNavigator().navigateTo(Profil.getName())
        );
        logo.addStyleName(ValoTheme.MENU_LOGO);

        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);

        Button view1 = new Button("View 1", e -> getNavigator().navigateTo("view1"));
        view1.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);
        Button view2 = new Button("View 2", e -> getNavigator().navigateTo("view2"));
        view2.addStyleNames(ValoTheme.BUTTON_LINK, ValoTheme.MENU_ITEM);

        CssLayout menu = new CssLayout(logo, title, view1, view2);
        menu.addStyleName(ValoTheme.MENU_ROOT);
        menu.setWidth(20, Unit.PERCENTAGE);
        return menu;
    }

    private void addViews() {
        UI.getCurrent().getNavigator().addView(BewerbenStudent.getName(), BewerbenStudent.class);
        UI.getCurrent().getNavigator().addView(Login.getName(), Login.class);
        UI.getCurrent().getNavigator().addView(MenueView.getName(), MenueView.class);
        UI.getCurrent().getNavigator().addView(Profil.getName(), Profil.class);
        UI.getCurrent().getNavigator().addView(ProfilUnternehmen.getName(), ProfilUnternehmen.class);
        UI.getCurrent().getNavigator().addView(RegistrationUnternehmen.getName(), RegistrationUnternehmen.class);
        UI.getCurrent().getNavigator().addView(StellenausschreibungUnternehmen.getName(), StellenausschreibungUnternehmen.class);
    }

    //        UI.getCurrent().getNavigator().addViewChangeListener();
}
