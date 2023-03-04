package org.example;

import java.util.Properties;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Main {

    static final String DB_URL = "jdbc:postgresql://localhost/2023AulaJdbc";
    static final String USER = "postgres";
    static final String PASS = "123456";

    public static void main(String[] args) {

        var t1 = System.nanoTime();
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123456");

        System.out.println(props.get("password"));

        try {
            Connection conn = DriverManager.getConnection(DB_URL, props);
            Statement stmt = conn.createStatement();


            String sql = "select * from teste";


            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("ID End: " + rs.getInt("endereco"));
            }
            var t2 = System.nanoTime();
            System.out.println("Temp 1: " + t1);
            System.out.println("Temp 2: " + t2);
            System.out.println("Diferença " + (t2 - t1));

            double passado = ((t2 - t1) / 1000000000);
            System.out.println(passado);


            rs.close();
        } catch (SQLException e) {
            if (e.getMessage().contains("Bad value for type")) {
                System.out.println("Coluna da pesquisa com problemas");
            }
            if (e.getMessage().toUpperCase().contains("QQQ".toUpperCase())) {
                System.out.println("Bla bla bla");
            }
            if (e.getMessage().toUpperCase().contains("PASSWORD")) {
                System.out.println("USER OU PASS ERROR");
            }
            //throw new RuntimeException(e);
        }

    }
}