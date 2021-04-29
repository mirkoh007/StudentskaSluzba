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
                case 2:
                    pronadjiNastavnikaPoID();
                    break;
                case 3:
                    pronadjiNastavnikaPoImenu();
                    break;
                case 4:
                    pronadjiNastavnikaPoPrezimenu();
                    break;
                case 5:
                    pronadjiNastavnikaPoZvanju();
                    break;
                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }


    public static void ispisiMeni() {
        System.out.println("\nRad sa nastavnicima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih nastavnika");
        System.out.println("\tOpcija broj 2 - Pronadji nastavnika po ID");
        System.out.println("\tOpcija broj 3 - Pronadji nastavnika po IMENU");
        System.out.println("\tOpcija broj 4 - Pronadji nastavnika po PREZIMENU");
        System.out.println("\tOpcija broj 5 - Pronadji nastavnika po ZVANJU");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }


    public static void ispisiSveNastavnike() {
        List<Nastavnik> sviNastavnici = NastavnikDAO.pronadjiSveNastavnike(Application.conn);
        sviNastavnici.forEach(System.out::println);
    }

    public static void pronadjiNastavnikaPoID() {
        Nastavnik nastavnik;
        System.out.println("Unesi ID nastavnika: ");
        int id = ScannerWrapper.ocitajCeoBroj();
        nastavnik = NastavnikDAO.pronadjiNastavnikaPoID(Application.conn, id);
        if(nastavnik != null) {
            System.out.println(nastavnik);
        } else {
            System.out.println("Nastavnik sa id " + id + " ne postoji." + "\n");
        }
    }

   public static void pronadjiNastavnikaPoImenu() {
       List<Nastavnik> nastavnici;
       System.out.println("Unesi ime nastavnika:");
       String ime = ScannerWrapper.ocitajString();
       nastavnici = NastavnikDAO.pronadjiNastavnikaPoImenu(Application.conn, ime);
       if(nastavnici.size() != 0) {
           nastavnici.forEach(System.out::println);
       } else {
           System.out.println("Nastavnik(ci) sa imenom " + ime +" ne postoji(e)");
       }
   }

    public static void pronadjiNastavnikaPoPrezimenu() {
        List<Nastavnik> nastavnici;
        System.out.println("Unesi prezime nastavnika:");
        String prezime = ScannerWrapper.ocitajString();
        nastavnici = NastavnikDAO.pronadjiNastavnikaPoPrezimenu(Application.conn, prezime);
        if(nastavnici.size() != 0) {
            nastavnici.forEach(System.out::println);
        } else {
            System.out.println("Nastavnik(ci) sa prezimenom " + prezime + " nepostoji(e)");
        }
    }

    public static void pronadjiNastavnikaPoZvanju() {
        List<Nastavnik> nastavnici;
        System.out.println("\nUnesi zvanje nastavnika: ");
        String zvanje = ScannerWrapper.ocitajString();
        nastavnici = NastavnikDAO.pronadjiNastavnikaPoZvanju(Application.conn, zvanje);
        if(nastavnici.size() != 0) {
            nastavnici.forEach(System.out::println);
        } else {
            System.out.println("Nastavnik(ci) sa zvanjem " + zvanje + " nepostoji(e)");
        }

    }


}
