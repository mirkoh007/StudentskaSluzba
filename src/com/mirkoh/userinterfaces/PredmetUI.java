package com.mirkoh.userinterfaces;

import com.mirkoh.utils.ScannerWrapper;

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
            }
        }
    }

    public static void ispisiMeni() {
        System.out.println("Rad sa predmetima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih predmeta");
        System.out.println("\tOpcija broj 0 - IZLAZ");

    }

   public static void ispisiSvePredmete() {
       System.out.println("Bice uradjeno");
   }
}
