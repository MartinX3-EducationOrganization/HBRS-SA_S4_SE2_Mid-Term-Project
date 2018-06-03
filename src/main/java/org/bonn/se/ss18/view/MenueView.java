package org.bonn.se.ss18.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.LoggingControl;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Roles;

/**
 * @author rjourd2s
 */
public class MenueView extends VerticalLayout implements View {
    private final LoggingControl logging = new LoggingControl();
    private final User currentuser = (User) UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER);

    private final HorizontalLayout upperSection = new HorizontalLayout();
    private final HorizontalLayout innerUpperSection = new HorizontalLayout();
    private final HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    private final VerticalLayout menuLayout = new VerticalLayout();
    private final HorizontalLayout menuTitle = new HorizontalLayout();
    private final VerticalLayout contentLayout = new VerticalLayout();

    private final Label lblHeader;
    private final Label lblMenu;
    private final Button btnLogout;

    public MenueView() {

        //UI Components

        lblHeader = new Label("");
        lblHeader.addStyleName("colored");
        lblHeader.addStyleName("h2");
        lblHeader.setSizeUndefined();

        btnLogout = new Button("Sign Out");
        btnLogout.addStyleName("small");
        btnLogout.addStyleName("friendly");
        btnLogout.setSizeUndefined();
        btnLogout.addClickListener(new Button.ClickListener() {

            // Bei Logout ausloggen und wieder auf Startseite.
            @Override
            public void buttonClick(Button.ClickEvent event) {
                logging.logout();
            }
        });

        lblMenu = new Label("Menu");
        lblMenu.addStyleName("colored");
        lblMenu.addStyleName("h2");


        innerUpperSection.addComponent(lblHeader);
        innerUpperSection.addComponent(btnLogout);
        innerUpperSection.setExpandRatio(btnLogout, 1);
        innerUpperSection.setSpacing(true);
        innerUpperSection.setComponentAlignment(btnLogout, Alignment.MIDDLE_RIGHT);

        upperSection.setSizeFull();
        upperSection.addComponent(innerUpperSection);

        upperSection.setMargin(new MarginInfo(false, true, false, false));
        upperSection.setComponentAlignment(innerUpperSection, Alignment.TOP_RIGHT);
        upperSection.addStyleName("borderBottom");
        upperSection.setHeight(4, Sizeable.UNITS_EM);

        //  Menü
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

        lowerSection.addComponent(menuLayout);
        lowerSection.addComponent(contentLayout);
        contentLayout.setSizeFull();
        lowerSection.setSizeFull();
        lowerSection.setSplitPosition(15);

        addComponent(upperSection);
        addComponent(lowerSection);

        setSizeFull();

        setExpandRatio(lowerSection, 1);


    }

    public static String getName() {
        return "MenueView";
    }

    private void setMenuTitle() {
        //set the menu title
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

    }

    private void addWelcomeText() {
        //create new label for welcome text
        String name = logging.getNameUser(currentuser);

        Label lblTitle = new Label("Welcome " + name + "!");
        lblTitle.addStyleName("h1");
        lblTitle.addStyleName("colored");

        // TODO
        lblHeader.setValue("" + logging.isStudendOrUnternehmer(currentuser));

        contentLayout.addComponent(lblTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));

    }


    private void addDashboardOption(String caption) {
        //set menu buttons

        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);

        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                contentLayout.removeAllComponents();
                addWelcomeText();
            }

        });
    }

    //TODO in Switch kommt noch mehr
    private Component getComponent(String componentName) {
        if (componentName.equals("")) {
            return new Login();
        } else if (componentName.equals("")) {
            return new Login();
        } else if (componentName.equals("")) {
            return new Login();
        } else {
            return new Login();
        }
    }

    private void addMenuOption(String caption, String componentName) {
        Button button = new Button(caption);
        button.setWidth("100%");
        button.setStyleName("borderless");
        menuLayout.addComponent(button);
        button.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                contentLayout.removeAllComponents();
                contentLayout.addComponent(getComponent(componentName));
            }

        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Wenn Logout oder Session beendet keine Weg zurück!
        if (currentuser == null) {
            getUI().getNavigator().navigateTo("");
        }
        //Reseten der Componenten
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        //Componente die geadded werden je Nach User.
        setMenuTitle();
        addDashboardOption("Profil");

        switch (logging.isStudendOrUnternehmer(currentuser)) {
            case "Student":
                addMenuOption("Persöhnliche Daten", "");
                addMenuOption("Profileinstellungen", "");
                addMenuOption("Dokumente", "");
                break;
            case "Unternehmer":
                addMenuOption("Persöhnliche Daten", "");
                addMenuOption("Profileinstellungen", "");
                addMenuOption("Stellen", "");
                break;
            default:
                UI.getCurrent().getNavigator().navigateTo(Login.getName());
        }

        addWelcomeText();
    }
}
