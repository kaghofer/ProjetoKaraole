package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.sql.*;
import java.util.concurrent.TimeUnit;

public class Main {

    static final String DB_URL = "jdbc:postgresql://localhost/2023AulaJdbc";
    static final String USER = "postgres";
    static final String PASS = "123456";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "123456");

        try {
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();

            int id = 2;

            PreparedStatement ps =  conn.prepareStatement("select * from teste inner join endereco on endereco.id = teste.endereco where teste.id = ? and teste.nome like ?");
            ps.setInt(1, id);
            ps.setString(2, "%da%");

            List<Properties> list = RetornaPessoas(conn);

            for (Properties p:list) {
                System.out.print(p.get("id")+"\t");
                System.out.println(p.get("nome"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Properties> RetornaPessoas(Connection connection) throws SQLException {
        Statement st = connection.createStatement();
        String query = "select * from teste";
        ResultSet rs = st.executeQuery(query);

        List<Properties> lista = new ArrayList<>();

        while (rs.next())
        {
            Properties prop = new Properties();
            prop.setProperty("id", rs.getString("id"));
            prop.setProperty("nome", rs.getString("nome"));
            prop.setProperty("id_endereço", rs.getString("endereco"));
            lista.add(prop);
        }
        rs.close();
        return lista;
    }

}