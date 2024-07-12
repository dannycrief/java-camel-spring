package org.skozurak.integration.service.java_camel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DirectJdbcTest {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "docker";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, age, email, created_at, updated_at FROM person WHERE email = 'step.kozbvb@gmail.com'")) {

            while (rs.next()) {
                System.out.println("First Name: " + rs.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}