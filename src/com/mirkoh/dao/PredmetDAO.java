package com.mirkoh.dao;

import com.mirkoh.model.Predmet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PredmetDAO {
    public static List<Predmet> pronadjiSvePredmete(Connection conn) {
        List<Predmet> sviPredmeti = new ArrayList<>();
        String qeury = "SELECT predmet_id, naziv FROM predmeti";

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(qeury)
        ) {
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

    public static List<Predmet> pronadjiPredmetePoImenu(Connection conn, String imePredmeta) {
        List<Predmet> predmeti = new ArrayList<>();
        String query = "SELECT predmet_id, naziv FROM predmeti WHERE naziv LIKE '"
                + imePredmeta + "%'";
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
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return predmeti;
    }

    public static Predmet pronadjiPredmetPoImenu(Connection conn, String imePredmeta) {
        Predmet predmet = null;
        String pronadji = "SELECT predmet_id, naziv FROM predmeti WHERE naziv = '" + imePredmeta + "'";
        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(pronadji)
                ) {
            if(resultSet.next()) {
                int id = resultSet.getInt(1);
                String naziv = resultSet.getString(2);

                predmet = new Predmet.Builder()
                        .withId(id)
                        .withNazivPredmeta(naziv)
                        .build();
            }

        }catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return predmet;
    }

    public static Predmet pronadjiPredmetPiID(Connection conn, int idPredmeta) {
        Predmet predmet = null;
        String query = "SELECT naziv FROM predmeti WHERE predmet_id = " + idPredmeta;

        try (
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(query)
        ) {
            if (resultSet.next()) {
                String naziv = resultSet.getString(1);

                predmet = new Predmet.Builder()
                        .withId(idPredmeta)
                        .withNazivPredmeta(naziv)
                        .build();
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return predmet;
    }

    public static boolean dodajNovPredet(Connection conn, Predmet predmet) {
        boolean dodat = false;
        String dodaj = "INSERT INTO predmeti (naziv) values (?)";
        try {
            PreparedStatement statement = conn.prepareStatement(dodaj);
            statement.setString(1, predmet.getNazivPredmeta());
            if (statement.executeUpdate() == 1) {
                dodat = true;
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu.");
            e.printStackTrace();
        }

        return dodat;
    }

    public static boolean obrisiPredmet(Connection conn, Predmet predmet) {
        boolean obrisano = false;
        String obrisi = "DELETE FROM predmeti WHERE predmet_id = " + predmet.getId();
        try (
                Statement statement = conn.createStatement()
                ) {
            if(statement.executeUpdate(obrisi) == 1) {
                obrisano = true;
            }

        } catch (SQLException e) {
            System.out.println("Nesto je krenulo po zlu!");
            e.printStackTrace();
        }

        return obrisano;
    }
}
