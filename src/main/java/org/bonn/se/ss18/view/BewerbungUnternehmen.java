/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bonn.se.ss18.view;

import com.vaadin.data.Binder;
import com.vaadin.data.util.BeanUtil;
import com.vaadin.ui.UI;
import com.vaadin.ui.*;
import java.util.Arrays;
import java.util.List;
import org.bonn.se.ss18.entity.Bewerbung;
import org.bonn.se.ss18.entity.Bewerbunganlage;
import org.bonn.se.ss18.service.Roles;

/**
 *
 * @author Mohamad
 */
public class BewerbungUnternehmen extends Abstract{
    
    //private final Bewerbung bewerbung = (Bewerbung) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER)
    final VerticalLayout layout = new VerticalLayout();
    Bewerbunganlage b = new Bewerbunganlage();
    Bewerbung a = new Bewerbung();
    Binder<Bewerbung> ba = new Binder<>();
    
    
    public BewerbungUnternehmen(){
        
        List<Bewerbunganlage> list = Arrays.asList(
        new Bewerbunganlage()
        );
        
        Grid<Bewerbung> grid = new Grid<Bewerbung>(Bewerbung.class);
        grid.addColumn(Bewerbung::getLinuxid).setCaption("ID");
        grid.addColumn(Bewerbung::getUnternehmenid).setCaption("Unternehmen");
        grid.addColumn(Bewerbung::getDatum).setCaption("Datum");
        grid.addColumn(Bewerbung::getAnschreiben).setCaption("Anschreiben");
        grid.addColumn(Bewerbung::getLebenslauf).setCaption("Lebenslauf");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        
        
        
        
        
        
        Grid<Bewerbunganlage> gridanlage = new Grid<Bewerbunganlage>(Bewerbunganlage.class);
        gridanlage.addColumn(Bewerbunganlage::getBewerbungid).setCaption("ID");
        gridanlage.addColumn(Bewerbunganlage::getDatei).setCaption("Anlage");
        gridanlage.setSelectionMode(Grid.SelectionMode.SINGLE);
        
        
        
       
        layout.addComponent(grid);
        layout.addComponent(gridanlage);
    }
     public static String getName() {
        return "BewerbungUnternehmen";
    }
    
}
