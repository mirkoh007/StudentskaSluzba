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
                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }

    private static void ispisiMeni() {
        System.out.println("Rad sa studentima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih studenata");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }

    private static void IspisiSveStudente() {
        List<Student> sviStudenti = StudentDAO.pronadjiSveStudente(Application.conn);
        sviStudenti.forEach(System.out::println);
    }

}
