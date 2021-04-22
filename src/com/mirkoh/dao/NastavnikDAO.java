package com.mirkoh.dao;

import com.mirkoh.model.Nastavnik;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NastavnikDAO {

    public static List<Nastavnik> pronadjiSveNastavnike(Connection conn) {
        List<Nastavnik> sviNastavnici = new ArrayList<>();
        String query = "SELECT nastavnik_id, ime, prezime, zvanje FROM nastavnici";

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String zvanje = resultSet.getString(4);

                Nastavnik nastavnik = new Nastavnik.Builder()
                        .withId(id)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withZvanje(zvanje)
                        .build();
                sviNastavnici.add(nastavnik);
            }
        } catch (SQLException e) {
            System.out.println("Nesto je otislo po zlu");
            e.printStackTrace();
        }

        return sviNastavnici;
    }
}
