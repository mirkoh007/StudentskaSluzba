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
}
