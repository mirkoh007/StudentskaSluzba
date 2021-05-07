package com.mirkoh;

import com.mirkoh.userinterfaces.NastavnikUI;
import com.mirkoh.userinterfaces.PredmetUI;
import com.mirkoh.userinterfaces.StudentUI;
import com.mirkoh.utils.ScannerWrapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    public static Connection conn = null;
    private static final String studentskasluzba = "jdbc:mysql://localhost:3306/studentskasluzba";
    private static final String username = "root";
    private static final String password = "root";

    static {
        try {
            conn = DriverManager.getConnection(studentskasluzba,username,password);

        }catch (SQLException e) {
            System.out.println("Nesto je otislo po zlu");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        int odluka = -1;
        while (odluka != 0) {
            Application.ispisiMeni();
            System.out.println("Opcija: ");
            odluka = ScannerWrapper.ocitajCeoBroj();
            switch (odluka) {
                case 0:
                    System.out.println("Izlaz iz programa.");
                    break;

                case 1:
                    StudentUI.meni();
                    break;

                case 2:
                    NastavnikUI.meni();
                    break;

                case 3:
                    PredmetUI.meni();
                    break;

                default:
                    System.out.println("Nepostojeca komanda");
                    break;
            }
        }

    }

    private static void ispisiMeni() {

        System.out.println("Studentska sluzba - osnovne opcije:");
        System.out.println("\tOpcija broj 1 - rad sa studentima");
        System.out.println("\tOpcija broj 2 - rad sa nastavicima");
        System.out.println("\tOpcija broj 3 - rad sa predmetima");
        System.out.println("\t.....................");
        System.out.println("\tOpcija broj 0 - IZLAZ IZ PROGRAMA");
    }

}
