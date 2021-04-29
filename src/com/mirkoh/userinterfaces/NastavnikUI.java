package com.mirkoh.userinterfaces;

import com.mirkoh.Application;
import com.mirkoh.dao.NastavnikDAO;
import com.mirkoh.model.Nastavnik;
import com.mirkoh.utils.ScannerWrapper;

import java.util.List;

public class NastavnikUI {

    public static void meni() {
        int odluka = -1;
        while (odluka != 0) {
            ispisiMeni();
            System.out.println("Opcija:");
            odluka = ScannerWrapper.ocitajCeoBroj();

            switch (odluka) {
                case 0:
                    System.out.println("Izlaz");
                    break;
                case 1:
                    ispisiSveNastavnike();
                    break;
                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }

    private static void ispisiMeni() {
        System.out.println("Rad sa nastavnicima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih nastavnika");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }


    private static void ispisiSveNastavnike() {
        List<Nastavnik> sviNastavnici = NastavnikDAO.pronadjiSveNastavnike(Application.conn);
        sviNastavnici.forEach(System.out::println);
    }


}
