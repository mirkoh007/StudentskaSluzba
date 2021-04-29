package com.mirkoh.utils;

import java.util.Scanner;

public class ScannerWrapper {

    static Scanner scanner = new Scanner(System.in);

    public static int ocitajCeoBroj() {
        int ceoBroj = 0;
        boolean ocitan = false;
        while (!ocitan) {
            try {
                ceoBroj = scanner.nextInt();
                scanner.nextLine();
                ocitan = true;
            } catch (Exception e) {
                System.out.println("GRESKA - Pogresno unesena vrednost za ceo broj, pokusajte ponovo:");
                e.printStackTrace();
                scanner.nextLine();
            }
        }
        return ceoBroj;
    }

    public static String ocitajString() {
        String string = "";
        while (string == null || string.equals("")) {
            string = scanner.nextLine();
        }
        return string;
    }

}
