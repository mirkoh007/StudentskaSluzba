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
                    ispisiSveStudente();
                    break;
                case 2:
                    pronadjiStudentaPoID();
                    break;
                case 3:
                    pronadjiStudentaPoIndeksu();
                    break;
                case 4:
                    pronadjiStudentaPoImenu();
                    break;
                case 5:
                    pronadjiStudentaPoPrezimenu();
                    break;
                case 6:
                    unosNovogStudenta();
                    break;
                case 7:
                    izmenaPodatakaOStudentu();
                    break;
                case 8:
                    brisanjeStudenta();
                    break;
                default:
                    System.out.println("Nepostojeca opcija!");
                    break;
            }
        }
    }


    public static void ispisiMeni() {
        System.out.println("\nRad sa studentima - opcije:");
        System.out.println("\tOpcija broj 1 - Ispis svih Studenata");
        System.out.println("\tOpcija broj 2 - Pronadji studenta po ID");
        System.out.println("\tOpcija broj 3 - Pronadji studenta po INDEKSU");
        System.out.println("\tOpcija broj 4 - Pronadji studenta po IMENU");
        System.out.println("\tOpcija broj 5 - Pronadji studenta po PREZIMENU");
        System.out.println("\tOpcija broj 6 - Unos novog Studenta");
        System.out.println("\tOpcija broj 7 - Izmena podataka Studenta");
        System.out.println("\tOpcija broj 8 - Brisanje Studenta");
        System.out.println("\tOpcija broj 0 - IZLAZ");
    }

    public static void ispisiSveStudente() {
        List<Student> sviStudenti = StudentDAO.pronadjiSveStudente(Application.conn);
        sviStudenti.forEach(System.out::println);
    }

    public static void pronadjiStudentaPoID() {
        Student student;
        System.out.println("Unesi ID studenta: ");
        int id = ScannerWrapper.ocitajCeoBroj();
        student = StudentDAO.pronadjiStudentaPoID(Application.conn, id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student sa id: " + id + " ne postoji!");
        }
    }

    public static void pronadjiStudentaPoIndeksu() {
        Student student;
        System.out.println("Unesi INDEKS studenta:");
        String indeks = ScannerWrapper.ocitajString();
        student = StudentDAO.pronadjiStudentaPoIndeksu(Application.conn, indeks);
        if (student != null) {
            System.out.println(student);
            System.out.println();
        } else {
            System.out.println("Student sa indeksom " + indeks + " ne postoji!");
        }
    }

    public static void pronadjiStudentaPoImenu() {
        List<Student> studenti;
        System.out.println("Unesi ime studenta:");
        String ime = ScannerWrapper.ocitajString();
        studenti = StudentDAO.pronadjiStudentaPoImenu(Application.conn, ime);
        if (studenti != null) {
            studenti.forEach(System.out::println);
        } else {
            System.out.println("Student(i) sa imenom " + ime + " ne postoji(e).");
        }
    }

    public static void pronadjiStudentaPoPrezimenu() {
        List<Student> studenti;
        System.out.println("Unesi prezime studenta:");
        String prezime = ScannerWrapper.ocitajString();
        studenti = StudentDAO.pronadjiPoPrezimenu(Application.conn, prezime);
        if (studenti != null) {
            studenti.forEach(System.out::println);
        } else {
            System.out.println("Student(i) sa prezimenom " + prezime + " ne postoji(e).");
        }
    }

    public static void unosNovogStudenta() {
        System.out.println("Unesite indeks u formatu: E1, E2, M1, M2");
        System.out.println("Unesi indeks:");
        String indeks = ScannerWrapper.ocitajString();
        indeks = indeks.toUpperCase();
        while (pronadjiStudenta(indeks) != null) {
            System.out.println("Greska! Student sa indeksom " + indeks +
                    " vec postoji u evidenciji!");
            indeks = ScannerWrapper.ocitajString();

        }
        System.out.println("Unesi ime studenta:");
        String ime = ScannerWrapper.ocitajString();
        System.out.println("Unesi prezime studenta:");
        String prezime = ScannerWrapper.ocitajString();
        System.out.println("Unesi grad gde zivi student:");
        String grad = ScannerWrapper.ocitajString();

        Student noviStudent = new Student.Builder()
                .withId(0)
                .withIme(ime)
                .withPrezime(prezime)
                .withIndeks(indeks)
                .withGrad(grad)
                .build();

        boolean dodatStudent = StudentDAO.dodajStudenta(Application.conn, noviStudent);
        if (dodatStudent) {
            System.out.println("Student uspesno dodat u bazu.");
        } else {
            System.out.println("Doslo je do greske prilikom dodavanja nogo studenta.");
            System.out.println("Pokusajte ponovo.");
        }

    }

    public static void izmenaPodatakaOStudentu() {
        Student stariStudent = pronadjiStudenta();
        if (stariStudent != null) {
            System.out.println("Unesi novi indesk u formatu E1, M1");
            String indeks = ScannerWrapper.ocitajString().toUpperCase();
            Student postoji = pronadjiStudenta(indeks);
            if (postoji == null) {
                System.out.println("Student sa datim indkeskom ne postoji");
            } else {
                stariStudent.setIndeks(indeks);
                System.out.println("Unesi ime studenta:");
                String ime = ScannerWrapper.ocitajString();
                stariStudent.setIme(ime);
                System.out.println("Unesi prezime studenta:");
                String prezime = ScannerWrapper.ocitajString();
                stariStudent.setPrezime(prezime);
                System.out.println("Unesi grad u kome zivi student:");
                String grad = ScannerWrapper.ocitajString();
                stariStudent.setGrad(grad);

                boolean izmenjeno = StudentDAO.izmenaPodatakaStudenta(Application.conn, stariStudent);
                if (izmenjeno) {
                    System.out.println("Izmena podataka uspesno izvrsena.");
                } else {
                    System.out.println("Neuspesna izmena podataka o studentu.");
                }
            }
        }
    }

    public static void brisanjeStudenta() {
        boolean obrisan = false;
        Student student = pronadjiStudenta();
        if (student != null) {
            obrisan = StudentDAO.obrisiStudenta(Application.conn, student.getId());
        }

        if (obrisan) {
            System.out.println("Student je uspesno obrisan!");
        } else {
            System.out.println("Neuspesno brisanje studenta!");
        }
    }

    public static Student pronadjiStudenta() {
        Student nadjenStudent;
        System.out.println("Unesite indeks studenta u formatu E1, M2:");
        String indeks = ScannerWrapper.ocitajString().toUpperCase();

        nadjenStudent = pronadjiStudenta(indeks);
        if (nadjenStudent == null) {
            System.out.println("Greska! Student sa indekskom " + indeks +
                    " ne postoji u evidenciji!");
        }

        return nadjenStudent;
    }

    public static Student pronadjiStudenta(String indeks) {
        Student pronadjenStudent = StudentDAO.pronadjiStudentaPoIndeksu(Application.conn, indeks);
        return pronadjenStudent;
    }


}
