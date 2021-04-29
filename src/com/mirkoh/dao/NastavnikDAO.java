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
            System.out.println("Nesto je krenulo po zlu");
            e.printStackTrace();
        }

        return sviNastavnici;
    }

    public static Nastavnik pronadjiNastavnikaPoID(Connection conn, int id) {
        String query = "SELECT ime, prezime, zvanje FROM nastavnici WHERE nastavnik_id =  " + id;
        Nastavnik nastavnik = null;

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                String ime = resultSet.getString(1);
                String prezime = resultSet.getString(2);
                String zvanje = resultSet.getString(3);

                nastavnik = new Nastavnik.Builder()
                        .withId(id)
                        .withIme(ime)
                        .withPrezime(prezime)
                        .withZvanje(zvanje)
                        .build();

            }
        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu.");
            e.printStackTrace();
        }

        return nastavnik;
    }

    public static List<Nastavnik> pronadjiNastavnikaPoImenu(Connection conn, String ime2) {
        List<Nastavnik> nadjeniNastavnici = new ArrayList<>();
        String pronadjiNastavnike = "SELECT nastavnik_id, ime, prezime, zvanje" +
                " FROM nastavnici WHERE ime LIKE '%" + ime2 + "%'";
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(pronadjiNastavnike)
        ) {
            kreirajNastavika(nadjeniNastavnici, resultSet);

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu.");
            e.printStackTrace();
        }
        return nadjeniNastavnici;
    }

    public static List<Nastavnik> pronadjiNastavnikaPoPrezimenu(Connection conn, String prezime2) {
        List<Nastavnik> nadjeniNastavnici = new ArrayList<>();
        String pronadjiNastavnike = "SELECT nastavnik_id, ime, prezime, zvanje " +
                "FROM nastavnici WHERE prezime LIKE '%" + prezime2 + "%'";

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(pronadjiNastavnike)
        ) {
            kreirajNastavika(nadjeniNastavnici, resultSet);

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu");
            e.printStackTrace();
        }
        return nadjeniNastavnici;
    }

    public static List<Nastavnik> pronadjiNastavnikaPoZvanju(Connection conn, String zvanje2) {
        List<Nastavnik> nadjeniNastavnici = new ArrayList<>();
        String pronadjiNastavnike = "SELECT nastavnik_id, ime, prezime, zvanje FROM nastavnici WHERE zvanje = '" + zvanje2 + "'";

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(pronadjiNastavnike)
        ) {
            kreirajNastavika(nadjeniNastavnici, resultSet);


        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu");
            e.printStackTrace();
        }

        return nadjeniNastavnici;
    }

    private static void kreirajNastavika(List<Nastavnik> nadjeniNastavnici, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String ime = resultSet.getString(2);
            String prezime = resultSet.getString(3);
            String zvanje = resultSet.getString(4);

            nadjeniNastavnici.add(new Nastavnik.Builder()
                    .withId(id)
                    .withIme(ime)
                    .withPrezime(prezime)
                    .withZvanje(zvanje)
                    .build());
        }
    }
}
