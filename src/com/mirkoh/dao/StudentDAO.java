package com.mirkoh.dao;

import com.mirkoh.model.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

                Student student = new Student.Builder()
                        .withId(id)
                        .withIndeks(indeks)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withGrad(grad)
                        .build();

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
            if(resultSet.next()) {
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

                student = new Student.Builder()
                        .withId(id)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withIndeks(indeks)
                        .withGrad(grad)
                        .build();
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu");
            e.printStackTrace();
        }
        return student;
    }

    public static List<Student> pronadjiStudentaPoImenu(Connection conn, String ime1) {
        String query = "SELECT student_id, indeks, ime, prezime, grad FROM studenti WHERE ime LIKE '%" + ime1 +"%'";
        List<Student> studenti = new ArrayList<>();
        try (
               Statement statement = conn.createStatement();
               ResultSet resultSet = statement.executeQuery(query)
                ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String indeks = resultSet.getString(2);
                String ime = resultSet.getString(3);
                String prezime = resultSet.getNString(4);
                String grad = resultSet.getString(5);

                studenti.add(new Student.Builder()
                        .withId(id)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withIndeks(indeks)
                        .withGrad(grad)
                        .build());
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return studenti;
    }

    public static List<Student> pronadjiPoPrezimenu(Connection conn, String prezime2) {
        String query = "SELECT student_id, indeks, ime, prezime, grad FROM studenti WHERE prezime LIKE '%" + prezime2 + "%'";
        List<Student> studenti = new ArrayList<>();
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String indeks = resultSet.getString(2);
                String ime = resultSet.getString(3);
                String prezime = resultSet.getString(4);
                String grad = resultSet.getString(5);

                studenti.add(new Student.Builder()
                        .withId(id)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withIndeks(indeks)
                        .withGrad(grad)
                        .build());
            }
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return studenti;
    }
}
