package com.mirkoh;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {

    public static Connection conn;
    private static final String studentskasluzba = "jdbc:mysql://localhost:3306/studentskasluzba";
    private static final String username = "root";
    private static final String password = "root";

    static {
        try {
            conn = DriverManager.getConnection(studentskasluzba,username,password);

        }catch (SQLException e) {
            System.out.println("Nesto je otislo po zlu");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {


    }

}
