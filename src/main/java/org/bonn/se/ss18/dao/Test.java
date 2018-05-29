package org.bonn.se.ss18.dao;

import org.bonn.se.ss18.entity.User;

/**
 * @author rjourd2s
 */
public class Test {
    public static void main(String[] args) {
        UserDao a = new UserDao();
        User b = new User();
        b.setiD(3);
        b.setPasswort("NEUS");
        b.setStrasse("Weg");
        b.setHausnr("18");
        b.setPlz("53757");
        b.setOrt("Sankt Augustin");
        b.setEmail("emmail@email.de");
        b.setTelNr("0716180");
        b.setFaxNr("298189");
        b.setKurzVorstellung("lala");
        System.out.println(a.update(b));
        System.out.println(a.read(2));
    }
}
