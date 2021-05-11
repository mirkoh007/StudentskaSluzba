package com.mirkoh.userinterfaces;

import com.mirkoh.Application;
import com.mirkoh.dao.PredmetDAO;
import com.mirkoh.model.Predmet;
import com.mirkoh.utils.ScannerWrapper;

import java.util.List;

public class PredmetUI {

    public static void meni() {
        int odluka = -1;
        while (odluka != 0) {
            ispisiMeni();
            System.out.println("Opcija: ");
            odluka = ScannerWrapper.ocitajCeoBroj();

            switch (odluka) {
                case 1:
                    ispisiSvePredmete();
                    break;

                case 2:
                    pronadjiPredmetPoImenu();
                    break;

                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }

    public static void ispisiMeni() {
        System.out.println("Rad sa predmetima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih predmeta");
        System.out.println("\tOpcija broj 2 - Ispisi predmet(e) po nazivu");
        System.out.println("\tOpcija broj 0 - IZLAZ");

    }

    public static void ispisiSvePredmete() {
        List<Predmet> sviPredmeti = PredmetDAO.pronadjiSvePredmete(Application.conn);
        sviPredmeti.forEach(System.out::println);
    }

    public static void pronadjiPredmetPoImenu() {
        List<Predmet> predmeti;
        System.out.printf("Unesi ime predmeta:");
        String nazivPredmeta = ScannerWrapper.ocitajString();
        predmeti = PredmetDAO.pronadjiPredmetPoImenu(Application.conn,nazivPredmeta);

        if(predmeti.isEmpty()) {
            System.out.println("Ne postoji(e) predmeti za unesenim nazivom");
        } else {
            predmeti.forEach(System.out::println);
        }
    }
}
