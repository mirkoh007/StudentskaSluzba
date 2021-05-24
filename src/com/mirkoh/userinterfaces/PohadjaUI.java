package com.mirkoh.userinterfaces;


import com.mirkoh.Application;
import com.mirkoh.dao.PohadjaDAO;
import com.mirkoh.dao.PredmetDAO;
import com.mirkoh.dao.StudentDAO;
import com.mirkoh.model.Predmet;
import com.mirkoh.model.Student;
import com.mirkoh.utils.ScannerWrapper;

import java.util.ArrayList;
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

                case 3:
                    dodajStudentaNaPredmet();
                    break;

                case 4:
                    ukloniStudentaSaPredmeta();
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
        System.out.println("\tOpcija broj 3 - Dodaj studenta na predmet");
        System.out.println("\tOpcija broj 4 - Ukloni studenta sa predmeta");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }

    public static void ispisiPredmeteZaStudenta() {

        Student student = StudentDAO.pronadjiStudenta();
        if(student != null) {
            System.out.println("Student " + student.getIme() + " " + student.getPrezime() +
                    " pohadja sledece predmete: \n");
            List<Predmet> predmeti = PohadjaDAO.pronadjiPredmeteZaStudenta(Application.conn, student.getId());
            if(!predmeti.isEmpty()) {
                predmeti.forEach(System.out::println);
            } else {
                System.out.println("Student ne pohadja nijedan predmet");
            }
        }

    }

    public static void ispisiStudenteZaPredmet() {
        System.out.println("Unesi ime predmeta: ");
        String imePredmeta = ScannerWrapper.ocitajString();
        Predmet predmet = PredmetDAO.pronadjiPredmetPoImenu(Application.conn, imePredmeta);
        if(predmet != null) {
            List<Student> studenti = PohadjaDAO.pronadjiStudenteZaPredmet(Application.conn, predmet.getId());
            if(!studenti.isEmpty()) {
                System.out.println("\nStudenti koji pohadjaju predmet: " + imePredmeta + " su:");
                studenti.forEach(System.out::println);
            } else {
                System.out.println("Predmet ne pohadja ni jedan student");
            }
        }

    }

    public static void dodajStudentaNaPredmet() {
        Student student = StudentUI.pronadjiStudenta();
        Predmet predmet = PredmetUI.pronadjiPredmet();
        boolean dodato = false;

        if(student != null && predmet != null) {
          dodato = PohadjaDAO.dodajStudentaNaPredmet(Application.conn, student.getId(), predmet.getId());
        }
        if(dodato) {
            System.out.println("Uspesno dodat student na predmet");
        } else {
            System.out.println("Neuspesno dodavanje");
        }
    }

    public static void ukloniStudentaSaPredmeta() {
        Student student = StudentUI.pronadjiStudenta();
        Predmet predmet = PredmetUI.pronadjiPredmet();
        boolean uklonjen = false;

        if(student != null && predmet != null) {
            uklonjen = PohadjaDAO.ukloniStudentaSaPredmeta(Application.conn, student.getId(), predmet.getId());
        }

        if(uklonjen) {
            System.out.println("Uspesno uklonjeno!");
        } else {
            System.out.println("Neuspesno uklanjanje!");
        }
    }
}
