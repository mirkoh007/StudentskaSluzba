package com.mirkoh.userinterfaces;


import com.mirkoh.Application;
import com.mirkoh.dao.PohadjaDAO;
import com.mirkoh.dao.PredmetDAO;
import com.mirkoh.dao.StudentDAO;
import com.mirkoh.model.Predmet;
import com.mirkoh.model.Student;
import com.mirkoh.utils.ScannerWrapper;

import java.util.List;

public class PohadjaUI {

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
                    ispisiPredmeteZaStudenta();
                    break;

                case 2:
                    ispisiStudenteZaPredmet();
                    break;

                default:
                    System.out.println("Nepostojeca komanda!!");
                    break;
            }
        }
    }

    public static void ispisiMeni() {
        System.out.println("\n");
        System.out.println("\tRad sa predmetima studenta - opcije:");
        System.out.println("\tOpcija broj 1 - Predmeti koje student pohadja");
        System.out.println("\tOpcija broj 2 - Studenti koji pohadjaju predmet");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }

    public static void ispisiPredmeteZaStudenta() {

        Student student = StudentDAO.pronadjiStudenta();
        if(student != null) {
            System.out.println("Student " + student.getIme() + " " + student.getPrezime() +
                    " pohadja sledece predmete: \n");
            List<Predmet> predmeti = PohadjaDAO.pronadjiPredmeteZaStudenta(Application.conn, student.getId());
            predmeti.forEach(System.out::println);
        }

    }

    private static void ispisiStudenteZaPredmet() {
        String imePredmeta = ScannerWrapper.ocitajString();
        Predmet predmet = PredmetDAO.pronadjiPredmetPoImenu(Application.conn, imePredmeta);

    }

}
