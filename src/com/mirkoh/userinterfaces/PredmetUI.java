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
                case 0:
                    System.out.println("Izlaz");
                    break;

                case 1:
                    ispisiSvePredmete();
                    break;

                case 2:
                    pronadjiPredmetPoID();
                    break;

                case 3:
                    pronadjiPredmetPoImenu();
                    break;

                case 4:
                    dodajPredmet();
                    break;

                case 5:
                    obrisiPredmet();
                    break;

                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }

    public static void ispisiMeni() {
        System.out.println("\n");
        System.out.println("Rad sa predmetima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih predmeta");
        System.out.println("\tOpcija broj 2 - Ispis predmeta po ID");
        System.out.println("\tOpcija broj 3 - Ispisi predmet(e) po nazivu");
        System.out.println("\tOpcija broj 4 - Dodaj predmet");
        System.out.println("\tOpcija broj 5 - Obrisi predmet");
        System.out.println("\tOpcija broj 0 - IZLAZ");

    }

    public static void ispisiSvePredmete() {
        List<Predmet> sviPredmeti = PredmetDAO.pronadjiSvePredmete(Application.conn);
        sviPredmeti.forEach(System.out::println);
    }

    public static void pronadjiPredmetPoID() {
        Predmet predmet;
        System.out.println("Unesi ID predmeta:");
        int idPredmeta = ScannerWrapper.ocitajCeoBroj();
        predmet = PredmetDAO.pronadjiPredmetPoID(Application.conn, idPredmeta);

        if (predmet != null) {
            System.out.println(predmet);
        } else {
            System.out.println("Predmet sa ID: " + idPredmeta + " ne postoji");
        }
    }

    public static void pronadjiPredmetPoImenu() {
        List<Predmet> predmeti;
        System.out.println("Unesi ime predmeta:");
        String nazivPredmeta = ScannerWrapper.ocitajString();
        predmeti = PredmetDAO.pronadjiPredmetePoImenu(Application.conn, nazivPredmeta);

        if (predmeti.isEmpty()) {
            System.out.println("Ne postoji(e) predmeti za unesenim nazivom");
        } else {
            predmeti.forEach(System.out::println);
        }
    }

    public static void dodajPredmet() {
        System.out.println("Unesite naziv novog predmeta:");
        String nazivNovogPredmeta = ScannerWrapper.ocitajString();
        while (pronadjiPredmete(nazivNovogPredmeta).size() != 0) {
            System.out.println("Greska! Predmet sa nazivom: " + nazivNovogPredmeta +
                     " vec postoji u evidenciji.");
            System.out.println("Unesite ime predmeta:");
            nazivNovogPredmeta = ScannerWrapper.ocitajString();
        }

        Predmet novPredmet = new Predmet.Builder()
                .withNazivPredmeta(nazivNovogPredmeta)
                .withId(0)
                .build();

        boolean dodatNovPredmet = PredmetDAO.dodajNovPredet(Application.conn, novPredmet);
        if(dodatNovPredmet) {
            System.out.println("Predmet je uspesno dodat u bazu");
        } else {
            System.out.println("Doslo je do greske prilikom dodavanja novog predmeta");
            System.out.println("Pokusajte ponovo.");
        }
    }

    public static void obrisiPredmet() {
        Predmet pronadjenPredmet;
        System.out.println("Unesite ime predmeta koji zelite da obrisete:");
        String nazivPredmetaZaBrisanje = ScannerWrapper.ocitajString();
        pronadjenPredmet = pronadjiPredmet(nazivPredmetaZaBrisanje);
        while (pronadjenPredmet == null) {
            System.out.println("Predmet koji ste uneli ne postoji u evidenciji");
            nazivPredmetaZaBrisanje = ScannerWrapper.ocitajString();
        }
       Predmet predmet = new Predmet.Builder()
               .withNazivPredmeta(pronadjenPredmet.getNazivPredmeta())
               .withId(pronadjenPredmet.getId())
               .build();

        boolean obrisano = PredmetDAO.obrisiPredmet(Application.conn, predmet);
        if(obrisano) {
            System.out.println("Predmet je uspesno obrisan iz baze!");
        } else {
            System.out.println("Doslo je do greske prilikom brisanja");
        }
    }

    public static List<Predmet> pronadjiPredmete(String nazivPredmeta) {
       List<Predmet> pronadjenPredmet = PredmetDAO.pronadjiPredmetePoImenu(Application.conn,nazivPredmeta);
        return pronadjenPredmet;
    }

    public static Predmet pronadjiPredmet() {
        Predmet nadjenPredmet;
        System.out.println("Unesite ime predmeta:");
        String imePredmeta = ScannerWrapper.ocitajString();

        nadjenPredmet = pronadjiPredmet(imePredmeta);
        if(nadjenPredmet == null) {
            System.out.println("Greska! Predmet sa imenom " + imePredmeta + " ne postoji!");
        }

        return nadjenPredmet;
    }

    public static Predmet pronadjiPredmet(String nazivpredmeta) {
        Predmet predmet = PredmetDAO.pronadjiPredmetPoImenu(Application.conn, nazivpredmeta);
        return predmet;
    }
}
