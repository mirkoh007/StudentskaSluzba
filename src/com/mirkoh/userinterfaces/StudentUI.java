package com.mirkoh.userinterfaces;

import com.mirkoh.Application;
import com.mirkoh.dao.StudentDAO;
import com.mirkoh.model.Student;
import com.mirkoh.utils.ScannerWrapper;

import java.util.List;

public class StudentUI {

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
                    IspisiSveStudente();
                    break;
                case 2:
                    PronadjiStudentaPoID();
                    break;
                case 3:
                    PronadjiStudentaPoIndeksu();
                    break;
                case 4:
                    PronadjiStudentaPoImenu();
                    break;
                case 5:
                    PronadjiStudentaPoPrezimenu();
                    break;
                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }


    private static void ispisiMeni() {
        System.out.println("Rad sa studentima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih studenata");
        System.out.println("\tOpcija broj 2 - Pronadji studenta po ID");
        System.out.println("\tOpcija broj 3 - Pronadji studenta po INDEKSU");
        System.out.println("\tOpcija broj 4 - Pronadji studenta po IMENU");
        System.out.println("\tOpcija broj 5 - Pronadji studenta po PREZIMENU");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }

    private static void IspisiSveStudente() {
        List<Student> sviStudenti = StudentDAO.pronadjiSveStudente(Application.conn);
        sviStudenti.forEach(System.out::println);
    }

    private static void PronadjiStudentaPoID() {
        Student student = null;
        System.out.println("Unesi ID studenta: ");
        int id = ScannerWrapper.ocitajCeoBroj();
        student = StudentDAO.pronadjiStudentaPoID(Application.conn, id);
        if(student != null) {
            System.out.println(student);
        }else {
            System.out.println("Student sa id: " + id + " ne postoji!");
        }
    }

    private static void PronadjiStudentaPoIndeksu() {
        Student student = null;
        System.out.println("Unesi INDEKS studenta:");
        String indeks = ScannerWrapper.ocitajString();
        student = StudentDAO.pronadjiStudentaPoIndeksu(Application.conn, indeks);
        if(student != null) {
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println("Student sa indeksom " + indeks + " ne postoji!");
        }
    }

    private static void PronadjiStudentaPoImenu() {
        Student student = null;
        System.out.println("Unesi ime studenta:");
        String ime = ScannerWrapper.ocitajString();
        student = StudentDAO.pronadjiStudentaPoImenu(Application.conn, ime);
        if(student !=null) {
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println("Student sa imenom " + ime + " ne postoji.");
        }
    }

    private static void PronadjiStudentaPoPrezimenu() {
        Student student = null;
        System.out.println("Unesi prezime studenta:");
        String prezime = ScannerWrapper.ocitajString();
        student = StudentDAO.pronadjiPoPrezimenu(Application.conn, prezime);
        if(student != null) {
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println("Student sa prezimenom " + prezime + " ne postoji.");
        }
    }

}
