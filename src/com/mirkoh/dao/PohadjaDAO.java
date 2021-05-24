package com.mirkoh.dao;

import com.mirkoh.model.Predmet;
import com.mirkoh.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PohadjaDAO {

    public static List<Predmet> pronadjiPredmeteZaStudenta(Connection conn, int id) {
        List<Predmet> predmeti = new ArrayList<>();
        String query = "SELECT predmet_id FROM pohadja WHERE student_id = " + id;
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int predmetID = resultSet.getInt(1);
                predmeti.add(PredmetDAO.pronadjiPredmetPoID(conn, predmetID));
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }
        return predmeti;
    }

    public static List<Student> pronadjiStudenteZaPredmet(Connection conn, int predmetID) {
        List<Student> studenti = new ArrayList<>();
        String query = "SELECT student_id FROM pohadja WHERE predmet_id = " + predmetID;

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
                ) {
            while (resultSet.next()) {
                int studentID = resultSet.getInt(1);
                studenti.add(StudentDAO.pronadjiStudentaPoID(conn, studentID));
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return studenti;
    }

    public static boolean dodajStudentaNaPredmet(Connection conn, int studentID, int predmetID) {
        boolean dodato = false;
        String query = "INSERT INTO pohadja (student_id, predmet_id) values (?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, studentID);
            statement.setInt(2, predmetID);
            if (statement.executeUpdate() == 1) {
                dodato = true;
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return dodato;

    }

    public static boolean ukloniStudentaSaPredmeta(Connection conn, int student_id, int predmet_id) {
        boolean uklonjeno = false;
        String query = "DELETE FROM pohadja WHERE student_id = " + student_id + " AND predmet_id = " + predmet_id;
        try (
                Statement statement = conn.createStatement();
        ) {
            if (statement.executeUpdate(query) == 1) {
                uklonjeno = true;
            }
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return uklonjeno;
    }
}
