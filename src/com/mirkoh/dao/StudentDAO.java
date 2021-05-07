package com.mirkoh.dao;

import com.mirkoh.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public static List<Student> pronadjiSveStudente(Connection conn) {
        List<Student> sviStudenti = new ArrayList<>();
        String query = "SELECT student_id, indeks, ime, prezime, grad FROM studenti";

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String indeks = resultSet.getString(2);
                String ime = resultSet.getString(3);
                String prezime = resultSet.getString(4);
                String grad = resultSet.getString(5);

                Student student = napraviStudenta(id, ime, prezime, grad, indeks.toUpperCase());

                sviStudenti.add(student);
            }

        } catch (SQLException e) {
            System.out.println("Nesto je poslo po zlu");
            e.printStackTrace();
        }
        return sviStudenti;
    }

    public static Student pronadjiStudentaPoID(Connection conn, int id) {
        String query = "SELECT indeks, ime, prezime, grad FROM studenti WHERE student_id = " + id;
        Student student = null;

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                String indeks = resultSet.getString(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String grad = resultSet.getString(4);

                student = new Student.Builder()
                        .withId(id)
                        .withIndeks(indeks)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withGrad(grad)
                        .build();
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return student;
    }

    public static Student pronadjiStudentaPoIndeksu(Connection conn, String indeks) {
        String query = "SELECT student_id, ime, prezime, grad FROM studenti WHERE indeks = '" + indeks + "'";
        Student student = null;
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String grad = resultSet.getString(4);

                student = napraviStudenta(id, ime, prezime, grad, indeks.toUpperCase());
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu");
            e.printStackTrace();
        }
        return student;
    }

    public static List<Student> pronadjiStudentaPoImenu(Connection conn, String ime1) {
        String query = "SELECT student_id, indeks, ime, prezime, grad FROM studenti WHERE ime LIKE '%" + ime1 + "%'";

        return pretragaStudenta(conn, query);
    }

    public static List<Student> pronadjiPoPrezimenu(Connection conn, String prezime2) {
        String query = "SELECT student_id, indeks, ime, prezime, grad FROM studenti WHERE prezime LIKE '%" + prezime2 + "%'";
        return pretragaStudenta(conn, query);
    }

    private static List<Student> pretragaStudenta(Connection conn, String query) {
        List<Student> studenti = new ArrayList<>();
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            dodajUListu(studenti, resultSet);
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return studenti;
    }

    private static void dodajUListu(List<Student> studenti, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String indeks = resultSet.getString(2);
            String ime = resultSet.getString(3);
            String prezime = resultSet.getString(4);
            String grad = resultSet.getString(5);

            studenti.add(napraviStudenta(id, ime, prezime, grad, indeks));
        }
    }

    private static Student napraviStudenta(int id, String ime, String prezime, String grad, String s) {
        return new Student.Builder()
                .withId(id)
                .withIme(ime)
                .withPrezime(prezime)
                .withIndeks(s)
                .withGrad(grad)
                .build();
    }

    public static boolean dodajNovogStudenta(Connection conn, Student student) {
        boolean updated = false;
        String update = "INSERT INTO studenti (indeks, ime, prezime, grad) values (?, ?, ?, ?)";
        try {

            PreparedStatement statement = conn.prepareStatement(update);
            statement.setString(1, student.getIndeks());
            statement.setString(2, student.getIme());
            statement.setString(3, student.getPrezime());
            statement.setString(4, student.getGrad());
            if (statement.executeUpdate() == 1) {
                updated = true;
            }
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu.");
            e.printStackTrace();
        }
        return updated;
    }

    public static boolean obrisiStudenta(Connection conn, int id) {
        boolean obrisano = false;
        String obrisi = "DELETE FROM studenti WHERE student_id = " + id;
        try (
                Statement statement = conn.createStatement();
        ) {
            if (statement.executeUpdate(obrisi) == 1) {
                obrisano = true;
            }
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }
        return obrisano;
    }

    public static boolean izmenaPodatakaStudenta(Connection conn, Student stariStudent) {
        boolean uspesnaIzmena = false;
        String izmeni = "UPDATE studenti SET indeks=?, ime=?, prezime=?, grad=? WHERE student_id=?";
        try (
                PreparedStatement statement = conn.prepareStatement(izmeni);
        ) {
            statement.setString(1, stariStudent.getIndeks());
            statement.setString(2, stariStudent.getIme());
            statement.setString(3, stariStudent.getPrezime());
            statement.setString(4, stariStudent.getGrad());
            statement.setInt(5, stariStudent.getId());
            if (statement.executeUpdate() == 1) {
                uspesnaIzmena = true;
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu.");
            e.printStackTrace();
        }

        return uspesnaIzmena;
    }
}
