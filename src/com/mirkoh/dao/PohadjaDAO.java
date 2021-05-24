package com.mirkoh.dao;

import com.mirkoh.model.Predmet;

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

    public static boolean dodajStudentaNaPredmet(Connection conn, int studentID, int predmetID) {
        boolean dodato = false;
        String query = "INSERT INTO pohadja (student_id, predmet_id) values (?,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setInt(1, studentID);
            statement.setInt(2, predmetID);
            if(statement.executeUpdate() == 1) {
                dodato = true;
            }
            statement.close();
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return dodato;

    }
}
