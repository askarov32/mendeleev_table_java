package main.mendeleyev.table_site;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.io.IOException;
import main.mendeleyev.table_site.db.DatabaseConnection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseConnection.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM elements");

            while (rs.next()) {
                System.out.println(rs.getString("column_name"));
            }

            rs.close();
            stmt.close();
            DatabaseConnection.closeConnection();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
