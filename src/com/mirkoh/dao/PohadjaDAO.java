package com.mirkoh.dao;

import com.mirkoh.model.Predmet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
