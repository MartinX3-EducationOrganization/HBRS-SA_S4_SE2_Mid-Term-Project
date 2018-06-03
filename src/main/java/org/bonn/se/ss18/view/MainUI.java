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
import org.bonn.se.ss18.service.Roles;

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
        CssLayout leftSideMenu = getLeftSideMenu();
        HorizontalLayout layout = new HorizontalLayout(
                leftSideMenu,
                getCenterLayout(viewContainer)
        );
        layout.setHeight(100, Unit.PERCENTAGE);
        setContent(layout);

        return true;
    }

    private VerticalLayout getCenterLayout(CssLayout viewContainer) {
        VerticalLayout centerLayout = new VerticalLayout();
        centerLayout.addComponent(viewContainer);

        if (UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER) != null) {
            HorizontalLayout headerMenu = getHeaderMenu();
            centerLayout.addComponent(headerMenu);
            centerLayout.setComponentAlignment(headerMenu, Alignment.MIDDLE_RIGHT);
        }

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
        Label title = new Label("Menu");
        title.addStyleName(ValoTheme.MENU_TITLE);
        Button profil = new Button(
                "Profil",
                e -> getNavigator().navigateTo(ProfilStudent.getName())
        );
        profil.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );

        Button view2 = new Button(
                "Profileinstellungen",
                e -> getNavigator().navigateTo("Profileinstellungen") //TODO: Profileinstellungen
        );
        view2.addStyleNames(
                ValoTheme.BUTTON_LINK,
                ValoTheme.MENU_ITEM
        );

        CssLayout layout = new CssLayout(
                title,
                profil,
                view2
        );
        layout.addStyleName(ValoTheme.MENU_ROOT);
        return layout;
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
