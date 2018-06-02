package org.bonn.se.ss18.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import org.bonn.se.ss18.controller.LoggingControl;
import org.bonn.se.ss18.entity.User;
import org.bonn.se.ss18.service.Roles;

/**
 * @author rjourd2s
 */
public class MenueView extends VerticalLayout implements View {

    private LoggingControl logging = new LoggingControl();
    private User currentuser = (User) UI.getCurrent().getSession().getAttribute(Roles.CURREN_USER);

    HorizontalLayout upperSection = new HorizontalLayout();
    HorizontalLayout innerUpperSection = new HorizontalLayout();
    HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
    VerticalLayout menuLayout = new VerticalLayout();
    HorizontalLayout menuTitle = new HorizontalLayout();
    VerticalLayout contentLayout = new VerticalLayout();

    Label lblHeader;
    Label lblMenu;
    Button btnLogout;

    public static String getName() {
        return "MenueView";
    }

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
        upperSection.setHeight(4, UNITS_EM);

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

    public void setMenuTitle() {
        //set the menu title
        menuTitle.addComponent(lblMenu);
        menuLayout.addComponent(menuTitle);
        menuLayout.setWidth("100%");
        menuLayout.setComponentAlignment(menuTitle, Alignment.MIDDLE_CENTER);

    }

    public void addWelcomeText() {
        //create new label for welcome text
        String name = logging.getNameUser(currentuser);

        Label lblTitle = new Label("Welcome " + name + "!");
        lblTitle.addStyleName("h1");
        lblTitle.addStyleName("colored");

        // TODO
        lblHeader.setValue("" + logging.isStudenorUnternehmer(currentuser));

        contentLayout.addComponent(lblTitle);
        contentLayout.setMargin(new MarginInfo(false, false, false, true));

    }


    public void addDashboardOption(String caption) {
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

    public Component getComponent(String componentName) {
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

    public void addMenuOption(String caption, String componentName) {
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
        if (currentuser == null) {
            getUI().getNavigator().navigateTo("");
        }
        //Reseten der Compunenten
        menuLayout.removeAllComponents();
        contentLayout.removeAllComponents();

        //Componente die geadded werden je Nach User.
        setMenuTitle();
        addDashboardOption("Profil");
        if (logging.isStudenorUnternehmer(currentuser).equals("Student")) {
            addMenuOption("Persöhnliche Daten", "");
            addMenuOption("Profileinstellungen", "");
            addMenuOption("Dokumente", "");

        } else {
            addMenuOption("Persöhnliche Daten", "");
            addMenuOption("Profileinstellungen", "");
            addMenuOption("Stellen", "");
        }

        addWelcomeText();
    }
}
