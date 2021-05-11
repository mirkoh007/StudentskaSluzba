package com.mirkoh.dao;

import com.mirkoh.model.Predmet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PredmetDAO {
    public static List<Predmet> pronadjiSvePredmete(Connection conn) {
        List<Predmet> sviPredmeti = new ArrayList<>();
        String qeury = "SELECT predmet_id, naziv FROM predmeti";

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(qeury)
                ){
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);

                Predmet predmet = new Predmet.Builder()
                        .withId(id)
                        .withNazivPredmeta(naziv)
                        .build();

                sviPredmeti.add(predmet);
            }
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu");
            e.printStackTrace();
        }

        return sviPredmeti;
    }

    public static List<Predmet> pronadjiPredmetPoImenu(Connection conn, String imePredmeta) {
        List<Predmet> predmeti = new ArrayList<>();
        String query = "SELECT predmet_id, naziv FROM predmeti WHERE naziv LIKE '"
                +imePredmeta + "%'";
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
                ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);

                predmeti.add(new Predmet.Builder()
                        .withId(id)
                        .withNazivPredmeta(naziv)
                        .build());
            }

        } catch (SQLException e) {
            System.out.printf("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return predmeti;
    }
}
